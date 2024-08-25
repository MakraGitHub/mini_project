package co.prime.mini.pos.mapper;

import co.prime.mini.pos.model.entity.Supplier;
import co.prime.mini.pos.model.request.SupplierRequest;
import co.prime.mini.pos.model.respone.SupplierResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toEntity(SupplierRequest request);
    SupplierResponse toDTO(Supplier entities);

}
