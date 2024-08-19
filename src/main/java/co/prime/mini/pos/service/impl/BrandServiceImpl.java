package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.entity.Brand;
import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.BrandMapper;
import co.prime.mini.pos.repository.BrandRepository;
import co.prime.mini.pos.resquest.BrandReponse;
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
        return brandRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Brand",id));
    }

    @Override
    public Brand update(Long id, Brand newBrand) {
        Brand brand = getByID(id);
        brand.setName(newBrand.getName());
        return brandRepository.save(brand);
        
    }

    @Override
    public List<BrandReponse> getBrands() {
      return brandRepository.findAll().stream()
              .map(itemBrandMapper::toDTO)
              .collect(Collectors.toList());
    }

   @Override
   public Brand deleteOneBrand(Long id) {
      Brand byId = getByID(id);
      brandRepository.delete(byId);
       return byId;
   }
}
