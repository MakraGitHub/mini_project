package co.prime.mini.pos.service;

import java.util.List;

import co.prime.mini.pos.entity.Brand;
import co.prime.mini.pos.resquest.BrandReponse;

public interface BrandService {
    Brand create(Brand brand);
    Brand getByID(Long id);
    Brand update(Long id, Brand newBrand);
    List<BrandReponse> getBrands();
    Brand deleteOneBrand(Long id);

}
