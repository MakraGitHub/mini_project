package co.prime.mini.pos.service;

import co.prime.mini.pos.models.entity.ItemUnit;
import co.prime.mini.pos.models.respone.ItemUnitResponse;

import java.util.List;

public interface ItemUnitService {
    ItemUnit create(ItemUnit itemUnit);
    ItemUnit getById(Long id);

    ItemUnit deleteById(Long id);
    ItemUnit update(Long id, ItemUnit unit);

    List<ItemUnitResponse> getAllUnit();
}
