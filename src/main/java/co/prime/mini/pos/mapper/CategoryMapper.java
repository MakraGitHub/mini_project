package co.prime.mini.pos.mapper;

import co.prime.mini.pos.models.entity.Category;
import co.prime.mini.pos.models.request.CategoryRequest;
import co.prime.mini.pos.models.respone.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "parent.id",source = "parentId")
    Category toEntity(CategoryRequest request);
    @Mapping(target = "parentId",source = "parent.id")
    CategoryResponse toDto(Category itemCategory);
    // I will to update to INSTANCE

}
