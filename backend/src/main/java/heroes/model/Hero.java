package heroes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hero {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	public Hero(){}
	
	public Hero(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id < 0)
			throw new IllegalArgumentException();
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null || name.isEmpty())
			throw new IllegalArgumentException();
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		Hero hero = null;
		try{
			hero = (Hero)o;
		}
		catch(ClassCastException e){
			return false;
		}
		return hero != null
			&& this.id == hero.id
			&& this.name != null
			&& this.name.equals(hero.name);
	}
}
