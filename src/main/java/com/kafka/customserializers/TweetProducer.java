package com.kafka.customserializers;

import com.kafka.jpa.TweetRepository;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Thread.sleep;

//@Component
public class TweetProducer {

//    @Autowired
//    private TweetRepository tweetRepository;

//    public TweetProducer(TweetRepository tweetRepository) {
//        this.tweetRepository = tweetRepository;
//    }

    public static void produceTweets() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.setProperty("value.serializer", "com.kafka.customserializers.TweetsSerializer");


        KafkaProducer<String, Tweets> producer = new KafkaProducer<String, Tweets>(props);

        //todo
        //wywolac  tweetRepository.findAll();

        //RestTemplate
        WebClient webClient = WebClient.create("http://localhost:8080");

        Mono<String> response = webClient.get()
                .uri("/twitter/allTweetsFromDb")
                .retrieve()
                .bodyToMono(String.class);

        List<Tweet> tweetList = new ArrayList<Tweet>();
        Tweet tweet = new Tweet();
        tweet.setRawTweets("aaabbbcccddeee");
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


    public static void main(String[] args) {
        produceTweets();

    }


}
