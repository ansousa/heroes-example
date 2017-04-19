package heroes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import heroes.model.HeroTest;
import heroes.model.ImageFromViewTest;
import heroes.model.ImageTest;
import heroes.model.LoginTest;
import heroes.model.LoginTokenTest;

@RunWith(Suite.class)
@SuiteClasses({
	HeroTest.class,
	ImageTest.class,
	ImageFromViewTest.class,
	LoginTest.class,
	LoginTokenTest.class
})
public class UnitTestSuite {

}
