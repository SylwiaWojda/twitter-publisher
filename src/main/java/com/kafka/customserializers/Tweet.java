package com.kafka.customserializers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;

//@Entity(name = "Tweet_details")
public class Tweet implements Serializable {


    /*{"id":1,
     "rawTweets":"While our car was returning, some cars chased us. BJP candidate (from Danta constituency) Latu Parghi &amp; 2 others came with weapons, with swords. We thought we must escape, we ran for 10-15 km &amp; for 2 hours we were in the jungle: Cong's Danta constituency candidate Kanti Kharadi https://t.co/QRN9JRO539",
     "amount":"100"}*/

//    @Id
//    @GeneratedValue
    private Integer id;
//    @Column(length = 65555000)
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
