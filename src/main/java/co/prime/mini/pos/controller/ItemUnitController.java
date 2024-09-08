package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.ItemUnitMapper;
import co.prime.mini.pos.models.DTO.PageDTO;
import co.prime.mini.pos.models.entity.ItemUnit;
import co.prime.mini.pos.models.request.ItemUnitRequest;
import co.prime.mini.pos.models.respone.ItemUnitResponse;
import co.prime.mini.pos.service.ItemUnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/units")
@RequiredArgsConstructor
public class ItemUnitController {

    private final ItemUnitService itemUnitService;
    private final ItemUnitMapper itemUnitMapper;

    @PostMapping
    public BaseApi<?> create(@Valid @RequestBody ItemUnitRequest request){
        ItemUnit save = itemUnitMapper.toEntity(request);
        ItemUnit load = itemUnitService.create(save);

        ItemUnitResponse responseData = itemUnitMapper.toDTO(load);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Item unit has been saved")
                .timestamp(LocalDateTime.now())
                .data(responseData)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getById(@Valid @PathVariable Long id){
        ItemUnit itemUnit = itemUnitService.getById(id);
        ItemUnitResponse responseData = itemUnitMapper.toDTO(itemUnit);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Item units have been found")
                .timestamp(LocalDateTime.now())
                .data(responseData)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> deleteById(@Valid @PathVariable Long id){
        ItemUnit byId = itemUnitService.deleteById(id);
        ItemUnitResponse saveDate = itemUnitMapper.toDTO(byId);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Item unit has been deleted")
                .timestamp(LocalDateTime.now())
                .data(saveDate)
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> update(@Valid @PathVariable Long id, @RequestBody
                             ItemUnitRequest request){
        ItemUnit unit = itemUnitMapper.toEntity(request);
        ItemUnit loadData = itemUnitService.update(id, unit);
        ItemUnitResponse saveData = itemUnitMapper.toDTO(loadData);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Item unit has been deleted")
                .timestamp(LocalDateTime.now())
                .data(saveData)
                .build();
    }
    @GetMapping("/get-all-units")
    public BaseApi<?> listAll(){
        List<ItemUnitResponse> list = itemUnitService.getAllUnit();
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Item unit have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }
    @GetMapping("/getWithPagination")
    public BaseApi<?> getWithPagination(@Valid @RequestParam Map<String, String > params){
        Page<ItemUnitResponse> responses = itemUnitService.getAllWithPagination(params);
        PageDTO dto = new PageDTO(responses);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Item unit have been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();
    }
}
