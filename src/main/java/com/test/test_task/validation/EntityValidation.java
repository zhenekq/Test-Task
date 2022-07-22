package com.test.test_task.validation;

public interface EntityValidation<Entity> {
    Boolean isValid(Entity entity);
}
