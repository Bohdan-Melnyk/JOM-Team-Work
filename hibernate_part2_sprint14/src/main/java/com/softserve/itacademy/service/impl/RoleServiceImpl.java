package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        Role newRole = roleRepository.saveAndFlush(role);
        return newRole;
    }

    @Override
    public Role readById(long id) {
        return  roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Role update(@NotNull Role role) {
        Role updateRole = readById(role.getId());
        updateRole.setName(role.getName());
        return roleRepository.save(updateRole);
    }

    @Override
    public void delete(long id) {
        Role role = readById(id);
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
