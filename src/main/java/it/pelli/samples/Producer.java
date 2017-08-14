package it.pelli.samples;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import au.com.bytecode.opencsv.CSVReader;

public class Producer {
    public static void main(String[] args) throws IOException {
        KafkaProducer<String, String> producer;
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }

        int messageCount = 0;

	if (args[1] != null) {
           try {
    	        CSVReader reader = new CSVReader(
                    new FileReader(Utils.validateFilePath(args[1])));
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    String topic = nextLine[0];
                    String message = nextLine[1] + ": " + nextLine[2];
                    if (Arrays.asList(Utils.TOPICS).contains(topic)) {
                        ProducerRecord<String, String> data = 
                             new ProducerRecord<String, String>
                                 (topic, message);
                        producer.send(data);
                        producer.flush();
                        System.out.printf("Sent message number %d: topic %s: %s\n", messageCount, topic, message);
                        messageCount++;
                    } else {
                        throw new IllegalStateException("Shouldn't be possible to get message on topic '" + topic + "'");
                    }
    		    TimeUnit.SECONDS.sleep(1);
                }
            } catch (Exception e) {
                System.err.printf("Caught Exception: %s", e.getMessage());
                e.printStackTrace();
            } finally {
                producer.close();
            }
        } else {
            System.out.println("You must specify a CSV file!");
            producer.close();
        }
    }
}
