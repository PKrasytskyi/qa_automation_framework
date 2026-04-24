package api.filters;

import api.logging.ApiCallLog;
import api.logging.ApiCallLogStore;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ApiCaptureFilter implements Filter {

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext context
    ){
        String requestText = formatRequest(requestSpec);

        Response response = context.next(requestSpec, responseSpec);

        String responseText = formatResponse(response);
        String name = requestSpec.getMethod() + " " + requestSpec.getURI();

        ApiCallLogStore.add(new ApiCallLog(name, requestText, responseText));

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
