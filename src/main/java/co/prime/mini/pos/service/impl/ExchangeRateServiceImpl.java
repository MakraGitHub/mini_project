package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.ExchangeRateMapper;
import co.prime.mini.pos.models.entity.ExchangeRate;
import co.prime.mini.pos.models.respone.ExchangeRateResponse;
import co.prime.mini.pos.repository.ExchangeRateRepository;
import co.prime.mini.pos.service.ExchangeRateService;
import co.prime.mini.pos.service.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository rateRepository;
    private final ExchangeRateMapper itemRateMapper;

    @Override
    public ExchangeRate save(ExchangeRate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public ExchangeRate getById(Long id) {
        return rateRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()->
                new ResourceNotFoundException("ExchangeRate",id));
    }

    @Override
    public ExchangeRate updateRate(Long id, ExchangeRate exchangeRate) {
        ExchangeRate rate = getById(id);
        rate.setExchangeRate(exchangeRate.getExchangeRate());
        return rateRepository.save(rate);
    }

    @Override
    public List<ExchangeRateResponse> getAllExchange() {
        return rateRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(itemRateMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExchangeRate delete(Long id) {
        ExchangeRate getID = getById(id);
         getID.setIsDeleted(true);
        ExchangeRate save = rateRepository.save(getID);
        return save;
    }

    @Override
    public Page<ExchangeRateResponse> findWithPagination(
            Map<String, String> params) {
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        Pageable pageable =  PageUtil.getPageable(pageNumber,pageLimit);
        Page<ExchangeRateResponse> page =
                rateRepository.findByIsDeletedIsFalseOrderByIdDesc(pageable)
                        .map(itemRateMapper::toDto);
        return page;
    }
}
