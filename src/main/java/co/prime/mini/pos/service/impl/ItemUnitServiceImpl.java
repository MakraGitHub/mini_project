package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.ItemUnitMapper;
import co.prime.mini.pos.models.entity.ItemUnit;
import co.prime.mini.pos.models.respone.ItemUnitResponse;
import co.prime.mini.pos.repository.ItemUnitRepository;
import co.prime.mini.pos.service.ItemUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemUnitServiceImpl implements ItemUnitService {
    private final ItemUnitRepository itemUnitRepository;
    private final ItemUnitMapper itemUnitMapper;
    @Override
    public ItemUnit create(ItemUnit itemUnit) {
        ItemUnit parentEntityUnit = new ItemUnit();
        parentEntityUnit.setItemUnitCode(itemUnit.getItemUnitCode());
        parentEntityUnit.setItemUnitName(itemUnit.getItemUnitName());
        parentEntityUnit.setOperator(itemUnit.getOperator());
        parentEntityUnit.setOperationValue(itemUnit.getOperationValue());

        if(itemUnit.getParent() !=null && itemUnit.getParent().getId() !=null){
            parentEntityUnit.setParent(itemUnit.getParent());
        }

        return itemUnitRepository.save(parentEntityUnit);
    }

    @Override
    public ItemUnit getById(Long id) {
        return itemUnitRepository.findByIdIsAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("ItemUnit",id));
    }
    @Override
    public ItemUnit deleteById(Long id) {
        ItemUnit itemUnit = getById(id);
        itemUnit.setIsDeleted(true);
        ItemUnit  save= itemUnitRepository.save(itemUnit);
        return save;
    }

    @Override
    public ItemUnit update(Long id, ItemUnit unit) {
        ItemUnit itemUnit = getById(id);
        itemUnit.setItemUnitCode(unit.getItemUnitCode());
        itemUnit.setItemUnitName(unit.getItemUnitName());
        itemUnit.setOperator(unit.getOperator());
        itemUnit.setOperationValue(unit.getOperationValue());

        if(unit.getParent() !=null && unit.getParent() !=null){
            ItemUnit parentItemUnit1 = getById(unit.getParent().getId());
        }else {
            unit.setParent(null);
        }
        return itemUnitRepository.save(itemUnit);
    }

    @Override
    public List<ItemUnitResponse> getAllUnit() {
        return itemUnitRepository
                .findAllByParentIsNullAndIsDeletedFalseOrderByIdDesc()
                .stream()
                .map(itemUnitMapper::toDTO)
                .collect(Collectors.toList());
    }
}
