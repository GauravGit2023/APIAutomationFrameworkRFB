package org.example.tests.integrationtests;

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
import org.example.tests.base.BaseTest;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class IntegrationTest extends BaseTest {

    String token;

    @Test(groups = "integration")
    @Owner("Gaurav")
    @Description("Verify the booking is created or Not")
    public void testCreateBooking(ITestContext iTestContext) throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        jsonPath = JsonPath.from(response.asString());
        System.out.println(jsonPath.getString("bookingid"));
        iTestContext.setAttribute("bookingid",jsonPath.getString("bookingid"));
        // we can use global variable, however it will need to run this test always before the 2nd one, while icontext will set once it runs
        //AssertJ
        // Either verify with string or convert to object with objectMapper and then verify
        //assertActions.verifyResponseBody(jsonPath.getString("firstname"),"Jimmy","Matched");

        ObjectMapper objectMapper = new ObjectMapper();
        //  using Jackson to deserialize response
        Booking bookingWithJackson = objectMapper.readValue(response.asString(), Booking.class);
        System.out.println(bookingWithJackson.getLastname());
    }

    @Test(groups = "integration", dependsOnMethods = "testCreateBooking")
    @Owner("Gaurav")
    @Description("Verify full update of previous booking is working")
    public void testCreateAndUpdateBooking(ITestContext iTestContext) throws JsonProcessingException {
        // need - token, booking id
        token = getToken();
        String bookingId = (String) iTestContext.getAttribute("bookingid");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token).when().body(payloadManager.updatePayload()).put();
        validatableResponse = response.then().log().all();

        validatableResponse.body("firstname", Matchers.is("Lucky"));

    }

    @Test(groups = "integration", dependsOnMethods = "testCreateBooking")
    @Owner("Gaurav")
    @Description("Verify that the booking is deleted")
    public void testDeleteCreatedBooking(ITestContext iTestContext){
        String bookingId = (String) iTestContext.getAttribute("bookingid");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingId);
        validatableResponse = RestAssured.given().spec(requestSpecification).auth().preemptive().basic("admin","password123").
                when().delete().then().log().all();

        validatableResponse.statusCode(201);
    }
}
