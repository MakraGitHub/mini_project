package co.prime.mini.pos.mapper;

import co.prime.mini.pos.model.entity.ExchangeRate;
import co.prime.mini.pos.model.request.ExchangeRateRequest;
import co.prime.mini.pos.model.respone.ExchangeRateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    ExchangeRate toEntity(ExchangeRateRequest request);
    ExchangeRateResponse toDto(ExchangeRate entities);

}
