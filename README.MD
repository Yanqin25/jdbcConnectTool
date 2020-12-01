# 用途
数据库连接测试工具，如果能够连上，则执行sql输出版本信息（执行sql可配置），连接不上则抛出jdbc异常信息
# 软件获取
1. 编译打包：mvn assembly:assembly
2. 直接在release下载zip包（包含jar包和对应配置文件）
# 使用方法
1. 将resources/applicationContext.xml复制到jar包同级目录，并在其中配置待测试的数据库连接信息。
2. 将resources/db.properties复制到jar包同级目录，可在其中指定表格显示宽度，最大长度，以及本次测试的数据库类型。
3. 连接测试：java -jar YJdbcConnect-1.0.jar [type]  
--type：可选参数，指定测试的数据库种类，不指定则在配置文件中读取  
# 输出效果
java -jar YQJdbcConnect.jar mysql  
![mysql输出结果](https://github.com/Yanqin25/imgServer/blob/master/jdbcConnect/mysql.png?raw=true)

java -jar YQJdbcConnect.jar redis  
![redis输出结果](https://github.com/Yanqin25/imgServer/blob/master/jdbcConnect/redis.png?raw=true)
# 版本变更历史
1. v1.0：基础实现；支持mysql、oracle、antdb、mssql、teradata、postgresql、gaussdb、gbase、es、redis、mongodb    
2. v1.0.1：使用spring改写（原来的工厂模式->>利用spring的IOC和DI实现）;修复sql结果中含有制表符时显示错位的情况，增强封装，简化printTable的调用方式
3. v1.0.2: 增加批量创建表及插入数据的功能；接入sybase，informix，vertica能力；数据库版本信息可以直接从Connection对象获取，不必手工写sql        