package org.example.tests.crud;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.endpoints.APIConstants;
import org.example.payload.Booking;
import org.example.payload.BookingWithId;
import org.example.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateBooking extends BaseTest {
    @Owner("Gaurav")
    @Description("Verify that the create booking is working fine")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"p0","sanity"})
    public void testCreateBooking() throws IOException {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        jsonPath = JsonPath.from(response.asString());
        System.out.println(jsonPath.getString("bookingid"));

        ObjectMapper objectMapper = new ObjectMapper();
        //  using Jackson to deserialize response
        //BookingWithId is pojo for response json only
        BookingWithId bookingWithJackson = objectMapper.readValue(response.asString(), BookingWithId.class);
        System.out.println(bookingWithJackson.getBooking().getFirstname());

        Assert.assertEquals(bookingWithJackson.getBooking().getLastname(),"Brown");
    }
}
