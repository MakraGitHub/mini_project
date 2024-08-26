package co.prime.mini.pos.service;
import co.prime.mini.pos.model.entity.ExchangeRate;
import co.prime.mini.pos.model.respone.ExchangeRateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ExchangeRateService {
   ExchangeRate save(ExchangeRate rate);
   ExchangeRate getById(Long id);
   ExchangeRate updateRate(Long id, ExchangeRate exchangeRate);
   List<ExchangeRateResponse> getAllExchange();
   ExchangeRate delete(Long id);

   Page<ExchangeRateResponse> findWithPagination(Map<String, String> params);

}
