package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception_handling.EntityNotFoundException;
import com.softserve.itacademy.exception_handling.NullEntityReferenceException;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws NullEntityReferenceException {
        if (user == null) {
            throw new NullEntityReferenceException("User can't be null");
        }
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) throws EntityNotFoundException {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("There is no user with ID " + id + " in database"));
    }

    @Override
    public User update(User user) throws EntityNotFoundException, NullEntityReferenceException {
        if (user == null) {
            throw new NullEntityReferenceException("User can't be null");
        }
        User oldUser = readById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

}
