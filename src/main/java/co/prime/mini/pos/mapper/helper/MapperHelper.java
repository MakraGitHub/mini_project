package co.prime.mini.pos.mapper.helper;

import co.prime.mini.pos.models.entity.Brand;
import co.prime.mini.pos.service.BrandService;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperHelper {

    @Autowired
    private BrandService brandService;
    @Named("getBrandById")
    public Brand getBrandById(Long id){
     if(id == null){
         return null;
     }
      return brandService.getByID(id);
    }

}
