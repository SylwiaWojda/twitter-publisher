package com.kafka.customserializers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Thread.sleep;


public class TweetProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.setProperty("value.serializer", "com.kafka.customserializers.TweetsSerializer");


        KafkaProducer<String, Tweets> producer = new KafkaProducer<String, Tweets>(props);


        List<Tweet> tweetList = new ArrayList<Tweet>();
        Tweet tweet = new Tweet();
        tweet.setRawTweets("aaabbbccc");
        tweetList.add(tweet);

        Tweets tweets = new Tweets(tweetList);

        ProducerRecord<String,Tweets> record = new ProducerRecord<>("OrderCSTopic", tweet.getRawTweets(), tweets);


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
