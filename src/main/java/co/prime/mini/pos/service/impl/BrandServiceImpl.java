package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.models.entity.Brand;
import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.BrandMapper;
import co.prime.mini.pos.repository.BrandRepository;
import co.prime.mini.pos.models.respone.BrandResponse;
import co.prime.mini.pos.service.BrandService;
import co.prime.mini.pos.service.util.PageUtil;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper itemBrandMapper;
    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getByID(Long id) {
        return brandRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Brand",id));
    }

    @Override
    public Brand update(Long id, Brand newBrand) {
        Brand brand = getByID(id);
        brand.setName(newBrand.getName());
        return brandRepository.save(brand);
    }
    @Override
    public List<BrandResponse> getBrands() {
      return brandRepository.findByIsDeletedIsFalseOrderByIdDesc().stream()
              .map(itemBrandMapper::toDTO)
              .collect(Collectors.toList());
    }
   @Override
   public Brand deleteOneBrand(Long id) {
       Brand byId = getByID(id);
       byId.setIsDeleted(true);
       Brand save = brandRepository.save(byId);
       return save;
   }

    @Override
    public Page<BrandResponse> getWithPagination(Map<String, String> params) {

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        //step 1: check page limit =2
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        //Step 2:check page number
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
        Page<BrandResponse> page =
                brandRepository.findByIsDeletedIsFalseOrderByIdDesc(pageable).map(itemBrandMapper::toDTO);
        return page;
    }
}
