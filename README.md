# seckill（Java高并发秒杀API）

#### 所用技术点
 - spring
 - springMVC: MVC框架
 - Tomcat: web容器
 - mybatis: ORM框架
 - bootstrap: css/html框架
 - JQuery: JS框架
 - Redis: NOSQL数据库
 - MySQL: 关系型数据库
 - Logback: 日志框架
 - JUnit: 单元测试
 - CDN: 内容分发服务器
 - Procedure:数据库存储过程
 - Protostuff:Google开发的基于Java语言的序列化库
 - ZooKeeper:分布式应用程序协调服务 //TODO
 - DUBBO:分布式应用服务框架 //TODO

## Reference

详细开发教程可以参考[codingXiaxw的开发文档],简直就是手把手教你做开发啊!!!

## Java高并发秒杀系统API

本项目参考慕课网视频(版权方),并在此基础上进行了模块划分,功能添加,欢迎去官网观看!!!!

## 安装部署

 #### 软件环境：

  - IDEA
  - MySQL
  - JDK1.6或以上
  - tomcat 8.0
  - Redis
  - Maven
 #### 硬件环境(最小配置)：

  - CPU：1核
  - 内存：1G

 #### 说明
 - seckill-core：秒杀核心模块，部署tomcat启动
 - seckill-api：秒杀api模块
 - seckill-base：秒杀工具类模块
 - seckill-web：秒杀页面模块

 #### 步骤
 1. 创建数据库，导入初始化脚本initDB.sql
 2. 修改系统数据库连接seckill-core/src/main/resources/jdbc.properties
 3. Tomcat运行

## FAQ
- Q:为什么我的maven下载依赖jar包这么慢
- A:可以自己下载maven,使用自己的Maven,修改maven安装目录下的/conf/settings.xml,在IDEA或eclipse里配置为默认的设置
```XML
                           <mirror>
                               <id>nexus-aliyun</id>
                               <mirrorOf>central</mirrorOf>
                               <name>Nexus aliyun</name>
                               <url>http://maven.aliyun.com/nexus/content/groups/public</url>
                           </mirror>
```

- Q:为什么我的数据库连不上
- A:查看一下jdbc.properties，配置url为你的数据库地址,你的数据库用户名和密码

- Q:为什控制台报Redis异常
- A:查看一下Redis服务器是否启动，没下载的建议去官网下载window或linux版


