package co.prime.mini.pos.controller;
import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.model.entity.Brand;
import co.prime.mini.pos.mapper.BrandMapper;
import co.prime.mini.pos.model.request.BrandRequest;
import co.prime.mini.pos.model.respone.BrandResponse;
import co.prime.mini.pos.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;
    @PostMapping("/brands")
    public BaseApi<?> create(@Valid @RequestBody BrandRequest request){
        Brand brand = brandMapper.toEntity(request);
        brand = brandService.create(brand);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brand has been saved")
                .timestamp(LocalDateTime.now())
                .data(brand)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getOneBrand(@PathVariable("id") Long brandId){
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
    public BaseApi<?> update(@PathVariable("id") Long brandId, @RequestBody BrandRequest request){
        Brand brand = brandMapper.toEntity(request);
        Brand updateBrand = brandService.update(brandId, brand);
        return BaseApi.builder()
        .status(true)
        .code(HttpStatus.ACCEPTED.value())
        .message("brand has been updated")
        .timestamp(LocalDateTime.now())
        .data(updateBrand)
        .build();
    }
    @GetMapping("/brands")
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
    public BaseApi<?> deleteBrand(@PathVariable Long id) {
        Brand brandDTO = brandService.deleteOneBrand(id);
        return  BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brand has been deleted")
                .timestamp(LocalDateTime.now())
                .data(brandDTO)
                .build();
    }
     
}
