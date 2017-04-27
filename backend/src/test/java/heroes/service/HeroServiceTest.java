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
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import heroes.exception.HeroNotFoundException;
import heroes.model.Hero;
import heroes.utils.TestConfig;

import static heroes.utils.HeroDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:application.properties")
@DatabaseSetup("/dataset/default.xml")
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})

public class HeroServiceTest {
	@Autowired
	private HeroService heroService;
	
	@Test
	@ExpectedDatabase(value = "/dataset/default.xml", table = "hero")
	public void getHeroTest() throws HeroNotFoundException{
		Hero hero = getValidHero();
		Hero result = heroService.getHero(hero.getId());
		assertThat(hero.getId(), is(equalTo(result.getId())));
		assertThat(hero.getName(), is(equalTo(result.getName())));
	}
	
	@Test(expected = HeroNotFoundException.class)
	public void getHeroHeroNotFoundExceptionTest() throws HeroNotFoundException{
		heroService.getHero(getNonExistentHeroId());
	}
	
	@Test
	@ExpectedDatabase(value = "/dataset/default.xml", table = "hero")
	public void getHeroesTest(){
		assertThat(heroService.getHeroes(), containsInAnyOrder(getHeroes()));
	}
	
	@Test
	@ExpectedDatabase(value = "/dataset/update-hero.xml", table = "hero")
	public void updateHeroTest() throws HeroNotFoundException{
		Hero hero = heroService.getHero(getValidHeroId());
		hero.setName(getAnotherValidHeroName());
		Hero heroUpdated = heroService.updateHero(hero);
		assertThat(heroUpdated, is(equalTo(hero)));
	}
	
	@Test(expected = HeroNotFoundException.class)
	@ExpectedDatabase(value = "/dataset/default.xml", table = "hero")
	public void updateHeroHeroNotFoundExceptionTest() throws HeroNotFoundException{
		Hero hero = new Hero(getNonExistentHeroId(), getValidHeroName());
		heroService.updateHero(hero);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@ExpectedDatabase(value = "/dataset/default.xml", table = "hero")
	public void updateNullHeroIllegalArgumentExceptionTest() throws HeroNotFoundException{
		heroService.updateHero(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@ExpectedDatabase(value = "/dataset/default.xml", table = "hero")
	public void updateNullNameHeroIllegalArgumentExceptionTest() throws HeroNotFoundException{
		heroService.updateHero(new Hero());
	}
	
	@Test
	@ExpectedDatabase(value = "/dataset/add-hero.xml", table = "hero")
	public void addHeroTest(){
		Hero hero = new Hero();
		hero.setName(getAnotherValidHeroName());
		Hero hero2 = heroService.addHero(hero);
		assertThat(hero.getName(), is(equalTo(hero2.getName())));
		assertThat(hero2.getId(), is(greaterThan(hero.getId())));
	}
	
	@Test(expected = IllegalArgumentException.class)
	@ExpectedDatabase(value = "/dataset/default.xml", table = "hero")
	public void addNullHeroIllegalArgumentExceptionTest(){
		heroService.addHero(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@ExpectedDatabase(value = "/dataset/default.xml", table = "hero")
	public void addNullNameHeroIllegalArgumentExceptionTest(){
		heroService.addHero(new Hero());
	}
	
	@Test
	@ExpectedDatabase(value = "/dataset/delete-hero.xml", table = "hero")
	public void deleteHeroTest() throws HeroNotFoundException{
		heroService.deleteHero(getValidHeroId());
	}
	
	@Test(expected = HeroNotFoundException.class)
	@ExpectedDatabase(value = "/dataset/default.xml", table = "hero")
	public void deleteHeroHeroNotFoundException() throws HeroNotFoundException{
		heroService.deleteHero(getNonExistentHeroId());
	}
	
}
