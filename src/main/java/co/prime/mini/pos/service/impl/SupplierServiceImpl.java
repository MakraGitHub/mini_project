package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.SupplierMapper;
import co.prime.mini.pos.model.entity.Supplier;
import co.prime.mini.pos.model.respone.SupplierResponse;
import co.prime.mini.pos.repository.SupplierServiceRepository;
import co.prime.mini.pos.service.SupplierService;
import co.prime.mini.pos.service.util.PageUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierServiceRepository supplierServiceRepository;
    private final SupplierMapper itemSupplierMapper;
    @Override
    public Supplier create(Supplier supplier) {
        return supplierServiceRepository.save(supplier);
    }

    @Override
    public Supplier getById(Long id) {
        return supplierServiceRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Supplier",id));
    }
    @Override
    public Supplier updated(Long id, Supplier newSupplier) {
        //find by id after updated new supplier
        Supplier supplier = getById(id);
        supplier.setSupplierLocalName(newSupplier.getSupplierLocalName());
        supplier.setSupplierEngName(newSupplier.getSupplierEngName());
        supplier.setSupplierEmail(newSupplier.getSupplierEmail());
        supplier.setSupplierPhone(newSupplier.getSupplierPhone());
        supplier.setSupplierAddress(newSupplier.getSupplierAddress());
        supplier.setSupplierVatNumber(newSupplier.getSupplierVatNumber());
        return supplierServiceRepository.save(supplier);
    }

    @Override
    public List<SupplierResponse> getSupplies() {
        return supplierServiceRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(itemSupplierMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Supplier deleteById(Long id) {
         Supplier byId = getById(id);
         byId.setIsDeleted(true);
         Supplier save = supplierServiceRepository.save(byId);
         return save;
    }
    @Override
    public Page<SupplierResponse> findWithPagination(Map<String, String> params) {

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        Pageable pageable =  PageUtil.getPageable(pageNumber,pageLimit);
        Page<SupplierResponse> page =
                supplierServiceRepository.findByIsDeletedIsFalseOrderByIdDesc(pageable)
                        .map(itemSupplierMapper::toDTO);
        return page;
    }
}
