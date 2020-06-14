FROM openjdk:8-jre-alpine
MAINTAINER XLP <xionglinpeng@163.com>

ENV VERSION 1.0.0
ENV SERVER_PORT 8080

# procps unzip curl bash tzdata
RUN echo "http://mirrors.aliyun.com/alpine/v3.8/main" > /etc/apk/repositories \
        && echo "http://mirrors.aliyun.com/alpine/v3.8/community" >> /etc/apk/repositories \
        && apk update upgrade \
        && apk add --no-cache procps bash tzdata\
        && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
        && echo "Asia/Shanghai" > /etc/timezone

COPY scripts /niveous/snowflake/scripts/
COPY target/niveous-snowflake-eureka-${VERSION}.jar /niveous/snowflake/niveous-snowflake-eureka-${VERSION}.jar

EXPOSE $SERVER_PORT

CMD ["/niveous/snowflake/scripts/start.sh"]