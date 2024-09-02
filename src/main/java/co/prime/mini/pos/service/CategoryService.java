package co.prime.mini.pos.service;

import co.prime.mini.pos.models.entity.Category;
import co.prime.mini.pos.models.respone.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Category save(Category category);
    Category getById(Long id);
    Category deleteById(Long id);
    Category update(Long id, Category newCategory);
    List<CategoryResponse> listAll();
    Page<CategoryResponse> getWithPagination(Map<String, String> params);
    Category saveImage (Long id, MultipartFile file) throws Exception;

}
