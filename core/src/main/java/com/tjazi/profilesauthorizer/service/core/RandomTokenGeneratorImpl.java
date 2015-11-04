package com.tjazi.profilesauthorizer.service.core;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@Service
public class RandomTokenGeneratorImpl implements RandomTokenGenerator {

    @Override
    public String generateAuthorizationToken() {
        return UUID.randomUUID().toString();
    }
}
