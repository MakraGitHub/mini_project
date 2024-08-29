package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.CategoryMapper;
import co.prime.mini.pos.models.entity.Category;
import co.prime.mini.pos.models.request.CategoryRequest;
import co.prime.mini.pos.models.respone.CategoryResponse;
import co.prime.mini.pos.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor

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
    public BaseApi<?> getbyId(@PathVariable Long id){
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

}
