package com.tjazi.profilesauthorizer.service.core;

import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseStatus;
import com.tjazi.profilesauthorizer.service.dao.ProfilesAuthorizerDAO;
import com.tjazi.profilesauthorizer.service.dao.model.ProfilesAuthorizerDAOModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@Service
public class TokenValidatorImpl implements TokenValidator {

    private final static Logger log = LoggerFactory.getLogger(TokenValidatorImpl.class);

    @Autowired
    private ProfilesAuthorizerDAO profilesAuthorizerDAO;

    @Override
    public ValidateAuthorizationTokenResponseMessage validateToken(ValidateAuthorizationTokenRequestMessage requestMessage) {

        if (requestMessage == null) {
            String errorMessage = "requestMessage is null";
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        final String authorizationToken = requestMessage.getAuthorizationToken();

        if (authorizationToken == null || authorizationToken.isEmpty()) {
            String errorMessage = "requestMessage.AuthorizationToken is null or empty";
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        UUID profileUuid = requestMessage.getProfileUuid();

        if (profileUuid == null) {
            String errorMessage = "requestMessage.ProfileUUID is null";

            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        List<ProfilesAuthorizerDAOModel> authorizationRecords = profilesAuthorizerDAO.findByUserProfileUuid(profileUuid);

        ValidateAuthorizationTokenResponseMessage responseMessage = new ValidateAuthorizationTokenResponseMessage();

        if (authorizationRecords == null || authorizationRecords.isEmpty()) {
            responseMessage.setResponseStatus(ValidateAuthorizationTokenResponseStatus.PROFILE_UUID_NOT_REGISTERED);
            return responseMessage;
        }

        boolean isAuthorizationTokenFoundForUser =
                authorizationRecords.stream()
                        .anyMatch(record -> record.getAuthorizationToken() == authorizationToken);

        if (!isAuthorizationTokenFoundForUser) {
            responseMessage.setResponseStatus(
                    ValidateAuthorizationTokenResponseStatus.AUTHORIZATION_TOKEN_NOT_VALID_FOR_PROFILE_UUID);

            return responseMessage;
        }

        responseMessage.setResponseStatus(ValidateAuthorizationTokenResponseStatus.OK);

        return responseMessage;
    }
}
