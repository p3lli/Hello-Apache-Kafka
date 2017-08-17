package it.pelli.samples.consumers;

import com.google.common.io.Resources;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import it.pelli.samples.utils.Utils;

public class Consumer {
    public static void main(String[] args) throws IOException {
        KafkaConsumer<String, String> consumer;
        try (InputStream props = Resources.getResource("consumer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            if (properties.getProperty("group.id") == null) {
                properties.setProperty("group.id", "group-" + new Random().nextInt(100000));
            }
            consumer = new KafkaConsumer<>(properties);
        }

        ArrayList<String> topics = new ArrayList<String>();

        if (args.length > 1) {
            for (int i=1; i<args.length; i++) {
                if (Arrays.asList(Utils.TOPICS).contains(args[i])) {
                    topics.add(args[i]);
                }
            }
        } else {
            topics.addAll(Arrays.asList(Utils.TOPICS));
        }

        consumer.subscribe(topics);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);
            for (ConsumerRecord<String, String> record : records) {
		try {
                    String message = Utils.convertMessage(record.topic(), record.value());
	            System.out.printf("Got message for topic '%s' : %s\n", record.topic(), message);
		} catch (Exception e) {
		    System.err.printf("Caught Exception: %s", e.getMessage());
                    e.printStackTrace();
		}
            }
        }
    }
}
