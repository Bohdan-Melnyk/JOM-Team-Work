package com.softserve.itacademy;

import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StateRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    StateRepository stateRepository;

    @Test
    public void getStateByName(){
        Assertions.assertTrue(stateRepository.getByName("Done").getId()==8);
    }

    @Test
    public void getAllStates() {
        Assertions.assertTrue(stateRepository.getAll().size()==4);
    }
}
