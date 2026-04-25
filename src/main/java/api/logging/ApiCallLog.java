package api.logging;

public record ApiCallLog (

        String name,
        String request,
        String response
)   {
}
