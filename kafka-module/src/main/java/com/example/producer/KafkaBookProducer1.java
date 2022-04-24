package com.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;


public class KafkaBookProducer1 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094");
        properties.put("acks", "1");
        properties.put("compression.type", "gzip");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(properties)) {
//            Future<RecordMetadata> metadata = producer.send(new ProducerRecord<String, String>("peter-topic", "Apache Kafka is a distributed streaming platform"));
//            System.out.printf("Partition: %dm, Offset: %d", metadata.get().partition(), metadata.get().offset());

            producer.send(new ProducerRecord<String, String>("peter-topic", "Apache Kafka is a distributed streaming platform"), new PeterCallback());
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
}
