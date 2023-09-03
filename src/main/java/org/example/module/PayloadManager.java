package org.example.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.example.payload.Booking;
import org.example.payload.Bookingdates;

public class PayloadManager {

    ObjectMapper objectMapper;

//    {
//        "firstname" : "Jimmy",
//            "lastname" : "Brown",
//            "totalprice" : 134,
//            "depositpaid" : true,
//            "bookingdates" : {
//        "checkin" : "2023-05-20",
//                "checkout" : "2023-05-21"
//    },
//        "additionalneeds" : "Breakfast"
//    }
    public String createPayload() throws JsonProcessingException {

        //Faker faker = new Faker();
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Jimmy"); // faker.name().firstName()
        booking.setLastname("Brown");
        booking.setTotalprice(134);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2023-05-20");
        bookingdates.setCheckout("2023-05-21");

        booking.setBookingdates(bookingdates);

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public String updatePayload() throws JsonProcessingException {

        Faker faker = new Faker();
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Brown");
        booking.setTotalprice(134);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2023-05-20");
        bookingdates.setCheckout("2023-05-21");

        booking.setBookingdates(bookingdates);

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }
}
