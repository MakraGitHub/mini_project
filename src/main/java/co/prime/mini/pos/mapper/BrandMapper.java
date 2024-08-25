package co.prime.mini.pos.mapper;

import co.prime.mini.pos.model.entity.Brand;
import co.prime.mini.pos.model.request.BrandRequest;
import co.prime.mini.pos.model.respone.BrandResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toEntity(BrandRequest request);
    BrandResponse toDTO(Brand entity);
}
