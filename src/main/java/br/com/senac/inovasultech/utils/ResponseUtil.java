package br.com.senac.inovasultech.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> parseResponse(Object message){
        Map<String, Object> response = new HashMap<>();
        response.put("messages", message);

        return response;
    }
}
