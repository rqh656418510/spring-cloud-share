## 启动多个相同类型的服务

``` shell
java -jar -Xms256m -Xmx512m -Dserver.port=8001 /usr/develop/bilibili-workspace/spring-cloud-study-2024/cloud-provoder-payment/target/cloud-provoder-payment-1.0-SNAPSHOT.jar
java -jar -Xms256m -Xmx512m -Dserver.port=8002 /usr/develop/bilibili-workspace/spring-cloud-study-2024/cloud-provoder-payment/target/cloud-provoder-payment-1.0-SNAPSHOT.jar
java -jar -Xms256m -Xmx512m -Dserver.port=8003 /usr/develop/bilibili-workspace/spring-cloud-study-2024/cloud-provoder-payment/target/cloud-provoder-payment-1.0-SNAPSHOT.jar
```
