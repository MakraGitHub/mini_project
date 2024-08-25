package co.prime.mini.pos.service;

import co.prime.mini.pos.model.entity.Customer;
import co.prime.mini.pos.model.respone.CustomerResponse;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    Customer save(Customer customer);
    Customer getById(Long id);
    Customer update(Long id, Customer newCustomer);
    List<CustomerResponse> getCustomers();
    Customer delete(Long id);
    Page<CustomerResponse> findWithPagination(Map<String, String> params);
}
