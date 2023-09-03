package org.example.endpoints;


public class APIConstants {

    public static String BASE_URL = "https://restful-booker.herokuapp.com/";
    public static String CREATE_TOKEN_AUTH_URL = "auth";

    public static String CREATE_UPDATE_BOOKING_URL = "booking";

    // stage - url 1 stage_td.xlsx
    // preprod - url 2 preprod_td.xlsx
    // prod - url 3 prod_td.xlsx

    // we can fetch data from these files
    // Excel, CSV, json, txt, properties, YAML
    // public String BASE_URL = FillowUtils.fetchDataFromXLSX("Sheet1","BaseUrl","Value");
}
