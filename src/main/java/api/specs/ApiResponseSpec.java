package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.hasItems;


public class ApiResponseSpec {

    private ApiResponseSpec(){}

    public static ResponseSpecification statusCode200Js(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification statusCode201Js(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification statusCode404Js(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification statusCode401Js(){
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification statusCode422ForBlankPatchFields(){
        return new ResponseSpecBuilder()
                .expectStatusCode(422)
                .expectContentType(ContentType.JSON)
                .expectBody("field", hasItems("email", "gender", "status"))
                .expectBody("message", hasItems(
                        "can't be blank",
                        "can't be blank, can be male of female",
                        "can't be blank"
                ))
                .build();
    }
}
