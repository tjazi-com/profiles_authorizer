package com.tjazi.profilesauthorizer.service.controller;

import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.service.core.TokenCreator;
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
public class ProfilesAuthorizerTokenCreatorController {

    private final static Logger log = LoggerFactory.getLogger(ProfilesAuthorizerTokenCreatorController.class);

    @Autowired
    private TokenCreator tokenCreator;

    @RequestMapping(value = "/createtoken", method = RequestMethod.POST)
    public CreateNewAuthorizationTokenResponseMessage createNewAuthorizationToken(
            @RequestBody CreateNewAuthorizationTokenRequestMessage createNewAuthorizationTokenMessage
    ) {
        if (createNewAuthorizationTokenMessage == null) {
            String errorMessage = "createNewAuthorizationTokenMessage is null.";
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return tokenCreator.createNewToken(createNewAuthorizationTokenMessage);
    }
}
