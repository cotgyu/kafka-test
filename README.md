
  ## 카프카 책 실습
  
- 책 : [카프카, 데이터 플랫폼의 최강자](https://ridibooks.com/books/3649000002?_s=search&_q=%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88%EC%B5%9C%EA%B0%95%EC%9E%90&_rdt_sid=search&_rdt_idx=6)

- 실습용 컨테이너 생성/실행

    ~~~
    cd docker
    docker-compose up -d
    ~~~

### zookeeper

- 주키퍼 확인
  
    ~~~
    # 주키퍼 상태 확인
    docker exec zookeeper-local /opt/zookeeper-3.4.13/bin/zkServer.sh status

    # 주키퍼 접속 후
    # 주키퍼 CLI
    /opt/zookeeper-3.4.13/bin/zkCli.sh

    # 지노드들 확인
    ls /
    ls /peter-kafka
    ~~~

### kafka

- 카프카 확인
    
    ~~~
    # 카프카 로그 확인
    docker logs kafka-local-001
    docker logs kafka-local-002
    docker logs kafka-local-003
    ~~~



- 간단한 명령어 및 책 실습

    ~~~
    # 토픽 생성 
    /opt/kafka/bin/kafka-topics.sh \
    --zookeeper zookeeper-local:2181/peter-kafka \
    --replication-factor 1 --partitions 1 --topic peter-topic --create


    # 토픽 describe
    /opt/kafka/bin/kafka-topics.sh \
    --zookeeper zookeeper-local:2181/peter-kafka \
    --topic peter-topic --describe


    # 토픽 삭제
    /opt/kafka/bin/kafka-topics.sh \
    --zookeeper zookeeper-local:2181/peter-kafka \
    --topic peter-topic --delete


    # 메시지를 퍼블리싱 
    /opt/kafka/bin/kafka-console-producer.sh \
    --broker-list kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094 \
    --topic peter-topic 

    # 메시지 퍼블리싱 (acks 옵션 사용)
    /opt/kafka/bin/kafka-console-producer.sh \
    --broker-list kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094 \
    --topic peter-topic --request-required-acks 1


    # 메시지 가져오기
    /opt/kafka/bin/kafka-console-consumer.sh \
    --bootstrap-server kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094 \
    --topic peter-topic --from-beginning


    # 토픽 리스트
    /opt/kafka/bin/kafka-topics.sh --list --bootstrap-server kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094 


    # 토픽2 생성 (replication-factor : 브로커 몇개 쓸껀지 / 파티션 : 브로커마다 파티션 몇개 쓸껀지)
    /opt/kafka/bin/kafka-topics.sh \
    --zookeeper zookeeper-local:2181/peter-kafka \
    --replication-factor 2 --partitions 2 --topic peter-topic2 --create

    # 토픽2 describe
    /opt/kafka/bin/kafka-topics.sh \
    --zookeeper zookeeper-local:2181/peter-kafka \
    --topic peter-topic2 --describe

    # 토픽2 메시지 가져오기
    /opt/kafka/bin/kafka-console-consumer.sh \
    --bootstrap-server kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094 \
    --topic peter-topic2 --from-beginning

    # 토픽2 메시지 가져오기 (특정 파티션)
    /opt/kafka/bin/kafka-console-consumer.sh \
    --bootstrap-server kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094 \
    --topic peter-topic2 --partition 1 --from-beginning



    ## 컨슈머 그룹 리스트 
    /opt/kafka/bin/kafka-consumer-groups.sh \
    --bootstrap-server kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094 \
    --list

    ## 그룹 지정해서 가져오기
    /opt/kafka/bin/kafka-console-consumer.sh \
    --bootstrap-server kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094 \
    --topic peter-topic --group peter-consumer-group --from-beginning
    ~~~

### 참고 

- https://data-engineer-tech.tistory.com/8
- https://github.com/onlybooks/kafka
- https://stackoverflow.com/questions/70271546/how-to-run-mulitple-kafka-brokers-using-docker-compose
