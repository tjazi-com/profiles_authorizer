package com.tjazi.profilesauthorizer.service.core;

import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseMessage;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@Service
public class TokenValidatorImpl implements TokenValidator {

    @Override
    public ValidateAuthorizationTokenResponseMessage validateToken(ValidateAuthorizationTokenRequestMessage requestMessage) {
        return null;
    }
}
