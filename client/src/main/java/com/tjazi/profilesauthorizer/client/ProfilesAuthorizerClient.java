package com.tjazi.profilesauthorizer.client;

import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseMessage;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 07/11/2015.
 */
public interface ProfilesAuthorizerClient {

    /**
     * Create new authorization token for the given profile UUID
     * @param profileUuid Profile UUID
     * @return
     */
    CreateNewAuthorizationTokenResponseMessage createNewAuthorizationToken(UUID profileUuid);

    /**
     * Validate given authorization token for the given profile UUID
     * @param profileUuid Profile UUID
     * @param authorizationToken Authorization token
     * @return
     */
    ValidateAuthorizationTokenResponseMessage validateAuthorizationToken(UUID profileUuid, String authorizationToken);
}
