package com.softserve.itacademy.service;

import com.softserve.itacademy.exception_handling.EntityNotFoundException;
import com.softserve.itacademy.exception_handling.NullEntityReferenceException;
import com.softserve.itacademy.model.State;

import java.util.List;

public interface StateService {
    State create(State state) throws NullEntityReferenceException;

    State readById(long id) throws EntityNotFoundException;

    State update(State state) throws NullEntityReferenceException, EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    State getByName(String name);

    List<State> getAll();
}
