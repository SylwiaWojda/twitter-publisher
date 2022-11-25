package com.kafka.customserializers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


public class OrderProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.setProperty("value.serializer", "com.kafka.customserializers.OrderSerializer");


        KafkaProducer<String, Order> producer = new KafkaProducer<String, Order>(props);
        
        //0. musi miec polaczenie z baza

        //1. wybrac tweety z bazy danych
        // jak przekazac parameter do wyszukiwania?

        //2. zrobic obiekt tweet zamiast order


        Order order = new Order();
        order.setCustomerName("Sylwia");
        order.setProduct("Iphone");
        order.setQuantity(1);
        ProducerRecord<String,Order> record = new ProducerRecord<>("OrderCSTopic", order.getCustomerName(), order);

        try {
            while(true) {
                producer.send(record);
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
