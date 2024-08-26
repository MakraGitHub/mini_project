package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.SupplierMapper;
import co.prime.mini.pos.model.DTO.PageDTO;
import co.prime.mini.pos.model.entity.Supplier;
import co.prime.mini.pos.model.request.SupplierRequest;
import co.prime.mini.pos.model.respone.SupplierResponse;
import co.prime.mini.pos.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supplies")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @PostMapping
    public BaseApi<?> save(@Valid @RequestBody SupplierRequest request){
        Supplier supplier = supplierMapper.toEntity(request);
        Supplier saveData = supplierService.create(supplier);

        SupplierResponse response = supplierMapper.toDTO(saveData);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Supplies have been save")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getById(@Valid @PathVariable("id") Long supplierId){
        Supplier supplier = supplierService.getById(supplierId);
        SupplierResponse response = supplierMapper.toDTO(supplier);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Supplier have been found")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> updateNewSupplier(@Valid @PathVariable("id") Long supplierId,
                                        @RequestBody SupplierRequest request){
        Supplier supplier = supplierMapper.toEntity(request);
        Supplier newSupplies = supplierService.updated(supplierId, supplier);

        SupplierResponse response = supplierMapper.toDTO(newSupplies);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Supplies have been update")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping
    public BaseApi<?> getSupplies(){
        List<SupplierResponse> list = supplierService.getSupplies();

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Supplier have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseApi<?> deleteById(@Valid @PathVariable("id") Long id){
        Supplier deleteById =supplierService.deleteById(id);
        SupplierResponse response = supplierMapper.toDTO(deleteById);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Supplier has been deleted")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/page")
    public BaseApi<?> getWithPagination(@Valid @RequestParam Map<String, String> params){

        Page<SupplierResponse> list = supplierService.findWithPagination(params);
        PageDTO dto = new PageDTO(list);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.ACCEPTED.value())
                .message("Pagination has been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();

    }
}
