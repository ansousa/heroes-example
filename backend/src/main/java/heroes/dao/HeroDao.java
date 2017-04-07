package heroes.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import heroes.model.Hero;

public class HeroDao {
	private static List<Hero> heroes;
	private static int lastIndex = 20;
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
	
	public static Hero updateHero(Hero hero){
		for(Hero hero2: heroes){
			if(hero.getId() == hero2.getId()){
				hero2.setName(hero.getName());
				return hero2;
			}
		}
		return null;
	}
	
	public static Hero addHero(Hero hero){
		hero.setId(++lastIndex);
		heroes.add(hero);
		return hero;
	}
	
	public static boolean deleteHero(Hero hero){
		for(Hero hero2: heroes){
			if(hero.getId() == hero2.getId()){
				heroes.remove(hero2);
				return true;
			}
		}
		return false;
	}
}
