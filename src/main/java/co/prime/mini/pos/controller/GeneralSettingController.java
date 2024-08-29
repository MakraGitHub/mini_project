package co.prime.mini.pos.controller;

import co.prime.mini.pos.base.BaseApi;
import co.prime.mini.pos.mapper.GeneralSettingMapper;
import co.prime.mini.pos.models.DTO.PageDTO;
import co.prime.mini.pos.models.entity.GeneralSetting;
import co.prime.mini.pos.models.request.GeneralSettingRequest;
import co.prime.mini.pos.models.respone.GeneralSettingResponse;
import co.prime.mini.pos.service.GeneralSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/general_settings")
@RequiredArgsConstructor
public class GeneralSettingController {
    private final GeneralSettingService generalSettingService;
    private final GeneralSettingMapper itemSettingMapper;

    @PostMapping
    public BaseApi<?> create(@Valid @RequestBody GeneralSettingRequest request){
        GeneralSetting setting = itemSettingMapper.toEntity(request);
        GeneralSetting saveDate = generalSettingService.save(setting);
        GeneralSettingResponse response = itemSettingMapper.toDto(saveDate);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("General settings has been save!")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getById(@Valid @PathVariable("id")Long settingId){
        GeneralSetting getID = generalSettingService.getById(settingId);
        GeneralSettingResponse saveDate = itemSettingMapper.toDto(getID);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("General settings have been found")
                .timestamp(LocalDateTime.now())
                .data(saveDate)
                .build();
    }
    @PutMapping("{id}")
    public BaseApi<?> update(@Valid @PathVariable("id") Long id,
                             @RequestBody GeneralSettingRequest request){
        GeneralSetting setting = itemSettingMapper.toEntity(request);
        GeneralSetting saveData = generalSettingService.update(id,setting);
        GeneralSettingResponse response = itemSettingMapper.toDto(saveData);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("General settings have been found")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> deleteId(@Valid @PathVariable("id") Long id){
        GeneralSetting setting = generalSettingService.deleteById(id);
        GeneralSettingResponse response = itemSettingMapper.toDto(setting);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("General settings have ben deleted")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
    @GetMapping
    public BaseApi<?> getAll(){
        List<GeneralSettingResponse> list = generalSettingService.getSettings();

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("General settings have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }
    @GetMapping("/page")
    public BaseApi<?> Specification(@Valid @RequestParam Map<String,String > params){
        Page<GeneralSettingResponse> responses = generalSettingService.findWithPagination(params);
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
