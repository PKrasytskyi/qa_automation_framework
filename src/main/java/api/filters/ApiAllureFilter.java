package api.filters;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;


public class ApiAllureFilter implements Filter {

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext context
    ) {

        Allure.addAttachment("API Request", formatRequest(requestSpec));

        Response response = context.next(requestSpec, responseSpec);

        Allure.addAttachment("API Response", formatResponse(response));

        return response;
    }

    private String formatRequest(FilterableRequestSpecification requestSpec){
        String body = requestSpec.getBody() == null ? "" : requestSpec.getBody();

        return """
                
                Method: %s
                URI: %s
                Headers: %s
                
                Body:
                %s
                """.formatted(
                        requestSpec.getMethod(),
                        requestSpec.getURI(),
                        requestSpec.getHeaders(),
                        body
        );
     }

     private String formatResponse(Response response){

        String body = response.getBody() == null ? "" : response.getBody().asPrettyString();

        return """
                
                Status code: %s
                Status line: %s
                Headers: %s
                
                Body:
                %s
                """.formatted(
                    response.getStatusCode(),
                    response.getStatusLine(),
                    response.getHeaders(),
                    body
        );
     }
}
