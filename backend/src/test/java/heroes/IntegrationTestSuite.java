package heroes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import heroes.service.HeroServiceTest;
import heroes.service.ImageServiceTest;
import heroes.service.LoginServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	HeroServiceTest.class,
	ImageServiceTest.class,
	LoginServiceTest.class
})
public class IntegrationTestSuite {

}
