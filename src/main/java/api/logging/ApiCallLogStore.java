package api.logging;

import java.util.ArrayList;
import java.util.List;

public final class ApiCallLogStore {

    private static final ThreadLocal<List<ApiCallLog>> CALLS = ThreadLocal.withInitial(ArrayList::new);

    private ApiCallLogStore(){}

    public static void add(ApiCallLog callLog){
        CALLS.get().add(callLog);
    }

    public static List<ApiCallLog> getAll(){
        return new ArrayList<>(CALLS.get());
    }

    public static void clean(){
        CALLS.remove();
    }

    public static boolean isEmpty(){
        return CALLS.get().isEmpty();
    }
}
