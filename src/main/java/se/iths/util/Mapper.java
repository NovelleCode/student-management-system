package se.iths.util;

import org.json.simple.JSONObject;

public class Mapper {

    public JSONObject jsonMapper(String errorCode, String message) {
        JSONObject json = new JSONObject();
        json.put("httpErrorCode", errorCode);
        json.put("errorMessage", message);
        return json;
    }
}