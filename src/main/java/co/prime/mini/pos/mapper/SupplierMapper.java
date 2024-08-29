package co.prime.mini.pos.mapper;

import co.prime.mini.pos.models.entity.Supplier;
import co.prime.mini.pos.models.request.SupplierRequest;
import co.prime.mini.pos.models.respone.SupplierResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toEntity(SupplierRequest request);
    SupplierResponse toDTO(Supplier entities);

}
