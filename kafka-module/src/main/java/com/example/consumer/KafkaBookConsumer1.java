package com.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaBookConsumer1 {
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "kafka-local-001:9092,kafka-local-002:9093,kafka-local-003:9094");
        properties.put("group.id", "peter-consumer");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.offset.reset", "latest");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        try (consumer) {
            consumer.subscribe(Arrays.asList("peter-topic"));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Topic : %s, Partition: %s, Offset: %d, Key: %s, Value: %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
                }
            }
        }
    }
}
