package com.kafka.customserializers;

import java.util.List;

public class Tweets {
    private List<Tweet> tweetList;

    public Tweets(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    public Tweets() {
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public void setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }
}
