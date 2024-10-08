package co.prime.mini.pos.mapper;

import co.prime.mini.pos.models.entity.GeneralSetting;
import co.prime.mini.pos.models.request.GeneralSettingRequest;
import co.prime.mini.pos.models.respone.GeneralSettingResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralSettingMapper {
    GeneralSetting toEntity(GeneralSettingRequest request);
    GeneralSettingResponse toDto(GeneralSetting entity);
}
