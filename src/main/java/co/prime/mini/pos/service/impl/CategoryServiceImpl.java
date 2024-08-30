package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.CategoryMapper;
import co.prime.mini.pos.models.entity.Category;
import co.prime.mini.pos.models.respone.CategoryResponse;
import co.prime.mini.pos.repository.CategoryRepository;
import co.prime.mini.pos.service.CategoryService;
import co.prime.mini.pos.service.GeneralFileService;
import co.prime.mini.pos.service.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper itemCategoryMapper;
    private  final GeneralFileService generalFileService;

    @Value("${file.server-path}")
    private String fileServerPath;

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

    @Override
    public Category deleteById(Long id) {
        Category byID = getById(id);
        byID.setIsDeleted(true);
        Category save = categoryRepository.save(byID);
        return save;
    }

    @Override
    public Category update(Long id, Category newCategory) {
        Category category = getById(id);
        category.setCategoryName(newCategory.getCategoryName());
        category.setCategoryCode(newCategory.getCategoryCode());

        if(category.getParent() !=null && category.getParent() !=null){
            Category parentCategory = getById(category.getParent().getId());
        }else {
            category.setParent(null);
        }
        return categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponse> listAll() {
        return categoryRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(itemCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CategoryResponse> getWithPagination(Map<String, String> params) {

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit =  Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
       Page<CategoryResponse> page = categoryRepository
               .findByIsDeletedIsFalseOrderByIdDesc(pageable)
               .map(itemCategoryMapper::toDto);

       // update condition specification
        return page;
    }

    @Override
    public Category saveImage(Long id, MultipartFile file) throws Exception {
        //Upload image to change name image
        String fileName = generalFileService.generalFile(file.getOriginalFilename());
        //After change file it is make transfer image to file path.
        String destinationPath = fileServerPath + fileName;
        file.transferTo(new File(destinationPath));

        Category saveImageCate = getById(id);
        saveImageCate.setCategoryPath(fileName);

        return categoryRepository.save(saveImageCate);
    }

}
