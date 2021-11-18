package se.iths.util;

import org.json.simple.JSONObject;

public class Mapper {

    public JSONObject jsonMapper(int statusCode, String message) {
        JSONObject json = new JSONObject();
        json.put("httpStatusCode", statusCode);
        json.put("message", message);
        return json;
    }
}