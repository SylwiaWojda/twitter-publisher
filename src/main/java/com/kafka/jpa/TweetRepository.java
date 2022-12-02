package com.kafka.jpa;


import com.kafka.customserializers.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {



}
