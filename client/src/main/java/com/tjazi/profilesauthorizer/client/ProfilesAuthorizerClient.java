package com.tjazi.profilesauthorizer.client;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 07/11/2015.
 */
public interface ProfilesAuthorizerClient {

    /**
     * Create new authorization token for the given profile UUID
     * @param profileUuid Profile UUID
     * @param authorizationToken Authorization token
     * @return
     */
    boolean saveAuthorizationToken(UUID profileUuid, String authorizationToken);
}
