package com.tjazi.profilesauthorizer.messages;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */
public class CreateNewAuthorizationTokenResponseMessage {

    private CreateNewAuthorizationTokenResponseStatus responseStatus;

    private String authorizationToken;

    public CreateNewAuthorizationTokenResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(CreateNewAuthorizationTokenResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }
}
