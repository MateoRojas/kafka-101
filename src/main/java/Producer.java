import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final var properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        final var producer = new KafkaProducer<String, String>(properties);
        final var record = new ProducerRecord<>("some-topic", "any-key", "any-value");
        // Sync vs Async
        producer.send(record).get();
        System.out.println("---------> Record sent!");
    }
}
