package com.tjazi.profilesauthorizer.service.core;

import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseMessage;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

public interface TokenValidator {

    ValidateAuthorizationTokenResponseMessage validateToken(ValidateAuthorizationTokenRequestMessage requestMessage);
}
