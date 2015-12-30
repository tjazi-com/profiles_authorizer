package com.tjazi.profilesauthorizer.service.controller;

import com.tjazi.profilesauthorizer.messages.SaveAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.service.core.TokenPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@RestController
@RequestMapping(value = "/profilesauthorizer")
public class ProfilesAuthorizerTokenPersisterController {

    private final static Logger log = LoggerFactory.getLogger(ProfilesAuthorizerTokenPersisterController.class);

    @Autowired
    private TokenPersister tokenPersister;

    @RequestMapping(value = "/savetoken", method = RequestMethod.POST)
    public boolean createNewAuthorizationToken(
            @RequestBody SaveAuthorizationTokenRequestMessage requestMessage
    ) {
        if (requestMessage == null) {
            String errorMessage = "requestMessage is null.";
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return tokenPersister.saveAuthorizationToken(requestMessage);
    }
}
