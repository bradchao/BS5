package tw.brad.service;

import java.util.List;

import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.brad.model.ResponseUser;
import tw.brad.model.User;
import tw.brad.model.UserStatus;
import tw.brad.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseUser regUser(User user) {
		ResponseUser responseUser = isExistUser(user.getAccount());
		if (responseUser.getUserStatus() == UserStatus.NOT_EXIST) {
			user.setPasswd(BCrypt.hashpw(user.getPasswd(), BCrypt.gensalt()));
			
			User newUser =  userRepository.save(user);
			if (newUser.getId() != null) {
				responseUser.setUserStatus(UserStatus.ADD_SUCCESS);
				responseUser.setMesg("新增成功");
				responseUser.setUser(newUser);
			}else {
				responseUser.setUserStatus(UserStatus.ADD_FAILURE);
				responseUser.setMesg("新增失敗");
				responseUser.setUser(user);
			}
			
		}else {
			responseUser.setMesg("帳號已存在");
		}
		
		
		return responseUser;
	}

	@Override
	public ResponseUser isExistUser(String account) {
		ResponseUser responseUser = new ResponseUser();
		List<User> users = userRepository.findByAccount(account);
		if (users != null && users.size() > 0) {
			responseUser.setUserStatus(UserStatus.EXIST);
			responseUser.setMesg("帳號存在");
			responseUser.setUser(users.get(0));
		}else {
			responseUser.setUserStatus(UserStatus.NOT_EXIST);
			responseUser.setMesg("帳號不存在");
			responseUser.setUser(null);
		}
		
		return responseUser;
	}

	@Override
	public ResponseUser loginUser(User user) {
		ResponseUser responseUser = isExistUser(user.getAccount());
		if (responseUser.getUserStatus() == UserStatus.NOT_EXIST) {
			responseUser.setUserStatus(UserStatus.LOGIN_FAILURE);
			responseUser.setMesg("Login Failure");
			responseUser.setUser(user);
		}else {
			User userDB = responseUser.getUser();
			if (BCrypt.checkpw(user.getPasswd(), userDB.getPasswd())) {
				responseUser.setUserStatus(UserStatus.LOGIN_SUCCESS);
				responseUser.setMesg("Login Success");
				responseUser.setUser(userDB);
			}else {
				responseUser.setUserStatus(UserStatus.LOGIN_FAILURE);
				responseUser.setMesg("Login Failure");
				responseUser.setUser(user);				
			}
		}
		
		return responseUser;
	}

	@Override
	public User updateUser(User user) {
		User userDB = userRepository.findById(user.getId()).orElse(null);
		if (userDB != null) {
			if (user.getAccount() != null) {
				userDB.setAccount(user.getAccount());
			}
			if (user.getName() != null) {
				userDB.setName(user.getName());
			}
			if (user.getDetail() != null && user.getDetail().getNickname() != null) {
				userDB.getDetail().setNickname(user.getDetail().getNickname());
			}
			
			userRepository.save(userDB);
			
			return userDB;
		}else {
			System.out.println("Not Found");
			return null;
		}
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
//		User userDB = userRepository.findById(id).orElse(null);
//		if (userDB != null) {
//			userRepository.delete(userDB);
//		}
		
	}

	
	
	
}
