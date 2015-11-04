package com.tjazi.profilesauthorizer.messages;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */
public class ValidateAuthorizationTokenResponseMessage {

    private ValidateAuthorizationTokenResponseStatus responseStatus;

    public ValidateAuthorizationTokenResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ValidateAuthorizationTokenResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
