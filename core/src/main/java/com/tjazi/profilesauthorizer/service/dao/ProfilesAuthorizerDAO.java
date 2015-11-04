package com.tjazi.profilesauthorizer.service.dao;

import com.tjazi.profilesauthorizer.service.dao.model.ProfilesAuthorizerDAOModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */
public interface ProfilesAuthorizerDAO extends CrudRepository<ProfilesAuthorizerDAOModel, Long> {

    List<ProfilesAuthorizerDAOModel> findByUserProfileUuid(UUID userProfileUuid);
}
