package com.tjazi.profilesauthorizer.service.controller;

import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.service.core.TokenValidator;
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
@RequestMapping(value = "${profilesauthorizer.mvc.controller.root.path}")
public class ProfilesAuthorizerTokenValidatorController {

    private final static Logger log = LoggerFactory.getLogger(ProfilesAuthorizerTokenValidatorController.class);

    @Autowired
    private TokenValidator tokenValidator;

    @RequestMapping(value = "${profilesauthorizer.mvc.controller.validatetoken.path}", method = RequestMethod.POST)
    public ValidateAuthorizationTokenResponseMessage validateAuthorizationToken(
            @RequestBody ValidateAuthorizationTokenRequestMessage requestMessage) {

        if (requestMessage == null) {
            String errorMessage = "requestMessage is null";

            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return tokenValidator.validateToken(requestMessage);
    }
}
