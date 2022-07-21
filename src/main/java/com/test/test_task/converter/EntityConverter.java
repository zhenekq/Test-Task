package com.test.test_task.converter;

import java.util.List;

/**
 * Converter for Entities and DTO
 *
 * @author yauheni_vozny
 * @version 1.0
 */
public interface EntityConverter <Entity, EntityDto>{

    /**
     * From Entity to DTO
     *
     * @author yauheni_vozny
     * @return dto
     */
    EntityDto convertToDto(Entity entity);

    /**
     * From List of Entities to List of DTO
     *
     * @author yauheni_vozny
     * @return List of DTO
     */
    List<EntityDto> convertToListDto(List<Entity> entityList);

    /**
     * From DTO to Entity
     *
     * @author yauheni_vozny
     * @return Entity
     */
    Entity convert(EntityDto entityDto);

    /**
     * From List of DTO to List of Entity
     *
     * @author yauheni_vozny
     * @return List of Entity
     */
    List<Entity> convertToList(List<EntityDto> entityDtoList);
}
