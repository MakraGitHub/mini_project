package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.ExchangeRateMapper;
import co.prime.mini.pos.models.DTO.PageDTO;
import co.prime.mini.pos.models.entity.ExchangeRate;
import co.prime.mini.pos.models.request.ExchangeRateRequest;
import co.prime.mini.pos.models.respone.ExchangeRateResponse;
import co.prime.mini.pos.service.ExchangeRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;
    private final ExchangeRateMapper itemExchangeMapper;

    @PostMapping
    public BaseApi<?> save(@Valid @RequestBody ExchangeRateRequest request){
        ExchangeRate exchangeRate = itemExchangeMapper.toEntity(request);
        ExchangeRate saveData = exchangeRateService.save(exchangeRate);
        ExchangeRateResponse response = itemExchangeMapper.toDto(saveData);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Rate has been saved")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getById(@Valid @PathVariable("id")Long exchangeId){
        ExchangeRate rate = exchangeRateService.getById(exchangeId);
        ExchangeRateResponse getData = itemExchangeMapper.toDto(rate);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Exchange have been found")
                .timestamp(LocalDateTime.now())
                .data(getData)
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> update(@Valid @PathVariable("id") Long id,
                             @RequestBody ExchangeRateRequest request){

        ExchangeRate rate = itemExchangeMapper.toEntity(request);
        ExchangeRate update = exchangeRateService.updateRate(id, rate);
        ExchangeRateResponse responseData = itemExchangeMapper.toDto(update);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Exchange rate has been updated")
                .timestamp(LocalDateTime.now())
                .data(responseData)
                .build();
    }
    @GetMapping
    public BaseApi<?> getExchangeRate(){
        List<ExchangeRateResponse> responses = exchangeRateService.getAllExchange();

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Exchange rate have been found")
                .timestamp(LocalDateTime.now())
                .data(responses)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> deleteById(@Valid @PathVariable("id") Long rateId){
        ExchangeRate rate = exchangeRateService.delete(rateId);
        ExchangeRateResponse response = itemExchangeMapper.toDto(rate);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Exchange rate has been deleted")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();

    }
    @GetMapping("/page")
    public BaseApi<?> getWithPagination(@Valid @RequestParam Map<String, String> params){

        Page<ExchangeRateResponse> list = exchangeRateService.findWithPagination(params);
        PageDTO dto = new PageDTO(list);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.ACCEPTED.value())
                .message("Pagination has been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();

    }
}
