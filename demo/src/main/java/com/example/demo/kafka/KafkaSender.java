package com.example.demo.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaSender
{
    private final static String TOPIC = "test-topic-study2";
    private final static String CLIENT_ID = "KafkaStudyProducer";
    private final static String BOOTSTRAP_SERVERS = "broker:29092";

    private static final Logger logger = LoggerFactory.getLogger("App");

 /////////
    //    sendMessageSync("car-J634XO", json2);
  ///////////////


    private static Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    public static void sendMessage(final String key, final String message) throws Exception {
        final Producer<String, String> producer = createProducer();
        long time = System.currentTimeMillis();

        try {
            final ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, message);
            RecordMetadata metadata = producer.send(record).get();

            long elapsedTime = System.currentTimeMillis() - time;

            logger.info("sent record(key={} value={}) meta(partition={}, offset={}) time={}",
                    record.key(), record.value(), metadata.partition(),
                    metadata.offset(), elapsedTime);

        } finally {
            producer.flush();
            producer.close();
        }
    }

}
