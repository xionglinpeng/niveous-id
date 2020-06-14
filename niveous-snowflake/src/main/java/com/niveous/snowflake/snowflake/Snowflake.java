package com.niveous.snowflake.snowflake;

/**
 * <p>twitter的snowflake算法</p>
 *
 * @author xlp
 * @version 1.0.0
 * @since 2020/6/7 15:16
 */
public class Snowflake {

    //每一部分占用位数
    private static final int SEQUENCE_BIT = 12; //序列号
    private static final int MACHINE_BIT = 5; //机器标识
    private static final int DATA_CENTER_BIT = 5; //数据中心标识

    //每一部分最大值
    private static final long MAX_SEQUENCE_VALUE = ~(-1L << SEQUENCE_BIT);
    private static final long MAX_MACHINE_VALUE = ~(-1L << MACHINE_BIT);
    private static final long MAX_DATA_CENTER_VALUE = ~(-1L << DATA_CENTER_BIT);

    //每一部分左移位
    private static final int MACHINE_LEFT_OFFSET = SEQUENCE_BIT;
    private static final int DATA_CENTER_LEFT_OFFSET = MACHINE_LEFT_OFFSET + MACHINE_BIT;
    private static final int TIMESTAMP_LEFT_OFFSET = DATA_CENTER_LEFT_OFFSET + DATA_CENTER_BIT;

    /**
     * 起始时间
     */
    private final long startTimestamp;

    /**
     * 机器ID
     */
    private final long machineId;

    /**
     * 数据中心ID
     */
    private final long dataCenterId;

    /**
     * 序列号
     */
    private long sequenceNumber = 0L;

    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    public Snowflake(long startTimestamp, long machineId, long dataCenterId) {
        if (machineId > MAX_MACHINE_VALUE || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_VALUE or less then 0");
        }
        if (dataCenterId > MAX_DATA_CENTER_VALUE || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater then MAX_DATA_CENTER_VALUE or less then 0");
        }
        this.startTimestamp = startTimestamp;
        this.machineId = machineId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();
        //处理时间回溯的情况
        if (currentTimestamp < lastTimestamp) {
            throw new NiveousSnowflakeException("Clock moved backwards. Refusing to generate id");
        }
        if (currentTimestamp == lastTimestamp) {
            //同一毫秒内，序列号自增
            sequenceNumber = (sequenceNumber + 1) & MAX_SEQUENCE_VALUE;
            //序列号到达最大值（当前毫秒内的序列号已经用完），等待至下一毫秒
            if (sequenceNumber == 0L) {
                currentTimestamp = getNextTimestamp();
            }
        } else {
            //新的时间戳，将序列号重置为0（序列号从0开始）
            sequenceNumber = 0L;
        }
        lastTimestamp = currentTimestamp;
        //组装每一部分数据
        return (currentTimestamp - startTimestamp) << TIMESTAMP_LEFT_OFFSET //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT_OFFSET                   //数据中心部分
                | machineId << MACHINE_LEFT_OFFSET                          //机器标识部分
                | sequenceNumber;                                           //序列号部分
    }

    /**
     * 获取上一次时间戳之后时间戳，只有当当前时间大于上一次时间，就会返回。
     *
     * @return 时间搓
     */
    private long getNextTimestamp() {
        long mill;
        do {
            mill = System.currentTimeMillis();
        } while (mill <= lastTimestamp);
        return mill;
    }
}
