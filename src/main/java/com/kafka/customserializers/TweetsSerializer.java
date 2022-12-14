//package com.kafka.customserializers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.common.serialization.Serializer;
//
//public class TweetsSerializer implements Serializer<Tweets> {
//
//    @Override
//    public byte[] serialize(String s, Tweets tweet) {
//        byte[] response = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            response = objectMapper.writeValueAsString(tweet).getBytes();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return response;
//    }
//}
