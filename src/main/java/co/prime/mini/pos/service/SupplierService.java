package co.prime.mini.pos.service;

import co.prime.mini.pos.model.entity.Supplier;
import co.prime.mini.pos.model.respone.SupplierResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface SupplierService {
    Supplier create(Supplier supplier);
    Supplier getById(Long id);
    Supplier updated(Long id, Supplier newSupplier);
    List<SupplierResponse> getSupplies();
    Supplier deleteById(Long id);
    Page<SupplierResponse> findWithPagination(Map<String, String> params);

}
