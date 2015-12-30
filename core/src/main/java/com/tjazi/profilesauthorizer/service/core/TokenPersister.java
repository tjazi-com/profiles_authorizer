package com.tjazi.profilesauthorizer.service.core;

import com.tjazi.profilesauthorizer.messages.SaveAuthorizationTokenRequestMessage;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */
public interface TokenPersister {
    boolean saveAuthorizationToken(SaveAuthorizationTokenRequestMessage requestMessage);
}
