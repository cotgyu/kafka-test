### zookeeper

~~~
docker network create zoo

cd zookeeper
docker build --tag test-zookeeper .
docker-compose up -d

## 상태 확인 
docker exec peter-zk001 /usr/local/zookeeper/bin/zkServer.sh status
docker exec peter-zk002 /usr/local/zookeeper/bin/zkServer.sh status
docker exec peter-zk003 /usr/local/zookeeper/bin/zkServer.sh status


# 주키퍼 CLI
/usr/local/zookeeper/bin/zkCli.sh

# 지노드들 확인
ls /
ls /peter-kafka
~~~

### kafka

~~~
cd kafka
docker build --tag test-kafka .
docker-compose up -d

## 로그 확인 
docker logs peter-kafka001
~~~

~~~
## 토픽생성
/usr/local/kafka/bin/kafka-topics.sh \
--zookeeper perter-zk001:2181,peter-zk002:2182,peter-zk003:2183/peter-kafka \
--replication-factor 1 --partitions 1 --topic peter-topic --create

## 토픽삭재
/usr/local/kafka/bin/kafka-topics.sh \
--zookeeper perter-zk001:2181,peter-zk002:2182,peter-zk003:2183/peter-kafka \
--topic peter-topic --delete


## 메시지를 퍼블리싱 
/usr/local/kafka/bin/kafka-console-producer.sh \
--broker-list peter-kafka001:9092,peter-kafka002:9092,peter-kafka003:9092 \
--topic peter-topic 

## 메시지 퍼블리싱 (acks 옵션 사용)
/usr/local/kafka/bin/kafka-console-producer.sh \
--broker-list peter-kafka001:9092,peter-kafka002:9093,peter-kafka003:9094 \
--topic peter-topic --request-required-acks 1

## 메시지 가져오기
/usr/local/kafka/bin/kafka-console-consumer.sh \
--bootstrap-server peter-kafka001:9092,peter-kafka002:9093,peter-kafka003:9094 \
--topic peter-topic --from-beginning

## 3장 
# 토픽 생성
/usr/local/kafka/bin/kafka-topics.sh \
--zookeeper peter-zk001:2181,peter-zk002:2182,peter-zk003:2183/peter-kafka \
--replication-factor 3 --partitions 1 --topic peter-topic --create

# 토픽 describe
/usr/local/kafka/bin/kafka-topics.sh \
--zookeeper peter-zk001:2181,peter-zk002:2182,peter-zk003:2183/peter-kafka \
--topic peter-topic2 --describe


# 토픽 리스트
/usr/local/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092 
~~~

### 참고 

- https://data-engineer-tech.tistory.com/8
- https://github.com/onlybooks/kafka

