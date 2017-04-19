package heroes.model;

import org.junit.Test;

import static heroes.utils.HeroDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class HeroTest {
	
	@Test
	public void emptyConstructorTest(){
		new Hero();
	}

	@Test
	public void constructorTest(){
		Hero hero = new Hero(getValidHeroId(), getValidHeroName());
		assertThat(hero.getId(), is(equalTo(getValidHeroId())));
		assertThat(hero.getName(), is(equalTo(getValidHeroName())));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWrongIdTest(){
		getValidHero().setId(getInvalidHeroId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWrongNameTest(){
		getValidHero().setName(getInvalidHeroName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setNullNameTest(){
		getValidHero().setName(null);
	}
}
