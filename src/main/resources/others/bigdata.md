## 关卡四
# 1 将已生成的假数据上传hdfs
# 将文件上传hdfs
hdfs dfs -ls /
hdfs dfs -mkdir /user/hadoop
hdfs dfs -put txt文件的linux路径 /user/hadoop


# 2 通过hive查询文档中的数据回答以下两个问题
# 连接beeline
./beeline -u jdbc:hive2://server1:10000
hdfs dfs -chmod -R 777 /user（optional）
hdfs dfs -chmod -R 777 /tmp

# 2.1 聊天内容中，出现频率最高的二十个汉字，以及他们分别出现了几次
# 新建hive表
create table wordcount (`line` string) row format delimited fields terminated by '\n';
# 将数据从hdfs导入hive
load data inpath 'hdfs://server1:9000/user/hadoop' overwrite into table wordcount;

select split(line, ';')[4] as sentence from wordcount;
select explode(split(sentence, '')) as word from (select split(line, ';')[4] as sentence from wordcount) as sentence;
select word, count(1) as count from (select explode(split(sentence, '')) as word from (select split(line, ';')[4] as sentence from wordcount) as sentence) group by word order by word;

# 参考答案
select word, count(1) as count
from (select explode(split(sentence, '')) as word
    from (select split(line, ';')[4] as sentence from wordcount) as sentence) w
group by word
order by count desc
limit 20;


# 2.2 假设00：00：00 - 00：59：59 为一个小时，请问各个用户聊天记录最多的是那个时间段
#




