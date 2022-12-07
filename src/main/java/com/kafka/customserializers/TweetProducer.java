package com.kafka.customserializers;

import com.jayway.jsonpath.TypeRef;
import com.kafka.jpa.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static java.lang.Thread.sleep;

@Slf4j
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
        props.setProperty("value.serializer", "com.kafka.customserializers.TweetSerializer");


        KafkaProducer<String, Tweet[]> producer = new KafkaProducer<String, Tweet[]>(props);

        //todo
        //wywolac  tweetRepository.findAll();
        //RestTemplate restTemplate = new RestTemplate();

        //dodac header z autoryzacja

        String fooResourceUrl
                = "http://localhost:8080//twitter/allTweetsFromDb";
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(fooResourceUrl, String.class);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

// Note: here we are making this converter to process any kind of response,
// not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        //restTemplate.setMessageConverters(messageConverters);

//        String plainCreds = "willie:p@ssword";
//        byte[] plainCredsBytes = plainCreds.getBytes();
//        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
//        String base64Creds = new String(base64CredsBytes);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic " + base64Creds);
//
//        HttpEntity<String> request = new HttpEntity<String>(headers);
//        ResponseEntity<Tweets> response = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, request, Tweets.class);
//        Tweets tweets = response.getBody();


//        List<Tweet> bookingIds = restTemplate.getForObject(fooResourceUrl, new TypeRef<List<Tweet>>() {
//        });
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<String> request = new HttpEntity<String>(headers);

        final RestTemplate restTemplate = new RestTemplate();
        //final String response = restTemplate.getForObject(fooResourceUrl, String.class);

        //System.out.println(response);
//        List<Tweet> tweet = restTemplate
//                .getForObject(fooResourceUrl, new ArrayList<Tweet>());


        ResponseEntity<Tweet[]> response =
                restTemplate.getForEntity(
                        fooResourceUrl,
                        Tweet[].class);
        Tweet[] tweetsArray = response.getBody();


//        Tweets response = restTemplate.getForObject(
//                fooResourceUrl,
//                Tweets.class);
//        List<Tweet> employees = response.getTweetList();





//        List<Tweet> tweets = (List<Tweet>) restTemplate
//                .getForObject(fooResourceUrl, Tweet.class);
        //ResponseEntity<Tweet> response2 = restTemplate.exchange(fooResourceUrl, Tweet.class);
//


//        ResponseEntity<String> response = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, request, String.class);
        //ResponseEntity<Tweets> response = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, request, Tweets.class);
        //Tweets tweets = response.getBody();

//        List<Tweet> tweets = (List<Tweet>) restTemplate
//                .getForObject(fooResourceUrl, Tweet.class);



//        List<Tweet> tweetList = new ArrayList<Tweet>();
//        Tweet tweet = new Tweet();
//        tweet.setRawTweets("aaabbbcccddeee");
//        tweetList.add(tweet);
//
//        Tweets tweets = new Tweets(tweetList);

        ProducerRecord<String, Tweet[]> record =
                new ProducerRecord<String, Tweet[]>("OrderCSTopic", "tweet", tweetsArray);


        try {
            //todo
            while(true) {
                log.info("record " + record.toString());
                log.info("tweet" + record.value()[0].toString());
                producer.send(record);
                for(Tweet t : record.value()) {

                    log.info("abc" + t.getRawTweets());
                }

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
