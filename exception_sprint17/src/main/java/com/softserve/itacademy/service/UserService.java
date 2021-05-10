package com.softserve.itacademy.service;

import com.softserve.itacademy.exception_handling.EntityNotFoundException;
import com.softserve.itacademy.exception_handling.NullEntityReferenceException;
import com.softserve.itacademy.model.User;

import java.util.List;

public interface UserService {
    User create(User user) throws NullEntityReferenceException;

    User readById(long id) throws EntityNotFoundException;

    User update(User user) throws EntityNotFoundException, NullEntityReferenceException;

    void delete(long id) throws EntityNotFoundException;

    List<User> getAll();

}
