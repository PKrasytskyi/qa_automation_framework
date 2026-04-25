package core;


import api.filters.ApiCaptureFilter;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class ApiManager {

    private RequestSpecification baseRequestSpec;
    private RequestSpecification authorizedRequestSpec;
    private RequestSpecification unAuthorizedRequestSpec;

    public RequestSpecification getBaseRequestSpec(){
        if(baseRequestSpec == null){
            baseRequestSpec = buildBaseRequestSpec();
        }
        return baseRequestSpec;
    }

    public RequestSpecification getAuthorizedRequestSpec(){
        if(authorizedRequestSpec == null){
            authorizedRequestSpec = buildAuthorizedRequestSpec();
        }
        return authorizedRequestSpec;
    }

    public RequestSpecification getUnAuthorizedRequestSpec(){
        if(unAuthorizedRequestSpec == null){
            unAuthorizedRequestSpec = buildUnAuthorizedRequestSpec();
        }
        return unAuthorizedRequestSpec;
    }

    public RequestSpecification newRequest(){
        return RestAssured.given()
                .spec(getBaseRequestSpec());
    }

    public RequestSpecification newUnAuthorizedRequest(){
        return RestAssured.given()
                .spec(getUnAuthorizedRequestSpec());
    }

    public RequestSpecification newAuthorizedRequest(){
        return RestAssured.given()
                .spec(getAuthorizedRequestSpec());
    }

    private RequestSpecification buildBaseRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept", ContentType.JSON.toString())
                .addFilter(new ApiCaptureFilter())
                .build();
    }

    private RequestSpecification buildUnAuthorizedRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getTokenApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept", ContentType.JSON.toString())
                .addFilter(new ApiCaptureFilter())
                .build();
    }

    private RequestSpecification buildAuthorizedRequestSpec(){
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getTokenApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept", ContentType.JSON.toString())
                .addFilter(new ApiCaptureFilter());


        String token = ConfigReader.getApiToken();
        if(token != null && !token.isBlank()){
            builder.addHeader("Authorization", "Bearer " + token.trim());
        }

        String apiKey = ConfigReader.getApiKey();
        if(apiKey != null && !apiKey.isBlank()){
            builder.addHeader("X-API-KEY", apiKey.trim());
        }

        return builder.build();
    }

    public void reset(){
        RestAssured.reset();
        baseRequestSpec = null;
        authorizedRequestSpec = null;
    }
}
