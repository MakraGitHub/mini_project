package co.prime.mini.pos.repository;

import co.prime.mini.pos.models.entity.Category;
import co.prime.mini.pos.models.entity.ItemUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemUnitRepository extends JpaRepository<ItemUnit, Long> {
    Optional<ItemUnit> findByIdIsAndIsDeletedFalse(Long id);
    List<ItemUnit> findAllByParentIsNullAndIsDeletedFalseOrderByIdDesc();
    Page<ItemUnit> findAllByParentIsNullAndIsDeletedFalseOrderByIdDesc(Pageable pageable);
}
