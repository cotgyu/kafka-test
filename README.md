~~~
docker network create zoo

docker build --tag test-zookeeper .

docker-compose up -d


docker exec peter-zk001 /usr/local/zookeeper/bin/zkServer.sh status
docker exec peter-zk002 /usr/local/zookeeper/bin/zkServer.sh status
docker exec peter-zk003 /usr/local/zookeeper/bin/zkServer.sh status
~~~





### 참고 

- https://data-engineer-tech.tistory.com/8
- https://github.com/onlybooks/kafka