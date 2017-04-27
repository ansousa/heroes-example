package heroes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import heroes.service.HeroServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	HeroServiceTest.class
})
public class IntegrationTestSuite {

}
