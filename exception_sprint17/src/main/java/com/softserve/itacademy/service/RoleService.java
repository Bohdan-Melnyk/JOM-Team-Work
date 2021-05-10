package com.softserve.itacademy.service;

import com.softserve.itacademy.exception_handling.EntityNotFoundException;
import com.softserve.itacademy.exception_handling.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role) throws NullEntityReferenceException;

    Role readById(long id) throws EntityNotFoundException;

    Role update(Role role) throws EntityNotFoundException, NullEntityReferenceException;

    void delete(long id) throws EntityNotFoundException;

    List<Role> getAll();
}
