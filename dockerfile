FROM ubuntu:18.04 
RUN mkdir -p /root/install 
RUN apt-get update 
RUN apt-get install systemd -y

WORKDIR /root/install 

# java 설치 준비 
ENV DEBIAN_FRONTEND noninteractive 
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 

# java 설치 
RUN apt-get install openjdk-8-jdk -y 
RUN apt-get install wget -y 
RUN apt-get install vim -y 

# zookeeper 설치 
RUN wget downloads.apache.org/zookeeper/zookeeper-3.7.0/apache-zookeeper-3.7.0-bin.tar.gz 
RUN tar -zxvf apache-zookeeper-3.7.0-bin.tar.gz 
RUN mv apache-zookeeper-3.7.0-bin /usr/local/zookeeper 

# 설정파일 및 초기화 파일 복사 
COPY config/zoo.cfg /usr/local/zookeeper/conf/zoo.cfg 
COPY config/init.sh init.sh 
COPY config/zookeeper-server.service /etc/systemd/system/zookeeper-server.service

CMD bash init.sh

