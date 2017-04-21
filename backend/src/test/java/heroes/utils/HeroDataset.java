package heroes.utils;

import heroes.model.Hero;

public class HeroDataset {
	
	public static Hero getValidHero(){
		return new Hero(getValidHeroId(), getValidHeroName());
	}
	
	public static int getValidHeroId(){
		return 1;
	}
	
	public static String getValidHeroName(){
		return "A";
	}
	
	public static int getInvalidHeroId(){
		return -1;
	}
	
	public static String getInvalidHeroName(){
		return "";
	}
	
	public static Hero[] getHeroes(){
		return new Hero[]{
			new Hero(1, "A"),
			new Hero(2, "B"),
			new Hero(3, "C"),
			new Hero(4, "D"),
			new Hero(5, "E")
		};
	}
	
	public static int getNonExistentHeroId(){
		return 6;
	}
	
	public static String getAnotherValidHeroName(){
		return "asd";
	}
}
