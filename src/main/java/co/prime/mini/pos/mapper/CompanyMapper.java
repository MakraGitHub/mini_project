package co.prime.mini.pos.mapper;

import co.prime.mini.pos.model.entity.Company;
import co.prime.mini.pos.model.request.CompanyRequest;
import co.prime.mini.pos.model.respone.CompanyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CompanyRequest request);
    CompanyResponse toDTO(Company entity);
}
