package heroes.utils;

import heroes.model.Hero;

public class HeroDataset {
	
	public static Hero getValidHero(){
		return new Hero(0, "Hero0");
	}
	
	public static int getValidHeroId(){
		return 1;
	}
	
	public static String getValidHeroName(){
		return "Hero1";
	}
	
	public static int getInvalidHeroId(){
		return -1;
	}
	
	public static String getInvalidHeroName(){
		return "";
	}
}
