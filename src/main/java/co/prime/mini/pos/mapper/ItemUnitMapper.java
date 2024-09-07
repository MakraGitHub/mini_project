package co.prime.mini.pos.mapper;

import co.prime.mini.pos.models.entity.ItemUnit;
import co.prime.mini.pos.models.request.ItemUnitRequest;
import co.prime.mini.pos.models.respone.ItemUnitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemUnitMapper {
    @Mapping(target = "parent.id",source = "parentId")
    ItemUnit toEntity(ItemUnitRequest request);
    @Mapping(target = "parentId",source = "parent.id")
    ItemUnitResponse toDTO(ItemUnit dto);
}
