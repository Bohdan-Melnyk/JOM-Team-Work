package com.softserve.itacademy.Service;

import com.softserve.itacademy.model.State;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.service.StateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class StateServiceTest {

    @Autowired
    private StateService stateService;

    @Test
    public void createState(){
        State state = new State();
        state.setName("In progress");
        state.setTasks(new ArrayList<Task>());

        Assertions.assertTrue(stateService.create(state)!=null);
    }

    @Test
    public void readStateById(){
        Assertions.assertEquals(stateService.readById(8).getId(), 8);
        Assertions.assertEquals(stateService.readById(8).getName(),"Done");
    }

    @Test
    public void updateState(){
        State state = stateService.readById(5);
        state.setName("not verified");
        stateService.update(state);

        Assertions.assertEquals(stateService.readById(5).getName(), "not verified");
    }

    @Test
    public void delete(){
        stateService.delete(7);
        Assertions.assertThrows(EntityNotFoundException.class, ()->stateService.readById(7));
    }

    @Test
    public void getStateByName(){
        Assertions.assertEquals("Doing", stateService.getByName("Doing").getName());
    }

    @Test
    public void getAllStates(){
        Assertions.assertTrue(stateService.getAll().size()>=3);
    }
}
