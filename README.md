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
~~~

### kafka

~~~
cd kafka
docker build --tag test-kafka .
docker-compose up -d

## 로그 확인 
docker logs peter-kafka001
~~~





### 참고 

- https://data-engineer-tech.tistory.com/8
- https://github.com/onlybooks/kafka

