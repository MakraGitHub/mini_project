package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.CompanyMapper;
import co.prime.mini.pos.model.entity.Company;
import co.prime.mini.pos.model.request.CompanyRequest;
import co.prime.mini.pos.model.respone.CompanyResponse;
import co.prime.mini.pos.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @PostMapping
    public BaseApi<?> save(@Valid @RequestBody CompanyRequest request){
        Company company = companyMapper.toEntity(request);
        Company save = companyService.create(company);
        CompanyResponse response = companyMapper.toDTO(save);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customer has been saved")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
}
