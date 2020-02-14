import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {

    public static void main(String[] args) {
        final var properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        final var consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(List.of("some-topic"));
        while (true)  {
            final var records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record -> {
                final var message =
                    "Key: [" + record.key() + "] " +
                    "Value: [" + record.value() + "] " +
                    "Offset: [" + record.offset() + "] " +
                    "Partition: [" + record.partition() + "]";
                System.out.println(message);
            });
        }
    }
}
