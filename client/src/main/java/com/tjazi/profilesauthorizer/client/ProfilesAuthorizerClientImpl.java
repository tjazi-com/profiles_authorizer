package com.tjazi.profilesauthorizer.client;

import com.tjazi.profilesauthorizer.messages.SaveAuthorizationTokenRequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 07/11/2015.
 */
public class ProfilesAuthorizerClientImpl implements ProfilesAuthorizerClient {

    @Autowired
    private RestTemplate restTemplate;

    private final static Logger log = LoggerFactory.getLogger(ProfilesAuthorizerClientImpl.class);

    private final static String PROFILES_AUTHORIZER_SERVICE_NAME = "profiles-authorizer-service-core";

    private final static String TOKEN_CREATOR_PATH = "http://" + PROFILES_AUTHORIZER_SERVICE_NAME + "/profilesauthorizer/savetoken";

    @Override
    public boolean saveAuthorizationToken(UUID profileUuid, String authorizationToken) {

        log.debug("[ProfilesAuthorizerClient] Saving authorization token '{}' for profileUUID: '{}'",
                authorizationToken, profileUuid);

        if (profileUuid == null) {
            throw new IllegalArgumentException("profileUuid is null");
        }

        if (authorizationToken == null || authorizationToken.isEmpty()) {
            throw new IllegalArgumentException("authorizationToken is null or empty");
        }

        SaveAuthorizationTokenRequestMessage requestMessage = new SaveAuthorizationTokenRequestMessage();
        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setAuthorizationToken(authorizationToken);

        return  restTemplate.postForObject(
                TOKEN_CREATOR_PATH,
                requestMessage, boolean.class, (Object) null);
    }
}
