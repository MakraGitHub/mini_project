package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.ProductMapper;
import co.prime.mini.pos.models.entity.ItemProduct;
import co.prime.mini.pos.models.request.ItemProductRequest;
import co.prime.mini.pos.models.respone.ItemProductResponse;
import co.prime.mini.pos.service.ItemProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ItemProductController {
    private final ItemProductService itemProductService;
    private final ProductMapper productMapper;

    @PostMapping
    public BaseApi<?> create(@Valid @RequestBody ItemProductRequest request){
        ItemProduct create = productMapper.toEntity(request);
        ItemProduct load = itemProductService.create(create);

        ItemProductResponse response = productMapper.toDTO(load);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("product has been saved")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
}
