package com.kafka.customserializers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "Tweet_details")
public class Tweet {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 65555000)
    private String rawTweets;
    
    private String amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRawTweets() {
        return rawTweets;
    }

    public void setRawTweets(String rawTweets) {
        this.rawTweets = rawTweets;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Tweet(Integer id, String rawTweets, String amount) {
        this.id = id;
        this.rawTweets = rawTweets;
        this.amount = amount;
    }

    public Tweet() {
    }
    
}
