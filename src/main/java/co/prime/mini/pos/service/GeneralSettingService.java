package co.prime.mini.pos.service;

import co.prime.mini.pos.model.entity.GeneralSetting;
import co.prime.mini.pos.model.respone.CustomerResponse;
import co.prime.mini.pos.model.respone.GeneralSettingResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface GeneralSettingService {
    GeneralSetting save(GeneralSetting generalSetting);
    GeneralSetting getById(Long id);
    GeneralSetting update(Long id, GeneralSetting upGeneralSetting);
    GeneralSetting deleteById(Long id);
    List<GeneralSettingResponse> getSettings();
    Page<GeneralSettingResponse> findWithPagination(Map<String, String> params);
}
