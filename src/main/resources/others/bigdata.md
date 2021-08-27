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
select regexp_replace(split(line, ';')[4],'。','') as sentence from wordcount;
select explode(split(sentence, '')) as word from (select split(line, ';')[4] as sentence from wordcount) as sentence;
select word, count(1) as count from (select explode(split(sentence, '')) as word from (select split(line, ';')[4] as sentence from wordcount) as sentence) group by word order by word;

# 参考答案
SELECT word, count(1) as count
FROM (select explode(split(sentence, '')) AS word
    FROM (select split(line, ';')[4] AS sentence FROM wordcount) AS sentence) w
GROUP BY word
ORDER BY count desc
LIMIT 20;

SELECT word, count(1) as count
FROM (select explode(split(sentence, '')) AS word
    FROM (select regexp_replace(split(line, ';')[4],'。','') AS sentence FROM wordcount) AS sentence) w
GROUP BY word
ORDER BY count desc
LIMIT 20;

# 创建新表
create table wordcount(`id` string, `conversationid` string, `senderid` string, `msgtype` string, `msgcontent` string, `chattime` string)
row format delimited
fields terminated by ';'
lines terminated by '\n';

select explode(split(sentence, '')) as word
from (select trim(regexp_replace(msgcontent,'\u3002','')) as sentence from wordcount) as sentence;

select explode(split(regexp_replace(msgcontent,'\u3002','a'), '')) as word from wordcount;

# 新参考答案
SELECT word,count(1) AS count
FROM (SELECT explode(split(regexp_replace(msgcontent,'\u3002',''),'(?!\\A|\\z)')) AS word FROM wordcount) w
GROUP BY word
ORDER BY count DESC
LIMIT 20;

# 2.2 假设00：00：00 - 00：59：59 为一个小时，请问各个用户聊天记录最多的是那个时间段
#
select regexp_replace('1979/2/10 03:33:42', '/', '-')
select hour(regexp_replace(chattime, '/', '-')) from chatfrequency;

create table chatfrequency(`user_id` string, `chat_time` string) row format delimited fields terminated by '\n';
insert into chatfrequency(user_id, chat_time) select (select split(line, ';')[1] from wordcount), (select split(line, ';')[5] from wordcount) from wordcount;

select userid, chattime, count(1) as count
from (select split(line, ';')[1] as userid from wordcount), (select split(line, ';')[5] as chattime from wordcount)


# 创建新表
create table chatfrequency(`id` string, `conversationid` string, `senderid` string, `msgtype` string, `msgcontent` string, `chattime` string)
row format delimited
fields terminated by ';'
lines terminated by '\n';

# 加载数据
LOAD DATA INPATH 'hdfs://server1:9000/user/hadoop' overwrite into table chatfrequency;

select senderid,hour(regexp_replace(chattime, '/', '-')) from chatfrequency as time, count(1) as count
from chatfrequency;

select hour(regexp_replace(chattime, '/', '-')) FROM chatfrequency;

# 参考答案
SELECT senderid,hour(regexp_replace(chattime, '/', '-')),count(1) AS count
FROM chatfrequency c
GROUP BY hour(regexp_replace(chattime, '/', '-')),c.senderid
ORDER BY c.senderid,count desc;
