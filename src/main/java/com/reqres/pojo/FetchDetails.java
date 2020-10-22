package com.reqres.pojo;

public class FetchDetails {

private static int statusCode;

    public static int getStatusCode() {
        return statusCode;
    }

    public static void setStatusCode(int statusCode) {
        FetchDetails.statusCode = statusCode;
    }
}
