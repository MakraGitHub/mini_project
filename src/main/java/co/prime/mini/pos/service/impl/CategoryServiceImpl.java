package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.models.entity.Category;
import co.prime.mini.pos.repository.CategoryRepository;
import co.prime.mini.pos.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category save(Category category) {
        Category parentEntityCategory = new Category();
        parentEntityCategory.setCategoryName(category.getCategoryName());
        parentEntityCategory.setCategoryCode(category.getCategoryCode());

        if(category.getParent() !=null && category.getParent().getId() !=null){
            parentEntityCategory.setParent(category.getParent());
        }
        return categoryRepository.save(parentEntityCategory);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository
                .findByIdIsAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Category",id));
    }
}
