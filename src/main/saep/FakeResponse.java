package main.saep;

/**
 * Created by Rony on 11/10/2016.
 */
public class FakeResponse {
    public static String make(String message, String args, String uri){
        return "{\"message\":\"" + message + "\",\"args\":\""+ args +"\",\"uri\":\"" + uri + "\"}";
    }
}
