package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    StateRepository stateRepository;

    @Override
    public State create(State state) {
        State newState = stateRepository.saveAndFlush(state);
        return newState;
    }

    @Override
    public State readById(long id) {

        return stateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public State update(@NotNull State state) {
        State updateState = readById(state.getId());
        updateState.setName(state.getName());
        return stateRepository.save(updateState);
    }

    @Override
    public void delete(long id) {
        State state = readById(id);
        stateRepository.delete(state);
    }

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public State getByName(String name) {
        return stateRepository.findByName(name);
    }

    @Override
    public List<State> getSortAsc() {
        return stateRepository.findByOrderByNameAsc();
    }
}
