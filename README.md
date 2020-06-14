# Niveous ID
Niveous-id包含雪花算法的分布式ID，集成了Apollo和Eureka进行远程配置和服务发现，并提供了Spring Cloud openFeign客户端实现。

## docker部署
niveous-id镜像已被推送至阿里云仓库，使用如下命令拉取：
```shell
$ docker pull registry.cn-hangzhou.aliyuncs.com/xionglinpeng/niveous-snowflake:1.0.0-alpine
```
启动
```shell
$ docker run \
    -p 17890:8080 \
    --env APOLLO_META=http://apollo-configservice:8080 \
    --env APP_ID=niveous-snowflake \
    --env ENV=DEV --name niveous-snowflake \
    --network network-apollo \
    --memory=200M \
    --cpu-shares=5 \
    registry.cn-hangzhou.aliyuncs.com/xionglinpeng/niveous-snowflake:1.0.0-alpine -d
```
**注意：**

需要保证Apollo服务处于运行状态。Apollo的配置请根据个人的实际情况配置。

*Apollo远程配置示例：*
```properties
spring.application.name = niveous-snowflake
server.port = 8080
eureka.client.service-url.defaultZone = http://apollo-configservice:8080/eureka/

niveous.snowflake.start-timestamp = 1591876094503
niveous.snowflake.machine-id = 25
niveous.snowflake.data-center-id = 25

management.server.port = 8080
management.endpoints.jmx.exposure.include = *
management.endpoints.web.exposure.include = *
management.endpoint.health.show-details = always
```