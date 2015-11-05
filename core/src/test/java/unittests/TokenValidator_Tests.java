package unittests;

import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseStatus;
import com.tjazi.profilesauthorizer.service.core.TokenValidator;
import com.tjazi.profilesauthorizer.service.core.TokenValidatorImpl;
import com.tjazi.profilesauthorizer.service.dao.ProfilesAuthorizerDAO;
import com.tjazi.profilesauthorizer.service.dao.model.ProfilesAuthorizerDAOModel;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 05/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class TokenValidator_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public TokenValidatorImpl tokenValidator;

    @Mock
    public ProfilesAuthorizerDAO profilesAuthorizerDAOMock;

    @Test
    public void validateToken_ExceptionOnNullRequestMessage_Test() {

        thrown.expect(IllegalArgumentException.class);

        tokenValidator.validateToken(null);
    }

    @Test
    public void validateToken_ExceptionOnAuthorizationTokenNull_Test() {
        thrown.expect(IllegalArgumentException.class);

        ValidateAuthorizationTokenRequestMessage inputMessage = new ValidateAuthorizationTokenRequestMessage();
        inputMessage.setProfileUuid(UUID.randomUUID());
        inputMessage.setAuthorizationToken(null);

        tokenValidator.validateToken(inputMessage);
    }

    @Test
    public void validateToken_ExceptionOnAuthorizationTokenEmpty_Test() {
        thrown.expect(IllegalArgumentException.class);

        ValidateAuthorizationTokenRequestMessage inputMessage = new ValidateAuthorizationTokenRequestMessage();
        inputMessage.setProfileUuid(UUID.randomUUID());
        inputMessage.setAuthorizationToken("");

        tokenValidator.validateToken(inputMessage);
    }

    @Test
    public void validateToken_ExceptionOnProfileUuidNull_Test() {
        thrown.expect(IllegalArgumentException.class);

        ValidateAuthorizationTokenRequestMessage inputMessage = new ValidateAuthorizationTokenRequestMessage();
        inputMessage.setProfileUuid(null);
        inputMessage.setAuthorizationToken(UUID.randomUUID().toString());

        tokenValidator.validateToken(inputMessage);
    }

    @Test
    public void validateToken_UserProfileUuidNotFound_Test() {

        final ValidateAuthorizationTokenResponseStatus expectedValidationStatus =
                ValidateAuthorizationTokenResponseStatus.PROFILE_UUID_NOT_REGISTERED;

        final String authorizationToken = UUID.randomUUID().toString();
        final UUID profileUuid = UUID.randomUUID();

        when(profilesAuthorizerDAOMock.findByUserProfileUuid(profileUuid))
                .thenReturn(Collections.<ProfilesAuthorizerDAOModel>emptyList());

        ValidateAuthorizationTokenRequestMessage requestMessage = new ValidateAuthorizationTokenRequestMessage();
        requestMessage.setAuthorizationToken(authorizationToken);
        requestMessage.setProfileUuid(profileUuid);

        // main call
        ValidateAuthorizationTokenResponseMessage responseMessage = tokenValidator.validateToken(requestMessage);

        // verification and assertion
        verify(profilesAuthorizerDAOMock, times(1)).findByUserProfileUuid(profileUuid);

        assertEquals(expectedValidationStatus, responseMessage.getResponseStatus());
    }

    @Test
    public void validateToken_AuthorizationTokenNotFound_Test() {

        final ValidateAuthorizationTokenResponseStatus expectedValidationStatus =
                ValidateAuthorizationTokenResponseStatus.AUTHORIZATION_TOKEN_NOT_VALID_FOR_PROFILE_UUID;

        final String authorizationToken = UUID.randomUUID().toString();
        final UUID profileUuid = UUID.randomUUID();

        ProfilesAuthorizerDAOModel storedAuthorizationRecord = new ProfilesAuthorizerDAOModel();
        storedAuthorizationRecord.setUserProfileUuid(profileUuid);
        storedAuthorizationRecord.setAuthorizationToken("fake authorization token");

        when(profilesAuthorizerDAOMock.findByUserProfileUuid(profileUuid))
                .thenReturn(Arrays.asList(storedAuthorizationRecord));

        ValidateAuthorizationTokenRequestMessage requestMessage = new ValidateAuthorizationTokenRequestMessage();
        requestMessage.setAuthorizationToken(authorizationToken);
        requestMessage.setProfileUuid(profileUuid);

        // main call
        ValidateAuthorizationTokenResponseMessage responseMessage = tokenValidator.validateToken(requestMessage);

        // verification and assertion
        verify(profilesAuthorizerDAOMock, times(1)).findByUserProfileUuid(profileUuid);

        assertEquals(expectedValidationStatus, responseMessage.getResponseStatus());
    }

    @Test
    public void validateToken_AuthorizationTokenFound_AuthorizationSuccess_Test() {

        final ValidateAuthorizationTokenResponseStatus expectedValidationStatus =
                ValidateAuthorizationTokenResponseStatus.OK;

        final String authorizationToken = UUID.randomUUID().toString();
        final UUID profileUuid = UUID.randomUUID();

        ProfilesAuthorizerDAOModel storedAuthorizationRecord = new ProfilesAuthorizerDAOModel();
        storedAuthorizationRecord.setUserProfileUuid(profileUuid);
        storedAuthorizationRecord.setAuthorizationToken(authorizationToken);

        when(profilesAuthorizerDAOMock.findByUserProfileUuid(profileUuid))
                .thenReturn(Arrays.asList(storedAuthorizationRecord));

        ValidateAuthorizationTokenRequestMessage requestMessage = new ValidateAuthorizationTokenRequestMessage();
        requestMessage.setAuthorizationToken(authorizationToken);
        requestMessage.setProfileUuid(profileUuid);

        // main call
        ValidateAuthorizationTokenResponseMessage responseMessage = tokenValidator.validateToken(requestMessage);

        // verification and assertion
        verify(profilesAuthorizerDAOMock, times(1)).findByUserProfileUuid(profileUuid);

        assertEquals(expectedValidationStatus, responseMessage.getResponseStatus());
    }
}
