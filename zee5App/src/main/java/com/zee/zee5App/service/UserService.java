package com.zee.zee5App.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.User;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;

public interface UserService {
	public User insertUser(User user) throws UnableToGenerateIdException;

	public User updateUser(String userId, User user);

	public String deleteUser(String userId);

	public Optional<List<User>> getAllUsers();

	public Optional<User> getUserById(String userId);
}
