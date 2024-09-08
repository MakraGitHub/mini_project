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
    private final  ItemProductRepository productRepository;
    @Override
    public ItemProduct create(ItemProduct itemProduct) {
        ItemProduct parentItemProduct = new ItemProduct();
        parentItemProduct.setProductCode(itemProduct.getProductCode());
        parentItemProduct.setProductName(itemProduct.getProductName());
        parentItemProduct.setProductType(itemProduct.getProductType());
        parentItemProduct.setCost(itemProduct.getCost());
        parentItemProduct.setPrice(itemProduct.getPrice());
        parentItemProduct.setQty(itemProduct.getQty());
        parentItemProduct.setAlertQty(itemProduct.getAlertQty());
        parentItemProduct.setBrand(itemProduct.getBrand());
        parentItemProduct.setItemCategory(itemProduct.getItemCategory());
        parentItemProduct.setItemUnit(itemProduct.getItemUnit());
        parentItemProduct.setPurchaseItemUnit(itemProduct.getPurchaseItemUnit());
        parentItemProduct.setSaleItemUnit(itemProduct.getSaleItemUnit());

        if(itemProduct.getBrand() ==null || itemProduct.getBrand().getId() ==null){
         return null;
        }if(itemProduct.getItemCategory() !=null && itemProduct.getItemCategory().getId() !=null){
            parentItemProduct.setItemCategory(itemProduct.getItemCategory());
        }if(itemProduct.getItemUnit() !=null && itemProduct.getItemUnit().getParent().getId() !=null){
            parentItemProduct.setItemUnit(itemProduct.getItemUnit());
        }

        return itemProductRepository.save(parentItemProduct);
    }
}
