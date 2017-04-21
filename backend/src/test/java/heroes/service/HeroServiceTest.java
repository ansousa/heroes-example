package heroes.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import heroes.exception.HeroNotFoundException;
import heroes.model.Hero;
import heroes.repository.HeroRepository;

import static heroes.utils.HeroDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HeroServiceTest {
	@Configuration
	static class HeroServiceTestContextConfiguration{
		@Bean
		public HeroService heroService(){
			return new HeroServiceImpl();
		}
		@Bean
		public HeroRepository heroRepository(){
			return Mockito.mock(HeroRepository.class);
		}
	}
	@Autowired
	private HeroService heroService;
	@Autowired
	private HeroRepository heroRepository;
	
	private int lastId;
	private List<Hero> heroes;
	
	@Before
	public void setup(){
		heroes = new ArrayList<Hero>();
		lastId = 0;
		for(Hero hero: getHeroes()){
			lastId = lastId >= hero.getId() ? lastId : hero.getId();
			heroes.add(new Hero(hero.getId(), new String(hero.getName())));
		}
		Mockito.when(heroRepository.findAll()).thenReturn(heroes);
		Mockito.when(heroRepository.findOne(Mockito.anyInt())).thenAnswer(new Answer<Hero>() {

			@Override
			public Hero answer(InvocationOnMock invocation) throws Throwable {
				int id = (Integer)invocation.getArguments()[0];
				for(Hero hero: heroes){
					if(hero.getId() == id)
						return new Hero(hero.getId(), hero.getName());
				}
				return null;
			}
		});
		Mockito.when(heroRepository.save(Mockito.any(Hero.class))).thenAnswer(new Answer<Hero>() {

			@Override
			public Hero answer(InvocationOnMock invocation) throws Throwable {
				Hero saveHero = (Hero)invocation.getArguments()[0];
				if(saveHero != null){					
					if(saveHero.getId() == 0){
						heroes.add(new Hero(++lastId, new String(saveHero.getName())));
						return new Hero(lastId, new String(saveHero.getName()));
					}
					for(Hero hero: heroes){
						if(hero.getId() == saveHero.getId()){
							hero.setName(new String(saveHero.getName()));
							return new Hero(hero.getId(), new String(saveHero.getName()));
						}
					}
				}
				return null;
			}
		});
		Mockito.when(heroRepository.exists(Mockito.anyInt())).thenAnswer(new Answer<Boolean>(){

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				int id = (Integer)invocation.getArguments()[0];
				for(Hero hero: heroes){
					if(hero.getId() == id)
						return true;
				}
				return false;
			}
			
		});
		Mockito.doAnswer(new Answer<Void>(){

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				int id = (Integer)invocation.getArguments()[0];
				for(Hero hero: heroes){
					if(hero.getId() == id){
						heroes.remove(hero);
						break;
					}
				}
				return null;
			}
			
		}).when(heroRepository).delete(Mockito.anyInt());
	}
	
	@Test
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
	public void getHeroesTest(){
		assertThat(heroService.getHeroes(), containsInAnyOrder(getHeroes()));
	}
	
	@Test
	public void updateHeroTest() throws HeroNotFoundException{
		Hero hero = heroService.getHero(getValidHeroId());
		Hero heroClone = new Hero(hero.getId(), new String(hero.getName()));
		int initialHeroLength = heroService.getHeroes().size();
		hero.setName(getAnotherValidHeroName());
		Hero heroUpdated = heroService.updateHero(hero);
		assertThat(heroUpdated, is(equalTo(hero)));
		assertThat(heroService.getHeroes(), hasItem(heroUpdated));
		assertThat(heroService.getHeroes(), not(hasItem(heroClone)));
		assertThat(initialHeroLength, is(equalTo(heroService.getHeroes().size())));
	}
	
	@Test(expected = HeroNotFoundException.class)
	public void updateHeroHeroNotFoundExceptionTest() throws HeroNotFoundException{
		Hero hero = new Hero(getNonExistentHeroId(), getValidHeroName());
		heroService.updateHero(hero);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateNullHeroIllegalArgumentExceptionTest() throws HeroNotFoundException{
		heroService.updateHero(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateNullNameHeroIllegalArgumentExceptionTest() throws HeroNotFoundException{
		heroService.updateHero(new Hero());
	}
	
	@Test
	public void addHeroTest(){
		Hero hero = new Hero();
		int initialHeroLength = heroService.getHeroes().size();
		hero.setName(getAnotherValidHeroName());
		hero = heroService.addHero(hero);
		assertThat(heroService.getHeroes(), hasItem(hero));
		assertThat(initialHeroLength, is(equalTo(heroService.getHeroes().size() - 1)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addNullHeroIllegalArgumentExceptionTest(){
		heroService.addHero(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addNullNameHeroIllegalArgumentExceptionTest(){
		heroService.addHero(new Hero());
	}
	
	@Test
	public void deleteHeroTest() throws HeroNotFoundException{
		Hero hero = heroService.getHero(getValidHeroId());
		int initialHeroLength = heroService.getHeroes().size();
		heroService.deleteHero(hero.getId());
		assertThat(heroService.getHeroes(), not(hasItem(hero)));
		assertThat(initialHeroLength, is(equalTo(heroService.getHeroes().size() + 1)));
	}
	
	@Test (expected = HeroNotFoundException.class)
	public void deleteHeroHeroNotFoundException() throws HeroNotFoundException{
		heroService.deleteHero(getNonExistentHeroId());
	}
	
}
