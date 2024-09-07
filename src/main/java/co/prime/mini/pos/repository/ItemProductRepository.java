package co.prime.mini.pos.repository;

import co.prime.mini.pos.models.entity.ItemProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemProductRepository extends JpaRepository<ItemProduct, Long> {

}
