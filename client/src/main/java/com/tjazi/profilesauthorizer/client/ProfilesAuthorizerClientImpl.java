package com.tjazi.profilesauthorizer.client;

import com.tjazi.lib.messaging.rest.RestClient;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 07/11/2015.
 */
public class ProfilesAuthorizerClientImpl implements ProfilesAuthorizerClient {

    private RestClient restClient;

    private final static Logger log = LoggerFactory.getLogger(ProfilesAuthorizerClientImpl.class);

    public ProfilesAuthorizerClientImpl(RestClient restClient) {

        if (restClient == null) {
            String errorMessage = "restClient is null";
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        this.restClient = restClient;
    }

    @Override
    public CreateNewAuthorizationTokenResponseMessage createNewAuthorizationToken(UUID profileUuid) {

        if (profileUuid == null) {
            throw new IllegalArgumentException("profileUuid is null");
        }

        CreateNewAuthorizationTokenRequestMessage requestMessage = new CreateNewAuthorizationTokenRequestMessage();
        requestMessage.setProfileUuid(profileUuid);

        return (CreateNewAuthorizationTokenResponseMessage) restClient.sendRequestGetResponse(
                requestMessage, CreateNewAuthorizationTokenResponseMessage.class);
    }

    @Override
    public ValidateAuthorizationTokenResponseMessage validateAuthorizationToken(UUID profileUuid, String authorizationToken) {

        if (profileUuid == null) {
            throw new IllegalArgumentException("profileUuid is null");
        }

        if (authorizationToken == null || authorizationToken.isEmpty()) {
            throw new IllegalArgumentException("authorizationToken is null or empty");
        }

        ValidateAuthorizationTokenRequestMessage requestMessage = new ValidateAuthorizationTokenRequestMessage();
        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setAuthorizationToken(authorizationToken);

        return (ValidateAuthorizationTokenResponseMessage) restClient.sendRequestGetResponse(
                requestMessage, ValidateAuthorizationTokenResponseMessage.class);
    }
}
