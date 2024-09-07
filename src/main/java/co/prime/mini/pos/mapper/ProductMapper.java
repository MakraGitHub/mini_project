package co.prime.mini.pos.mapper;

import co.prime.mini.pos.mapper.helper.MapperHelper;
import co.prime.mini.pos.models.entity.ItemProduct;
import co.prime.mini.pos.models.request.ItemProductRequest;
import co.prime.mini.pos.models.respone.ItemProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {MapperHelper.class})
public interface ProductMapper {
      @Mapping(target = "brand.id",source = "brandId",qualifiedByName = "getBrandById")
      ItemProduct toEntity(ItemProductRequest dto);
      @Mapping(target = "brandId", source = "brand.id")
      ItemProductResponse toDTO(ItemProduct list);
}
