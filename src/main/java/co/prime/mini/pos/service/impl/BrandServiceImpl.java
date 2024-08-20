package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.model.entity.Brand;
import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.BrandMapper;
import co.prime.mini.pos.repository.BrandRepository;
import co.prime.mini.pos.model.respone.BrandResponse;
import co.prime.mini.pos.service.BrandService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
}
