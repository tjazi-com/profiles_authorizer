package unittests;

import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.CreateNewAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenRequestMessage;
import com.tjazi.profilesauthorizer.messages.ValidateAuthorizationTokenResponseMessage;
import com.tjazi.profilesauthorizer.service.controller.ProfilesAuthorizerTokenCreatorController;
import com.tjazi.profilesauthorizer.service.controller.ProfilesAuthorizerTokenValidatorController;
import com.tjazi.profilesauthorizer.service.core.TokenCreator;
import com.tjazi.profilesauthorizer.service.core.TokenValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Krzysztof Wasiak on 05/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProfilesAuthorizerTokenValidatorController_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public ProfilesAuthorizerTokenValidatorController controller;

    @Mock
    public TokenValidator tokenValidator;

    @Test
    public void createNewAuthorizationToken_ExceptionOnNullInputException_Test() {

        thrown.expect(IllegalArgumentException.class);

        controller.validateAuthorizationToken(null);
    }

    @Test
    public void createNewAuthorizationToken_CallTokenCreator_PassResponse_Test() {

        ValidateAuthorizationTokenRequestMessage expectedRequestMessage = new ValidateAuthorizationTokenRequestMessage();
        ValidateAuthorizationTokenResponseMessage expectedResponseMessage = new ValidateAuthorizationTokenResponseMessage();

        when(tokenValidator.validateToken(expectedRequestMessage))
                .thenReturn(expectedResponseMessage);

        ValidateAuthorizationTokenResponseMessage actualResponseMessage =
                controller.validateAuthorizationToken(expectedRequestMessage);


        ArgumentCaptor<ValidateAuthorizationTokenRequestMessage> captor =
                ArgumentCaptor.forClass(ValidateAuthorizationTokenRequestMessage.class);

        verify(tokenValidator, times(1))
                .validateToken(captor.capture());

        assertEquals(expectedRequestMessage, captor.getValue());
        assertEquals(expectedResponseMessage, actualResponseMessage);
    }
}
