package api.filters;

import api.logging.ApiCallLog;
import api.logging.ApiCallLogStore;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.http.Headers;
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
                        maskHeaders(requestSpec.getHeaders()),
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
                        maskHeaders(response.getHeaders()),
                        body
        );
    }

    private String maskHeaders(Headers headers){

        StringBuilder str = new StringBuilder();

        for(Header header : headers){
            String name = header.getName();
            String value = header.getValue();

            if("Authorization".equalsIgnoreCase(name)){
                value = maskAuthorization(value);
            } else if ("X-API-KEY".equalsIgnoreCase(name)) {
                value = maskSecret(value);
            }

            str.append(name)
                    .append("=")
                    .append(value)
                    .append(System.lineSeparator());
        }

        return str.toString().trim();
    }

    private String maskAuthorization(String value){
        if(value == null || value.isBlank()){
            return "";
        }

        if(value.startsWith("Bearer ")){
            return "Bearer ***masked***";
        }

        return maskSecret(value);
    }

    private String maskSecret(String value){
        if(value == null || value.isBlank()){
            return "";
        }

        if (value.length() <= 6){
            return "***";
        }

        return value.substring(0,3) + "***" + value.substring(value.length() - 3);
    }
}
