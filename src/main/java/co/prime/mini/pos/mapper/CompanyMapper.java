package co.prime.mini.pos.mapper;

import co.prime.mini.pos.models.entity.Company;
import co.prime.mini.pos.models.request.CompanyRequest;
import co.prime.mini.pos.models.respone.CompanyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CompanyRequest request);
    CompanyResponse toDTO(Company entity);
}
