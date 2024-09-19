package tw.brad.service;

import tw.brad.model.ResponseUser;
import tw.brad.model.User;

public interface UserService {
	public ResponseUser regUser(User user);
	
	public ResponseUser isExistUser(String account);
	
	public ResponseUser loginUser(User user);
	
	
	public User updateUser(User user);
	public void deleteUser(Long id);
	
}
