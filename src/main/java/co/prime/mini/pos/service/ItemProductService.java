package co.prime.mini.pos.service;

import co.prime.mini.pos.models.entity.ItemProduct;
import co.prime.mini.pos.models.respone.ItemProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ItemProductService {
    ItemProduct create(ItemProduct itemProduct);
    ItemProduct getById(Long id);
    ItemProduct deleteById(Long id);
    List<ItemProductResponse> getAllProducts();
    ItemProduct update(Long id, ItemProduct product);
    Page<ItemProductResponse> getWithPagination(Map<String, String> params);

}
