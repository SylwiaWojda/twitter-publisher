//package com.kafka;
//
//import com.kafka.customserializers.Tweet;
//import com.kafka.customserializers.TweetProducer;
//import com.kafka.jpa.TweetRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
////@SpringBootApplication
//public class TwitterPublisherApi implements CommandLineRunner {
//
//    @Autowired
//    private TweetRepository tweetRepository;
//
//    @Autowired
//    TweetProducer tweetProducer;
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(TwitterPublisherApi.class, args);
//        System.out.println("Hello world!");
//
//        tweetProducer.produceTweets();
//
//    }
//
//    @Override
//    public void run(String...args) throws Exception {
//          tweetProducer.produceTweets();
//
//
////        RestTemplate restTemplate = new RestTemplate();
////        Country country = restTemplate.getForObject("http://www.services.groupkt.com/country/get/iso2code/US", Country.class);
////        log.info(country.toString());
//
//
//    }
//
//}
