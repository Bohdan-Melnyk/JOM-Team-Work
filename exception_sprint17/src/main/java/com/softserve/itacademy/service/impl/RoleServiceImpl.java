package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception_handling.EntityNotFoundException;
import com.softserve.itacademy.exception_handling.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) throws NullEntityReferenceException {
        if (role == null) {
            throw new NullEntityReferenceException("Role can't be null");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) throws EntityNotFoundException {
        Optional<Role> optional = roleRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("There is no role with ID " + id + " in database"));
    }

    @Override
    public Role update(Role role) throws EntityNotFoundException, NullEntityReferenceException {
        Role oldRole = readById(role.getId());
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Role role = readById(id);
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
