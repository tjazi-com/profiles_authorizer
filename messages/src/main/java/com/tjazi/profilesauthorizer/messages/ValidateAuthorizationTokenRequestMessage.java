package com.tjazi.profilesauthorizer.messages;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

/**
 * Validate if given authorization token is correct for the given user profile UUID
 */
public class ValidateAuthorizationTokenRequestMessage {

    /**
     * User profile UUID.
     */
    private UUID profileUuid;

    /**
     * Authorization token, which is
     */
    private String authorizationToken;

    public UUID getProfileUuid() {
        return profileUuid;
    }

    public void setProfileUuid(UUID profileUuid) {
        this.profileUuid = profileUuid;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }
}
