package heroes.repository;

import org.springframework.data.repository.CrudRepository;

import heroes.model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer>{
}
