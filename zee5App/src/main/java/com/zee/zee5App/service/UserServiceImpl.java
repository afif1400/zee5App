package com.zee.zee5App.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.User;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;
import com.zee.zee5App.repo.UserRepo;
import com.zee.zee5App.repo.UserRepoImpl;

public class UserServiceImpl implements UserService {
	private UserServiceImpl() {
		
	}

	private UserRepo repo = UserRepoImpl.getInstance();

	private static UserService userService;

	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}

		return userService;
	}
	
	@Override
	public User insertUser(User user) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		return repo.insertUser(user);
	}

	@Override
	public User updateUser(String userId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<User>> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.getAllUsers();                             
	}

	@Override
	public Optional<User> getUserById(String userId) {
		// TODO Auto-generated method stub
		return repo.getUserById(userId);
	}

}
