package com.tjazi.profilesauthorizer.service.core;

import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseMessage;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */
public interface TokenCreator {
    CreateNewAuthorizationTokenResponseMessage createNewToken(CreateNewAuthorizationTokenRequestMessage requestMessage);
}
