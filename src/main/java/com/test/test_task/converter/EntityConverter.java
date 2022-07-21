package com.test.test_task.converter;

import java.util.List;

public interface EntityConverter <Entity, EntityDto>{

    EntityDto convertToDto(Entity entity);

    List<EntityDto> convertToListDto(List<Entity> entityList);

    Entity convert(EntityDto entityDto);

    List<Entity> convertToList(List<EntityDto> entityDtos);
}
