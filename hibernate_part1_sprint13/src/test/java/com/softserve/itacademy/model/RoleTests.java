package com.softserve.itacademy.model;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleTests {
    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void constraintViolationOnEmptyRoleName() {
        Role emptyRole = new Role();
        emptyRole.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(emptyRole);
        assertEquals(1, violations.size());
        //
    }

    @Test
    public void roleIsValid() {
        Role role = new Role();
        role.setName("collaborator");

        Set<ConstraintViolation<Role>> constraintViolations =
                validator.validate( role );

        assertEquals( 0, constraintViolations.size() );
    }

}
