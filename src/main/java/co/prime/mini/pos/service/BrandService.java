package co.prime.mini.pos.service;

import java.util.List;

import co.prime.mini.pos.model.entity.Brand;
import co.prime.mini.pos.model.respone.BrandResponse;

public interface BrandService {
    Brand create(Brand brand);
    Brand getByID(Long id);
    Brand update(Long id, Brand newBrand);
    List<BrandResponse> getBrands();
    Brand deleteOneBrand(Long id);

}
