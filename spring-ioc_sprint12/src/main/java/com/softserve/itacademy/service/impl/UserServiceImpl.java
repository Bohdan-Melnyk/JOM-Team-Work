package com.softserve.itacademy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private List<User> users;

	public UserServiceImpl() {
		users = new ArrayList<>();
	}

	@Override
	public User addUser(User user) {
		if (user != null) {
			boolean idExists = users.stream().anyMatch(userFromList -> userFromList.getUserId() == user.getUserId());
			if (!idExists) {
				users.add(user);
				return user;
			} else {
				throw new RuntimeException("User is already exist");
			}
		}
		return null;
	}

	@Override
	public User updateUser(User user) {
        if (user != null ) {
            User userInTheList =  users.stream().filter(u -> u.getUserId() == user.getUserId()).findFirst().orElse(null);
            if (userInTheList != null ) {
                userInTheList.setFirstName(user.getFirstName());
                userInTheList.setLastName(user.getLastName());
                userInTheList.setPassword(user.getPassword());
                return userInTheList;
            }
        }
        return null;
    }

	@Override
	public void deleteUser(User user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserId() == user.getUserId()) {
				users.remove(user);
			} else {
				throw new RuntimeException("User not found");
			}
		}
	}

	@Override
	public List<User> getAll() {
		return users;
	}

}
