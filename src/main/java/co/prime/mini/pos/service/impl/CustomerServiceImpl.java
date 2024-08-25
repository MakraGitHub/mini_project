package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.CustomerMapper;
import co.prime.mini.pos.model.entity.Customer;
import co.prime.mini.pos.model.respone.CustomerResponse;
import co.prime.mini.pos.repository.CustomerRepository;
import co.prime.mini.pos.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper itemCustomerMapper;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Customer getById(Long id) {
        return customerRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()->
                new ResourceNotFoundException("Customer",id));
    }
    @Override
    public Customer update(Long id, Customer newCustomer) {
        Customer upcustomer = getById(id);
        upcustomer.setCustomerLocalName(newCustomer.getCustomerLocalName());
        upcustomer.setCustomerEngName(newCustomer.getCustomerEngName());
        upcustomer.setCustomerEmail(newCustomer.getCustomerEmail());
        upcustomer.setCustomerPhone(newCustomer.getCustomerPhone());
        upcustomer.setCustomerAddress(newCustomer.getCustomerAddress());
        return customerRepository.save(upcustomer);
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(itemCustomerMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Customer delete(Long id) {
        Customer byId = getById(id);
        byId.setIsDeleted(true);
        Customer save = customerRepository.save(byId);
        return save;
    }

    @Override
    public Page<CustomerResponse> findWithPagination(Map<String, String> params) {

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
               pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
        Page<CustomerResponse> page =
                customerRepository.findByIsDeletedIsFalseOrderByIdDesc(pageable).map(itemCustomerMapper::toDTO);
        return page;
    }
}
