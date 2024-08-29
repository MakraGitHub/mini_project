package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.CustomerMapper;
import co.prime.mini.pos.models.DTO.PageDTO;
import co.prime.mini.pos.models.entity.Customer;
import co.prime.mini.pos.models.request.CustomerRequest;
import co.prime.mini.pos.models.respone.CustomerResponse;
import co.prime.mini.pos.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService  customerService;
    private final CustomerMapper customerMapper;
    @PostMapping
    public BaseApi<?> save(@Valid @RequestBody CustomerRequest request){
          Customer customer = customerMapper.toEntity(request);
          customerService.save(customer);
          CustomerResponse save = customerMapper.toDTO(customer);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customer has been saved")
                .timestamp(LocalDateTime.now())
                .data(save)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getById(@Valid @PathVariable("id") Long customerId){
        Customer byID = customerService.getById(customerId);
        CustomerResponse response = customerMapper.toDTO(byID);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customer have been found")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> updatedNewCustomer(@Valid @PathVariable("id") Long customerId,
                                        @RequestBody CustomerRequest request){
        Customer customer = customerMapper.toEntity(request);
        Customer newCustomer = customerService.update(customerId, customer);
        CustomerResponse response = customerMapper.toDTO(newCustomer);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.ACCEPTED.value())
                .message("Customer has been updated")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping
    public BaseApi<?> getCustomers(){
        List<CustomerResponse> list = customerService.getCustomers();

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customers have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> delete(@Valid @PathVariable("id") Long id){
        Customer deleteById = customerService.delete(id);
        CustomerResponse response = customerMapper.toDTO(deleteById);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.ACCEPTED.value())
                .message("Customer has been deleted")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/page")
    public BaseApi<?> Specification(@Valid @RequestParam Map<String,String > params){
        Page<CustomerResponse> responses = customerService.findWithPagination(params);
        PageDTO pageDTO = new PageDTO(responses);
          return BaseApi.builder()
                  .status(true)
                  .code(HttpStatus.OK.value())
                  .message("Specification with Pagination have been found")
                  .timestamp(LocalDateTime.now())
                  .data(pageDTO)
                  .build();

    }

}
