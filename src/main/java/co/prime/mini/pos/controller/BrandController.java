package co.prime.mini.pos.controller;
import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.models.DTO.PageDTO;
import co.prime.mini.pos.models.entity.Brand;
import co.prime.mini.pos.mapper.BrandMapper;
import co.prime.mini.pos.models.request.BrandRequest;
import co.prime.mini.pos.models.respone.BrandResponse;
import co.prime.mini.pos.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;
    @PostMapping
    public BaseApi<?> create(@Valid @RequestBody BrandRequest request){
        Brand brand = brandMapper.toEntity(request);
        Brand save = brandService.create(brand);

        BrandResponse response = brandMapper.toDTO(save);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brand has been saved")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getOneBrand(@Valid @PathVariable("id") Long brandId){
         Brand getData = brandService.getByID(brandId);
         BrandResponse dto = brandMapper.toDTO(getData);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brand have been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();
    }

    @PutMapping("/{id}")
    public BaseApi<?> update(@Valid @PathVariable("id") Long brandId, @RequestBody BrandRequest request){
        Brand brand = brandMapper.toEntity(request);
        Brand updateBrand = brandService.update(brandId, brand);
        BrandResponse response = brandMapper.toDTO(updateBrand);

        return BaseApi.builder()
        .status(true)
        .code(HttpStatus.ACCEPTED.value())
        .message("brand has been updated")
        .timestamp(LocalDateTime.now())
        .data(response)
        .build();
    }
    @GetMapping()
    public BaseApi<?> findAll(){
        List<BrandResponse> list = brandService.getBrands();
        return BaseApi.builder()
        .status(true)
        .code(HttpStatus.OK.value())
        .message("brands have been found.")
        .timestamp(LocalDateTime.now())
        .data(list)
        .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> deleteBrand(@Valid @PathVariable Long id) {
        Brand brandDTO = brandService.deleteOneBrand(id);
        BrandResponse response = brandMapper.toDTO(brandDTO);

        return  BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brand has been deleted")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/page")
    public BaseApi<?> getAllWithPagination(@Valid @RequestParam Map<String, String> params){

        Page<BrandResponse> withPagination = brandService.getWithPagination(params);
        PageDTO pageDTO = new PageDTO(withPagination);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Specification and Pagination have been found")
                .timestamp(LocalDateTime.now())
                .data(pageDTO)
                .build();
    }
     
}
