package heroes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import heroes.model.Login;
import heroes.utils.TestConfig;

import static heroes.utils.LoginDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:application.properties")
@DatabaseSetup("/dataset/default.xml")
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class LoginServiceTest {
	
	@Autowired
	private LoginService loginService;
	
	@Test
	public void existsTests(){
		assertThat(loginService.exists(getValidLogin()), is(equalTo(true)));
	}
	
	@Test
	public void existsFalseTest(){
		assertThat(loginService.exists(new Login(getDifferentValidLoginName(), getDifferentValidLoginPassword())), is(equalTo(false)));
	}
	
	@Test
	public void existsWrongTest(){
		assertThat(loginService.exists(new Login(getValidLoginName(), getDifferentValidLoginPassword())), is(equalTo(false)));
	}

}
