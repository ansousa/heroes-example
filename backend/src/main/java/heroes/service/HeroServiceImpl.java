package heroes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heroes.model.Hero;
import heroes.repository.HeroRepository;

@Service
public class HeroServiceImpl implements HeroService {
	
	@Autowired
	private HeroRepository repository;

	@Override
	public List<Hero> getHeroes() {
		Iterable<Hero> heroes = repository.findAll();
		List<Hero> heroList = new ArrayList<>();
		for(Hero hero: heroes){
			heroList.add(hero);
		}
		return heroList;
	}

	@Override
	public Hero getHero(int id) {
		return repository.findOne(id);
	}

	@Override
	public Hero updateHero(Hero hero) {
		return repository.save(hero);
	}

	@Override
	public Hero addHero(Hero hero) {
		return repository.save(hero);
	}

	@Override
	public boolean deleteHero(int id) {
		repository.delete(id);
		return true;
	}

}
