package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.ProductMapper;
import co.prime.mini.pos.models.DTO.PageDTO;
import co.prime.mini.pos.models.entity.ItemProduct;
import co.prime.mini.pos.models.request.ItemProductRequest;
import co.prime.mini.pos.models.respone.ItemProductResponse;
import co.prime.mini.pos.service.ItemProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
@Slf4j
public class ItemProductController {
    private final ItemProductService itemProductService;
    private final ProductMapper productMapper;

    @PostMapping
    public BaseApi<?> create(@Valid @RequestBody ItemProductRequest request){
        ItemProduct create = productMapper.toEntity(request);
        ItemProduct load = itemProductService.create(create);
        log.info("Load data",load);
        ItemProductResponse response = productMapper.toDTO(load);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("product has been saved")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getById(@Valid @PathVariable Long id){
        ItemProduct itemProduct = itemProductService.getById(id);
        ItemProductResponse response = productMapper.toDTO(itemProduct);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("product have been found")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> deleteById(@Valid @PathVariable Long id){
        ItemProduct byID = itemProductService.deleteById(id);
        ItemProductResponse response = productMapper.toDTO(byID);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("product has been deleted")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/get-all-products")
    public BaseApi<?> getProducts(){
        List<ItemProductResponse> getList = itemProductService.getAllProducts();
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("product has been deleted")
                .timestamp(LocalDateTime.now())
                .data(getList)
                .build();
    }

    @PutMapping("/{id}")
    public BaseApi<?> updatePro(@Valid @PathVariable Long id,
                                @RequestBody ItemProductRequest request){

        ItemProduct item = productMapper.toEntity(request);
        ItemProduct save = itemProductService.update(id, item);

        ItemProductResponse responseData = productMapper.toDTO(save);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("product has been updated")
                .timestamp(LocalDateTime.now())
                .data(responseData)
                .build();
    }
    @GetMapping("/pagination")
    public BaseApi<?> getAllWithPagination(@Valid @RequestParam Map<String, String> params){

        Page<ItemProductResponse> withPagination = itemProductService.getWithPagination(params);
        PageDTO pageDTO = new PageDTO(withPagination);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Specification and Pagination have been found")
                .timestamp(LocalDateTime.now())
                .data(pageDTO)
                .build();
    }

    @PostMapping("/image/{id}")
    public ResponseEntity<?> saveImage(@Valid @PathVariable("id") Long id, @RequestParam
                                       MultipartFile file) throws Exception{

        if(file.isEmpty()){
            return  new ResponseEntity<>("file to select file upload",
                    HttpStatus.BAD_REQUEST);

        }
        if(!file.getContentType().startsWith("image")){
            return new ResponseEntity<>("Please upload an image file",
                    HttpStatus.BAD_REQUEST);
        }
        ItemProduct save = itemProductService.uploadImage(id, file);
        ItemProductResponse response = productMapper.toDTO(save);
        return ResponseEntity.ok(save);
    }

}
