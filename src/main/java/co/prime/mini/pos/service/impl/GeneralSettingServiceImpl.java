package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.GeneralSettingMapper;
import co.prime.mini.pos.models.entity.GeneralSetting;
import co.prime.mini.pos.models.respone.GeneralSettingResponse;
import co.prime.mini.pos.repository.GeneralSettingRepository;
import co.prime.mini.pos.service.GeneralSettingService;
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
public class GeneralSettingServiceImpl implements GeneralSettingService {

    private final GeneralSettingRepository generalSettingRepository;
    private final GeneralSettingMapper itemGeneralSettingMapper;
    @Override
    public GeneralSetting save(GeneralSetting generalSetting) {
        return generalSettingRepository.save(generalSetting);
    }
    @Override
    public GeneralSetting getById(Long id) {
        return generalSettingRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()->
                new ResourceNotFoundException("GeneralSetting",id));
    }

    @Override
    public GeneralSetting update(Long id, GeneralSetting upGeneralSetting) {
        GeneralSetting setting = getById(id);
        setting.setSiteTitle(upGeneralSetting.getSiteTitle());
        setting.setSiteLogo(upGeneralSetting.getSiteLogo());
        setting.setSitePhone(upGeneralSetting.getSitePhone());
        setting.setSiteAddress(upGeneralSetting.getSiteAddress());
        return generalSettingRepository.save(setting);
    }

    @Override
    public GeneralSetting deleteById(Long id) {
        GeneralSetting setting = getById(id);
        //if recode has been deleted.
        setting.setIsDeleted(true);
        GeneralSetting save = generalSettingRepository.save(setting);
        return save;
    }

    @Override
    public List<GeneralSettingResponse> getSettings() {
        return generalSettingRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(itemGeneralSettingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<GeneralSettingResponse> findWithPagination(
            Map<String, String> params) {
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
        Page<GeneralSettingResponse> page =
                generalSettingRepository.findByIsDeletedIsFalseOrderByIdDesc(pageable)
                        .map(itemGeneralSettingMapper::toDto);
        return page;
    }
}
