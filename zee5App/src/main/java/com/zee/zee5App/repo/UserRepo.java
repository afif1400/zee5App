package com.zee.zee5App.repo;

import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.User;
import com.zee.zee5App.exeptions.NoDataFoundException;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;

public interface UserRepo {
	public User insertUser(User user) throws UnableToGenerateIdException;

	public Optional<User> updateUser(String userId, User user) throws Exception;

	public String deleteUser(String userId) throws Exception;

	public Optional<List<User>> getAllUsers();

	public Optional<User> getUserById(String userId);

}