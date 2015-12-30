package com.tjazi.profilesauthorizer.service.core;

import com.tjazi.profilesauthorizer.messages.SaveAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.service.dao.ProfilesAuthorizerDAO;
import com.tjazi.profilesauthorizer.service.dao.model.ProfilesAuthorizerDAOModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@Service
public class TokenPersisterImpl implements TokenPersister {

    private final static Logger log = LoggerFactory.getLogger(TokenPersisterImpl.class);

    @Autowired
    private ProfilesAuthorizerDAO profilesAuthorizerDAO;

    @Override
    public boolean saveAuthorizationToken(SaveAuthorizationTokenRequestMessage requestMessage) {

        this.assertRequestMessage(requestMessage);

        ProfilesAuthorizerDAOModel profilesAuthorizerDAOModel = new ProfilesAuthorizerDAOModel();
        profilesAuthorizerDAOModel.setAuthorizationToken(requestMessage.getAuthorizationToken());
        profilesAuthorizerDAOModel.setUserProfileUuid(requestMessage.getProfileUuid());

        profilesAuthorizerDAO.save(profilesAuthorizerDAOModel);

        // no exceptions, so all seems to be fine
        return true;
    }

    private void assertRequestMessage(SaveAuthorizationTokenRequestMessage requestMessage) {

        if (requestMessage == null) {
            String errorMessage = "requestMessage is null";
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        if (requestMessage.getProfileUuid() == null) {
            String errorMessage = "requestMessage.ProfileUUID is null.";
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        if (requestMessage.getAuthorizationToken() == null || requestMessage.getAuthorizationToken().isEmpty()) {
            String errorMessage = "requestMessage.AuthorizationToken is null or empty";
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }
    }


}
