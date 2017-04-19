package heroes.model;

import org.junit.Test;

import static heroes.utils.LoginDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {
	
	@Test
	public void emptyConstructorTest(){
		new Login();
	}
	
	@Test
	public void constructorTest(){
		Login login = new Login(getValidLoginName(), getValidLoginPassword());
		assertThat(login.getName(), is(equalTo(getValidLoginName())));
		assertThat(login.getPassword(), is(equalTo(getValidLoginPassword())));
	}
	
	@Test
	public void equalsTest(){
		Login login = new Login(getValidLoginName(), getValidLoginPassword());
		assertThat(login.equals(getValidLogin()), is(equalTo(true)));
		assertThat(login.equals(null), is(equalTo(false)));
		assertThat(login.equals("asd"), is(equalTo(false)));
		login.setName(null);
		assertThat(login.equals(getValidLogin()), is(equalTo(false)));
		login.setName(getValidLoginName());
		login.setPassword(null);
		assertThat(login.equals(getValidLogin()), is(equalTo(false)));
		login.setPassword(getValidLoginPassword());
		login.setName(getDifferentValidLoginName());
		assertThat(login.equals(getValidLogin()), is(equalTo(false)));
		login.setName(getValidLoginName());
		login.setPassword(getDifferentValidLoginPassword());
		assertThat(login.equals(getValidLogin()), is(equalTo(false)));
	}

}
