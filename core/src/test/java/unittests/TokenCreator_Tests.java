package unittests;

import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseStatus;
import com.tjazi.profilesauthorizer.service.core.RandomTokenGenerator;
import com.tjazi.profilesauthorizer.service.core.TokenCreatorImpl;
import com.tjazi.profilesauthorizer.service.dao.ProfilesAuthorizerDAO;
import com.tjazi.profilesauthorizer.service.dao.model.ProfilesAuthorizerDAOModel;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class TokenCreator_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public TokenCreatorImpl tokenCreator;

    @Mock
    public ProfilesAuthorizerDAO profilesAuthorizerDAO;

    @Mock
    public RandomTokenGenerator randomTokenGenerator;

    @Test
    public void createNewToken_ExceptionOnNullParameter_Test() {
        thrown.expect(IllegalArgumentException.class);

        tokenCreator.createNewToken(null);
    }

    @Test
    public void createNewToken_ExceptionOnNullProfileUuid_Test() {
        thrown.expect(IllegalArgumentException.class);

        CreateNewAuthorizationTokenRequestMessage requestMessage = new CreateNewAuthorizationTokenRequestMessage();
        requestMessage.setProfileUuid(null);

        tokenCreator.createNewToken(requestMessage);
    }

    @Test
    public void createNewToken_TokenGeneratesAndStored() {

        final String expectedAuthorizationToken = UUID.randomUUID().toString();
        final CreateNewAuthorizationTokenResponseStatus expectedReturnStatus =
                CreateNewAuthorizationTokenResponseStatus.OK;

        final UUID expectedProfileUuid = UUID.randomUUID();

        when(randomTokenGenerator.generateAuthorizationToken())
                .thenReturn(expectedAuthorizationToken);


        CreateNewAuthorizationTokenRequestMessage requestMessage = new CreateNewAuthorizationTokenRequestMessage();
        requestMessage.setProfileUuid(expectedProfileUuid);

        // main call
        CreateNewAuthorizationTokenResponseMessage createTokenResult = tokenCreator.createNewToken(requestMessage);

        // validation / assertion

        ArgumentCaptor<ProfilesAuthorizerDAOModel> argumentCaptor =
                ArgumentCaptor.forClass(ProfilesAuthorizerDAOModel.class);

        verify(profilesAuthorizerDAO, times(1))
                .save(argumentCaptor.capture());

        ProfilesAuthorizerDAOModel capturedArgument = argumentCaptor.getValue();

        assertEquals(expectedAuthorizationToken, capturedArgument.getAuthorizationToken());
        assertEquals(expectedProfileUuid, capturedArgument.getUserProfileUuid());

        assertEquals(expectedReturnStatus, createTokenResult.getResponseStatus());
        assertEquals(expectedAuthorizationToken, createTokenResult.getAuthorizationToken());
    }
}
