package core;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiManager {

    private RequestSpecification baseRequestSpec;

    public RequestSpecification getBaseRequestSpec(){
        if(baseRequestSpec != null){
            baseRequestSpec = buildBaseRequestSpec();
        }
        return baseRequestSpec;
    }

    public RequestSpecification buildBaseRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept", ContentType.JSON.toString())
                .build();
    }

    public RequestSpecification newRequest(){
        return RestAssured.given()
                .spec(getBaseRequestSpec());
    }

    public void rest(){
        RestAssured.reset();
        baseRequestSpec = null;
    }
}
