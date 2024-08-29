package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.CompanyMapper;
import co.prime.mini.pos.models.DTO.PageDTO;
import co.prime.mini.pos.models.entity.Company;
import co.prime.mini.pos.models.request.CompanyRequest;
import co.prime.mini.pos.models.respone.CompanyResponse;
import co.prime.mini.pos.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
                .message("Company has been saved")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getById(@Valid @PathVariable("id") Long customerId){
        Company byID = companyService.getById(customerId);
        CompanyResponse response = companyMapper.toDTO(byID);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Company have been found")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> updatedNewCustomer(@Valid @PathVariable("id") Long customerId,
                                         @RequestBody CompanyRequest request){
        Company company = companyMapper.toEntity(request);
        Company newCustomer = companyService.update(customerId, company);
        CompanyResponse response = companyMapper.toDTO(newCustomer);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.ACCEPTED.value())
                .message("Company has been updated")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping
    public BaseApi<?> getCustomers(){
        List<CompanyResponse> list = companyService.getCustomers();

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customers have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> delete(@Valid @PathVariable("id") Long id){
        Company deleteById = companyService.delete(id);
        CompanyResponse response = companyMapper.toDTO(deleteById);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.ACCEPTED.value())
                .message("Company has been deleted")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/page")
    public BaseApi<?> Specification(@Valid @RequestParam Map<String,String > params){
        Page<CompanyResponse> responses = companyService.findWithPagination(params);
        PageDTO pageDTO = new PageDTO(responses);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Company with Pagination have been found")
                .timestamp(LocalDateTime.now())
                .data(pageDTO)
                .build();

    }
    @PostMapping("/image/{id}")
    public ResponseEntity<?> saveImage(@PathVariable("id") Long id,
                                       @RequestParam  MultipartFile file) throws Exception {
        if(file.isEmpty()){
            return  new ResponseEntity<>("Please to select file upload.",
                    HttpStatus.BAD_REQUEST);
        }
        //if user upload from PDF EXCEL Can't to uploads.
        if(!file.getContentType().startsWith("image")){
            return new ResponseEntity<>("Please upload an image file",
                    HttpStatus.BAD_REQUEST);
        }
        Company saveImage = companyService.saveImage(id, file);
        CompanyResponse response = companyMapper.toDTO(saveImage);

        return ResponseEntity.ok(response);

    }
}
