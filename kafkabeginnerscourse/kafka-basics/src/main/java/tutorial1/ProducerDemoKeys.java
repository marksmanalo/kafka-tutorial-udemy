package tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // create Producer properties
        String bootstrapServerAddress = "127.0.0.1:9092";
        String serializerName = StringSerializer.class.getName();
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializerName);
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializerName);

        // create the producer
        KafkaProducer<String, String> producer =new KafkaProducer<String, String>(properties);

        // create a producer record


        // send data - asynchronous
        for (int i = 0; i < 10; i++) {

            String producerTopic = "first_topic";
            String value = "hello world " + Integer.toString(i);
            String key = "id_" + Integer.toString(i);

            ProducerRecord<String, String> record = new ProducerRecord<String, String>(producerTopic, key, value);

            final Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

            logger.info("Key: " + key); // log the key
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // executes everytime a record is successfully sent or an exception is thrown
                    if (e == null) {
                        // the record was successfully sent
                        logger.info("Received new metadata." + "\n" +
                                "Topic: " + recordMetadata.topic() + "\n" +
                                "Partition: " + recordMetadata.partition() + "\n" +
                                "Offset: " + recordMetadata.offset() + "\n" +
                                "Timestamp" + recordMetadata.timestamp());
                    } else {

                    }
                }
            }).get(); // block the .send() to make it synchronous - don't do this in production
        }

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();
    }
}
