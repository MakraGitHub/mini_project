package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.mapper.ProductMapper;
import co.prime.mini.pos.models.entity.ItemProduct;
import co.prime.mini.pos.repository.ItemProductRepository;
import co.prime.mini.pos.service.ItemProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemProductServiceImpl implements ItemProductService {
    private final ItemProductRepository itemProductRepository;
    private final ProductMapper productMapper;

    @Override
    public ItemProduct create(ItemProduct itemProduct) {
        return itemProductRepository.save(itemProduct);
    }
}
