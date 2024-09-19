package tw.brad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.brad.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public List<User> findByAccount(String account);
	
	public List<User> findByAccountContaining(String account);
	
	@Query("SELECT u FROM User u WHERE u.account LIKE %:account% OR u.name LIKE %:name%")
	public List<User> findByAccountOrNameLike(@Param("account") String account,
											@Param("name") String name);
	
	
}
