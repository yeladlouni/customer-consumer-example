import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Example {
    public Example() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomerDeserializer.class.getName());

        KafkaConsumer<String, Customer> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton("customers"));

        while(true) {
            ConsumerRecords<String, Customer> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord record: records) {
                String key = (String) record.key();
                Customer customer = (Customer) record.value();
                System.out.printf("key=%s, id=%d, first_name=%s, last_name=%s, age=%d, gender=%c, salary=%f",
                        key, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getAge(),
                        customer.getGender(), customer.getSalary());
            }
        }
    }

    public static void main(String[] args) {
        new Example();
    }
}
