package unittests;

import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.service.controller.ProfilesAuthorizerTokenCreatorController;
import com.tjazi.profilesauthorizer.service.core.TokenCreator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProfilesAuthorizerTokenCreatorController_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public ProfilesAuthorizerTokenCreatorController controller;

    @Mock
    public TokenCreator tokenCreator;

    @Test
    public void createNewAuthorizationToken_ExceptionOnNullInputException_Test() {

        thrown.expect(IllegalArgumentException.class);

        controller.createNewAuthorizationToken(null);
    }

    @Test
    public void createNewAuthorizationToken_CallTokenCreator_PassResponse_Test() {

        CreateNewAuthorizationTokenRequestMessage expectedRequestMessage = new CreateNewAuthorizationTokenRequestMessage();
        CreateNewAuthorizationTokenResponseMessage expectedResponseMessage = new CreateNewAuthorizationTokenResponseMessage();

        when(tokenCreator.createNewToken(expectedRequestMessage))
                .thenReturn(expectedResponseMessage);

        CreateNewAuthorizationTokenResponseMessage actualResponseMessage =
                controller.createNewAuthorizationToken(expectedRequestMessage);


        ArgumentCaptor<CreateNewAuthorizationTokenRequestMessage> captor =
                ArgumentCaptor.forClass(CreateNewAuthorizationTokenRequestMessage.class);

        verify(tokenCreator, times(1))
                .createNewToken(captor.capture());

        assertEquals(expectedRequestMessage, captor.getValue());
        assertEquals(expectedResponseMessage, actualResponseMessage);
    }
}
