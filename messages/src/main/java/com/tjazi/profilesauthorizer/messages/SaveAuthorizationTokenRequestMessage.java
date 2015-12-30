package com.tjazi.profilesauthorizer.messages;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

import java.util.UUID;

/**
 * Create new authorization toke for the given profile UUID
 */
public class SaveAuthorizationTokenRequestMessage {

    private UUID profileUuid;
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
