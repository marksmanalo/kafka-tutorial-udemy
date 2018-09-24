package tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
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
        String producerTopic = "first_topic";
        String value = "hello world";
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(producerTopic, value);

        // send data - asynchronous
        producer.send(record);

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();
    }
}
