package heroes.model;

import org.junit.Test;

import static heroes.utils.LoginTokenDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class LoginTokenTest {

	@Test
	public void emptyConstructorTest(){
		new LoginToken();
	}
	
	@Test
	public void constructorTest(){
		LoginToken token = new LoginToken(getValidLoginTokenToken());
		assertThat(token.getToken(), is(equalTo(getValidLoginTokenToken())));
	}
}
