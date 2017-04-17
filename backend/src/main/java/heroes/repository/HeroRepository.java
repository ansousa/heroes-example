package heroes.repository;

import org.springframework.data.repository.CrudRepository;

import heroes.model.Hero;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

}
