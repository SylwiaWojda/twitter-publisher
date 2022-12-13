//package com.kafka.customserializers;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.http.*;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Properties;
//
//import static java.lang.Thread.sleep;
//
//@Slf4j
//public class TweetProducer {
//
//
//
//    public static void produceTweets() {
//        Properties props = new Properties();
//        props.setProperty("bootstrap.servers", "localhost:9092");
//        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.setProperty("value.serializer", "com.kafka.customserializers.TweetSerializer");
//
//
//        KafkaProducer<String, Tweet[]> producer = new KafkaProducer<String, Tweet[]>(props);
//
//        String fooResourceUrl
//                = "http://localhost:8080//twitter/allTweetsFromDb";
//
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//
//// Note: here we are making this converter to process any kind of response,
//// not only application/*json, which is the default behaviour
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//
//        final RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<Tweet[]> response =
//                restTemplate.getForEntity(
//                        fooResourceUrl,
//                        Tweet[].class);
//        Tweet[] tweetsArray = response.getBody();
//
//        ProducerRecord<String, Tweet[]> record =
//                new ProducerRecord<String, Tweet[]>("TweetTopic", "tweet", tweetsArray);
//
//        try {
//            //todo
//            while(true) {
////                if(record != null && record.value() != null
////                && 0 < record.value().length
////                && null != record.value()[0]) {
////                    log.info("record " + record.toString());
////                    log.info("tweet" + record.value()[0].toString());
////                } else {
////                    log.info("NO RECORDS !!");
////                }
//
//                producer.send(record);
////                for(Tweet t : record.value()) {
////
////                    log.info("raw tweet " + t.getRawTweets());
////                    log.info("amount " + t.getAmount());
////                }
//
//                sleep(100000);
//            }
////            System.out.println(recordMetadata.partition());
////            System.out.println(recordMetadata.offset());
////            System.out.println("Message send suc.");
//        } catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            producer.close();
//        }
//    }
//
//
//    public static void main(String[] args) {
//        produceTweets();
//
//    }
//
//
//}
