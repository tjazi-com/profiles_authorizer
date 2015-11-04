package com.tjazi.profilesauthorizer.service.core;

import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseStatus;
import com.tjazi.profilesauthorizer.service.dao.ProfilesAuthorizerDAO;
import com.tjazi.profilesauthorizer.service.dao.model.ProfilesAuthorizerDAOModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@Service
public class TokenCreatorImpl implements TokenCreator {

    private final static Logger log = LoggerFactory.getLogger(TokenCreatorImpl.class);

    @Autowired
    private ProfilesAuthorizerDAO profilesAuthorizerDAO;

    @Autowired
    private RandomTokenGenerator randomTokenGenerator;

    @Override
    public CreateNewAuthorizationTokenResponseMessage createNewToken(CreateNewAuthorizationTokenRequestMessage requestMessage) {

        if (requestMessage == null) {
            String errorMessage = "requestMessage is null";
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        UUID profileUuid = requestMessage.getProfileUuid();

        if (profileUuid == null) {
            String errorMessage = "requestMessage.ProfileUUID is null.";
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        String authorizationToken = randomTokenGenerator.generateAuthorizationToken();

        ProfilesAuthorizerDAOModel profilesAuthorizerDAOModel = new ProfilesAuthorizerDAOModel();
        profilesAuthorizerDAOModel.setAuthorizationToken(authorizationToken);
        profilesAuthorizerDAOModel.setUserProfileUuid(profileUuid);

        profilesAuthorizerDAO.save(profilesAuthorizerDAOModel);

        CreateNewAuthorizationTokenResponseMessage responseMessage = new CreateNewAuthorizationTokenResponseMessage();
        responseMessage.setAuthorizationToken(authorizationToken);
        responseMessage.setResponseStatus(CreateNewAuthorizationTokenResponseStatus.OK);

        return responseMessage;
    }


}
