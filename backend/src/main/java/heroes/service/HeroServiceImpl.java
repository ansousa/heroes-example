package heroes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import heroes.model.Hero;

@Service
public class HeroServiceImpl implements HeroService{
	private List<Hero> heroes;
	private int lastIndex = 20;
	public HeroServiceImpl() {
		Hero[] heroes = {
				new Hero(11, "Mr. Nice"),
				new Hero(12, "Narco"),
				new Hero(13, "Bombasto"),
				new Hero(14, "Celeritas"),
				new Hero(15, "Magneta"),
				new Hero(16, "RubberMan"),
				new Hero(17, "Dynama"),
				new Hero(18, "Dr IQ"),
				new Hero(19, "Magma"),
				new Hero(20, "Tornado")
		};
		this.heroes = new ArrayList<Hero>(Arrays.asList(heroes));
	}
	
	public List<Hero> getHeroes(){
		return this.heroes;
	}
	
	public Hero getHero(int id){
		for(Hero hero: this.heroes){
			if(hero.getId() == id)
				return hero;
		}
		return null;
	}
	
	public Hero updateHero(Hero hero){
		for(Hero hero2: this.heroes){
			if(hero.getId() == hero2.getId()){
				hero2.setName(hero.getName());
				return hero2;
			}
		}
		return null;
	}
	
	public Hero addHero(Hero hero){
		hero.setId(++this.lastIndex);
		this.heroes.add(hero);
		return hero;
	}
	
	public boolean deleteHero(Hero hero){
		for(Hero hero2: this.heroes){
			if(hero.getId() == hero2.getId()){
				return this.heroes.remove(hero2);
			}
		}
		return false;
	}
}
