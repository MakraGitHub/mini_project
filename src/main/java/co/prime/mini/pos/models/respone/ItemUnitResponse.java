package co.prime.mini.pos.models.respone;

import co.prime.mini.pos.models.enums.EnumItemUnit;
import lombok.Data;

import java.util.List;

@Data
public class ItemUnitResponse {
    private Long id;
    private String itemUnitCode;
    private String itemUnitName;
    private EnumItemUnit operator;
    private Double operationValue;
    private Long parentId;
    private List<Long> childId;
}
