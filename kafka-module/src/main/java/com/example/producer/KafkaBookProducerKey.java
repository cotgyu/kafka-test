package com.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


public class KafkaBookProducerKey {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094");
        properties.put("acks", "1");
        properties.put("compression.type", "gzip");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(properties);
        String testTopic = "peter-topic2";
        String oddKey = "1";
        String evenKey = "2";

        for (int i = 1; i < 11; i++) {
            if (i % 2 == 1) {
                producer.send(new ProducerRecord<String, String>(testTopic, oddKey, String.format("%d - Apache Kafka is a distributed streaming platform - key =" + oddKey, i)));
            } else {
                producer.send(new ProducerRecord<String, String>(testTopic, evenKey, String.format("%d - Apache Kafka is a distributed streaming platform - key=" + evenKey, i)));
            }
        }

        producer.close();
    }
}
