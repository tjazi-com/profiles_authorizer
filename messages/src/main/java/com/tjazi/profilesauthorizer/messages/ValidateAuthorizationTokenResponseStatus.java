package com.tjazi.profilesauthorizer.messages;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */
public enum  ValidateAuthorizationTokenResponseStatus {

    /**
     * Default value. Result has not been set.
     */
    UNKNOWN,

    /**
     * Authorization token is valid for the give profile UUID
     */
    OK,

    /**
     * Profile UUID is not registered
     */
    PROFILE_UUID_NOT_REGISTERED,

    /**
     * Profile UUID us registered, but authorization token is not valid for that UUID
     */
    AUTHORIZATION_TOKEN_NOT_VALID_FOR_PROFILE_UUID
}
