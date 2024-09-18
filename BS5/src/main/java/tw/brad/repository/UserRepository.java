package tw.brad.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tw.brad.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public List<User> findByAccount(String account);
	
	
}
