package co.prime.mini.pos.repository;

import co.prime.mini.pos.models.entity.Brand;
import co.prime.mini.pos.models.entity.ItemProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemProductRepository extends JpaRepository<ItemProduct, Long> {
    Optional<ItemProduct> findByIdAndIsDeletedFalse(Long id);
    List<ItemProduct> findByIsDeletedIsFalseOrderByIdDesc();
    Page<ItemProduct> findByIsDeletedIsFalseOrderByIdDesc(Pageable page);
}
