package unittests;

import com.tjazi.profilesauthorizer.messages.SaveAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.service.controller.ProfilesAuthorizerTokenPersisterController;
import com.tjazi.profilesauthorizer.service.core.TokenPersister;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProfilesAuthorizerTokenCreatorController_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public ProfilesAuthorizerTokenPersisterController controller;

    @Mock
    public TokenPersister tokenCreator;

    @Test
    public void createNewAuthorizationToken_ExceptionOnNullInputException_Test() {

        thrown.expect(IllegalArgumentException.class);

        controller.createNewAuthorizationToken(null);
    }

    @Test
    public void createNewAuthorizationToken_CallTokenCreator_PassResponse_Test() {

        SaveAuthorizationTokenRequestMessage expectedRequestMessage = new SaveAuthorizationTokenRequestMessage();

        when(tokenCreator.saveAuthorizationToken(expectedRequestMessage))
                .thenReturn(true);

        // main call
        boolean actualResponse = controller.createNewAuthorizationToken(expectedRequestMessage);

        ArgumentCaptor<SaveAuthorizationTokenRequestMessage> captor =
                ArgumentCaptor.forClass(SaveAuthorizationTokenRequestMessage.class);

        verify(tokenCreator, times(1))
                .saveAuthorizationToken(captor.capture());

        assertEquals(expectedRequestMessage, captor.getValue());
        assertEquals(true, actualResponse);
    }
}
