package heroes.repository;

import org.springframework.data.repository.CrudRepository;

import heroes.model.Login;

public interface LoginRepository extends CrudRepository<Login, String> {

}
