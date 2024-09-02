package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.CategoryMapper;
import co.prime.mini.pos.models.DTO.PageDTO;
import co.prime.mini.pos.models.entity.Category;
import co.prime.mini.pos.models.request.CategoryRequest;
import co.prime.mini.pos.models.respone.CategoryResponse;
import co.prime.mini.pos.service.CategoryService;
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

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public BaseApi<?> create(@Valid @RequestBody CategoryRequest request){
        Category entity = categoryMapper.toEntity(request);
        Category saveData = categoryService.save(entity);
        CategoryResponse response = categoryMapper.toDto(saveData);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("category has been saved")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getbyId(@Valid @PathVariable Long id){
        Category getData = categoryService.getById(id);
        CategoryResponse response = categoryMapper.toDto(getData);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("category has been saved")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> update( @Valid @PathVariable Long id,
                              @RequestBody CategoryRequest request){
        Category category = categoryMapper.toEntity(request);
        Category saveData = categoryService.update(id, category);
        CategoryResponse response = categoryMapper.toDto(saveData);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("category has been updated")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> delete(@Valid @PathVariable Long id){
        Category category = categoryService.deleteById(id);
        CategoryResponse response = categoryMapper.toDto(category);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("category has been deleted")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/get-all-categories")
    public BaseApi<?> getCategories(){
        List<CategoryResponse> list = categoryService.listAll();
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("category have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }
    @GetMapping("/getPagination")
    public BaseApi<?> getWithPagination(@Valid @RequestParam Map<String, String> params){
        Page<CategoryResponse> responses = categoryService.getWithPagination(params);
        PageDTO dto = new PageDTO(responses);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Pagination has been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
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
        //log.info("file saved",file);
        Category saveImage = categoryService.saveImage(id, file);
       // CategoryResponse response = categoryMapper.toDto(saveImage);
        log.info("file saved",saveImage);
        return ResponseEntity.ok(saveImage);
    }
}
