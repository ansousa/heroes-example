package heroes.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import heroes.model.Hero;

public class HeroDao {
	private static List<Hero> heroes;
	static {
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
		HeroDao.heroes = new ArrayList<Hero>(Arrays.asList(heroes));
	}
	
	public static List<Hero> getHeroes(){
		return heroes;
	}
	
	public static Hero getHero(int id){
		for(Hero hero: heroes){
			if(hero.getId() == id)
				return hero;
		}
		return null;
	}
}
