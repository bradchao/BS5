package tw.brad.repository;

import org.springframework.data.repository.CrudRepository;

import tw.brad.model.Test;

public interface TestRepository extends CrudRepository<Test, Long>{

}
