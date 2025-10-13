## Docker Compose 部署 Nacos + Dubbo Admin

``` yml
version: '3.5'
services:
  nacos:
    image: nacos/nacos-server:v2.5.0
    container_name: nacos
    restart: always
    environment:
      - PREFER_HOST_MODE=hostname
      - MODE=standalone
      - NACOS_AUTH_IDENTITY_KEY=2222
      - NACOS_AUTH_IDENTITY_VALUE=2xxx
      - NACOS_AUTH_TOKEN=SecretKey012345678901234567890123456789012345678901234567890123456789
    network_mode: "host"
  dubbo-admin:
    image: apache/dubbo-admin:0.6.0
    container_name: dubbo-admin
    restart: always
    environment:
      - TZ=Asia/Shanghai
      - admin.registry.address=nacos://127.0.0.1::8848
      - admin.config-center=nacos://127.0.0.1::8848
      - admin.metadata-report.address=nacos://127.0.0.1::8848
      - dubbo.application.name=dubbo-admin
      - dubbo.registry.address=nacos://127.0.0.1::8848
      - spring.h2.console.enabled=true
      - spring.datasource.url=jdbc:h2:mem:~/dubbo-admin;MODE=MYSQL;
      - spring.datasource.driver-class-name=org.h2.Driver
      - spring.datasource.username=sa
      - spring.datasource.password=
      - spring.jpa.hibernate.ddl-auto=update
    network_mode: "host"
    depends_on:
      - nacos
```

- Nacos 控制台界面的访问地址：`http://127.0.0.1:8848/nacos`，可以是宿主机的 IP 地址
- Dubbo Admin 控制台界面的访问地址：`http://127.0.0.1:38080`，可以是宿主机的 IP 地址，登录用户名和密码是 `root / root`

## Docker Compose 部署 ZooKeeper + Dubbo Admin

``` yml
version: '3.5'
services:
  zookeeper:
    image: zookeeper:3.8.4
    container_name: zookeeper
    restart: always
    environment:
      TZ: Asia/Shanghai
      ZOO_MY_ID: 1
      ZOO_SERVERS: server.1=zookeeper:2888:3888;2181
    network_mode: "host"
  dubbo-admin:
    image: apache/dubbo-admin:0.6.0
    container_name: dubbo-admin
    restart: always
    environment:
      - TZ=Asia/Shanghai
      - admin.registry.address=zookeeper://127.0.0.1:2181
      - admin.config-center=zookeeper://127.0.0.1:2181
      - admin.metadata-report.address=zookeeper://127.0.0.1:2181
      - dubbo.application.name=dubbo-admin
      - dubbo.registry.address=zookeeper://127.0.0.1:2181
      - spring.h2.console.enabled=true
      - spring.datasource.url=jdbc:h2:mem:~/dubbo-admin;MODE=MYSQL;
      - spring.datasource.driver-class-name=org.h2.Driver
      - spring.datasource.username=sa
      - spring.datasource.password=
      - spring.jpa.hibernate.ddl-auto=update
    network_mode: "host"
    depends_on:
      - zookeeper
```

- ZooKeeper 的访问地址：`127.0.0.1:2181`，可以是宿主机的 IP 地址
- Dubbo Admin 控制台界面的访问地址：`http://127.0.0.1:38080`，可以是宿主机的 IP 地址，登录用户名和密码是 `root / root`
