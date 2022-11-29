package com.kafka.customserializers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static java.lang.Thread.sleep;


public class TweetProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.setProperty("value.serializer", "com.kafka.customserializers.TweetSerializer");


        KafkaProducer<String, Tweet> producer = new KafkaProducer<String, Tweet>(props);
        
        //0. musi miec polaczenie z baza

        //1. wybrac tweety z bazy danych
        // jak przekazac parameter do wyszukiwania?


        Tweet tweet = new Tweet();
        tweet.setRawTweets("aaabbbccc");
        ProducerRecord<String,Tweet> record = new ProducerRecord<>("OrderCSTopic", tweet.getRawTweets(), tweet);


        try {
            while(true) {
                producer.send(record);
                sleep(100000);
            }
//            System.out.println(recordMetadata.partition());
//            System.out.println(recordMetadata.offset());
//            System.out.println("Message send suc.");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }


    }


}
