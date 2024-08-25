package co.prime.mini.pos.mapper;

import co.prime.mini.pos.model.entity.Customer;
import co.prime.mini.pos.model.request.CustomerRequest;
import co.prime.mini.pos.model.respone.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerResponse toDTO(Customer entity);
}
