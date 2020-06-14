## 命令行构建运行

构建

```shell
$ docker build \
	-t registry.cn-hangzhou.aliyuncs.com/xionglinpeng/niveous-snowflake:1.0.0-alpine .
```

运行

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

## apollo配置
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