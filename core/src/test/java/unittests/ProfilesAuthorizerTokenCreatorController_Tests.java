package unittests;

import com.tjazi.profilesauthorizer.service.controller.ProfilesAuthorizerTokenCreatorController;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

public class ProfilesAuthorizerTokenCreatorController_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createNewAuthorizationToken_ExceptionOnNullInput_Exception() {

        thrown.expect(IllegalArgumentException.class);

        ProfilesAuthorizerTokenCreatorController controller = new ProfilesAuthorizerTokenCreatorController();

        controller.createNewAuthorizationToken(null);
    }
}
