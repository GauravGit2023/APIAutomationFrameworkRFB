package org.example.tests.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.example.actions.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.module.PayloadManager;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    //this class will act as parent for other tests

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeMethod
    @BeforeSuite
    public void setUpConfig(){
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = (RequestSpecification) new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json").build().log().all();
    }

    public String getToken(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL).basePath(APIConstants.CREATE_TOKEN_AUTH_URL);

        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload).when().post();

        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");

    }

}
