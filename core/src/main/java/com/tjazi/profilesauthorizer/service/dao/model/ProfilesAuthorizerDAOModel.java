package com.tjazi.profilesauthorizer.service.dao.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */
@Entity
@Table(name = "ProfilesAuthorizerData")
public class ProfilesAuthorizerDAOModel {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Profile UUID
     * There maybe more than one token per each profile ID, thus this field is not unique
     */
    @Column(name = "ProfileUUID", unique = false, nullable = false)
    private UUID userProfileUuid;

    /**
     * Authorization token per User Profile
     */
    @Column(name = "AuthorizationToken", unique = true, nullable = false)
    private String authorizationToken;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUserProfileUuid() {
        return userProfileUuid;
    }

    public void setUserProfileUuid(UUID userProfileUuid) {
        this.userProfileUuid = userProfileUuid;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }
}
