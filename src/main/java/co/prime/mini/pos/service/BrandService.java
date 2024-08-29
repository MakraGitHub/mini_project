package co.prime.mini.pos.service;

import java.util.List;
import java.util.Map;

import co.prime.mini.pos.models.entity.Brand;
import co.prime.mini.pos.models.respone.BrandResponse;
import org.springframework.data.domain.Page;

public interface BrandService {
    Brand create(Brand brand);
    Brand getByID(Long id);
    Brand update(Long id, Brand newBrand);
    List<BrandResponse> getBrands();
    Brand deleteOneBrand(Long id);
    Page<BrandResponse> getWithPagination(Map<String, String> params);

}
