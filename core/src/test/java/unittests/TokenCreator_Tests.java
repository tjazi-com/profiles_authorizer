package unittests;

import com.tjazi.profilesauthorizer.messages.SaveAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.service.core.TokenPersisterImpl;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class TokenCreator_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public TokenPersisterImpl tokenCreator;

    @Mock
    public ProfilesAuthorizerDAO profilesAuthorizerDAO;

    @Test
    public void createNewToken_ExceptionOnNullParameter_Test() {
        thrown.expect(IllegalArgumentException.class);

        tokenCreator.saveAuthorizationToken(null);
    }

    @Test
    public void createNewToken_ExceptionOnNullProfileUuid_Test() {
        thrown.expect(IllegalArgumentException.class);

        SaveAuthorizationTokenRequestMessage requestMessage = new SaveAuthorizationTokenRequestMessage();
        requestMessage.setProfileUuid(null);
        requestMessage.setAuthorizationToken("Sample authorization token");

        tokenCreator.saveAuthorizationToken(requestMessage);
    }

    @Test
    public void createNewToken_ExceptionOnNullEmptyAuthorizationToken_Test() {
        thrown.expect(IllegalArgumentException.class);

        SaveAuthorizationTokenRequestMessage requestMessage = new SaveAuthorizationTokenRequestMessage();
        requestMessage.setProfileUuid(UUID.randomUUID());
        requestMessage.setAuthorizationToken("");

        tokenCreator.saveAuthorizationToken(requestMessage);
    }

    @Test
    public void createNewToken_TokenGeneratesAndStored() {

        final String expectedAuthorizationToken = UUID.randomUUID().toString();
        final UUID expectedProfileUuid = UUID.randomUUID();

        SaveAuthorizationTokenRequestMessage requestMessage = new SaveAuthorizationTokenRequestMessage();
        requestMessage.setProfileUuid(expectedProfileUuid);
        requestMessage.setAuthorizationToken(expectedAuthorizationToken);

        // main call
        boolean saveTokenResult = tokenCreator.saveAuthorizationToken(requestMessage);

        // validation / assertion

        ArgumentCaptor<ProfilesAuthorizerDAOModel> argumentCaptor =
                ArgumentCaptor.forClass(ProfilesAuthorizerDAOModel.class);

        verify(profilesAuthorizerDAO, times(1))
                .save(argumentCaptor.capture());

        ProfilesAuthorizerDAOModel capturedArgument = argumentCaptor.getValue();

        assertEquals(expectedAuthorizationToken, capturedArgument.getAuthorizationToken());
        assertEquals(expectedProfileUuid, capturedArgument.getUserProfileUuid());

        assertEquals(true, saveTokenResult);
    }
}
