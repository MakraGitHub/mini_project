package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.ProductMapper;
import co.prime.mini.pos.models.entity.ItemProduct;
import co.prime.mini.pos.models.respone.ItemProductResponse;
import co.prime.mini.pos.repository.ItemProductRepository;
import co.prime.mini.pos.service.GeneralFileService;
import co.prime.mini.pos.service.ItemProductService;
import co.prime.mini.pos.service.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemProductServiceImpl implements ItemProductService {

    private final ItemProductRepository itemProductRepository;
    private final ProductMapper productMapper;
    private final GeneralFileService generalFileService;

    @Value("${file.server-path}")
    private String fileServerPath;
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

        if(itemProduct.getBrand() ==null || itemProduct.getBrand().getId() ==null) {
            return null;
        }
        return itemProductRepository.save(parentItemProduct);
    }

    @Override
    public ItemProduct getById(Long id) {
        return itemProductRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Product",id));
    }

    @Override
    public ItemProduct deleteById(Long id) {
        ItemProduct product = getById(id);
        product.setIsDeleted(true);
        ItemProduct save = itemProductRepository.save(product);
        return save;
    }

    @Override
    public List<ItemProductResponse> getAllProducts() {
        return itemProductRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ItemProduct update(Long id, ItemProduct product) {
        ItemProduct itemProduct = getById(id);
        itemProduct.setProductName(product.getProductName());
        itemProduct.setProductCode(product.getProductCode());
        itemProduct.setProductType(product.getProductType());
        itemProduct.setCost(product.getCost());
        itemProduct.setPrice(product.getPrice());
        itemProduct.setQty(product.getQty());
        itemProduct.setAlertQty(itemProduct.getAlertQty());
        itemProduct.setBrand(itemProduct.getBrand());
        itemProduct.setItemCategory(itemProduct.getItemCategory());
        itemProduct.setItemUnit(itemProduct.getItemUnit());
        itemProduct.setPurchaseItemUnit(itemProduct.getPurchaseItemUnit());
        itemProduct.setSaleItemUnit(itemProduct.getSaleItemUnit());

        return itemProductRepository.save(itemProduct);
    }

    @Override
    public Page<ItemProductResponse> getWithPagination(Map<String, String> params) {
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        //step 1: check page limit =2
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        //Step 2:check page number
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
        Page<ItemProductResponse> page =
                itemProductRepository.findByIsDeletedIsFalseOrderByIdDesc(pageable).map(productMapper::toDTO);
        return page;
    }

    @Override
    public ItemProduct uploadImage(Long id, MultipartFile file) throws Exception {
         String fileName = generalFileService.generalFile(file.getOriginalFilename());

         String destinationPath = fileServerPath + fileName;
         file.transferTo(new File(destinationPath));

         ItemProduct saveImageProduct = getById(id);
        saveImageProduct.setImagePath(fileName);
        return itemProductRepository.save(saveImageProduct);
    }
}
