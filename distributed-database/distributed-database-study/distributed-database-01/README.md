## 项目目标

使用 Hash 取模算法实现数据库的分库。

> 特别注意，此项目模块虽然可以正常运行，但却无法实现分库，真正原因暂时不明，可能与 `sharding.jar` 的使用方式有关。

## 安装私有库

``` sh
mvn install:install-file -Dfile=sharding.jar -DgroupId=com.caland -DartifactId=sharding -Dversion=1.0.0 -Dpackaging=jar
```

## 运行环境

- JDK 1.7
- Tomcat 7

## 分库后未解决的问题

- SQL 改写问题
- 生成分布式全局唯一 ID 
- 跨库的事务支持（分布式事务）
- 部分查询需要一次性查询多个库才能获取到完整的数据，比如查询当天的所有与订单
    - 也就是常说的数据异构问题（查询数据结果集的合并）
    - 可以采用 MySQL + Kafka + ElasticSearch 的方式，来解决数据异构问题
    - 本质也就是将 MySQL 所有库的数据通过 Kafka 同步到 ElasticSearch，然后再通过 ElasticSearch 来检索数据

## 参考项目

- [uncode-framework-parent](https://github.com/doublexxxxxx/uncode-framework-parent/tree/master/uncode-framework-cluster_cloud)

## 参考教程

- [数据库高并发解决方案](https://my.oschina.net/bigdataer/blog/486772)

