# 用途
数据库连接测试工具，如果能够连上，则执行sql输出版本信息（执行sql可配置），连接不上则抛出jdbc异常信息
# 使用方法
1. 在jar包同级目录的db.properties中配置待测试的数据库连接信息。
2. 在cmd中执行命令格式如下：
java -jar YJdbcConnect-1.0.jar [type]  
--type：可选参数，指定测试的数据库种类，如果不指定则在配置文件中读取  
# 输出效果
java -jar YQJdbcConnect.jar mysql  
![mysql输出结果](https://github.com/Yanqin25/imgServer/blob/master/jdbcConnect/mysql.png?raw=true)

java -jar YQJdbcConnect.jar redis  
![redis输出结果](https://github.com/Yanqin25/imgServer/blob/master/jdbcConnect/redis.png?raw=true)
# 支持类型
1. 1.0：mysql、oracle、antdb、mssql、teradata、postgresql、gaussdb、gbase、es、redis、mongodb    