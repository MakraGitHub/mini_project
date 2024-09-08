package co.prime.mini.pos.service;

import co.prime.mini.pos.models.entity.ItemUnit;
import co.prime.mini.pos.models.respone.ItemUnitResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ItemUnitService {
    ItemUnit create(ItemUnit itemUnit);
    ItemUnit getById(Long id);
    ItemUnit deleteById(Long id);
    ItemUnit update(Long id, ItemUnit unit);
    List<ItemUnitResponse> getAllUnit();
    Page<ItemUnitResponse> getAllWithPagination(Map<String, String> params);
}
