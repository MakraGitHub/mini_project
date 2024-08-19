package co.prime.mini.pos.mapper;

import co.prime.mini.pos.entity.Brand;
import co.prime.mini.pos.respone.BrandRequest;
import co.prime.mini.pos.resquest.BrandReponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toEntity(BrandRequest request);

    BrandReponse toDTO(Brand entity);
}
