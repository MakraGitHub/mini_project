package co.prime.mini.pos.mapper;

import co.prime.mini.pos.models.entity.Brand;
import co.prime.mini.pos.models.request.BrandRequest;
import co.prime.mini.pos.models.respone.BrandResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toEntity(BrandRequest request);
    BrandResponse toDTO(Brand entity);
}
