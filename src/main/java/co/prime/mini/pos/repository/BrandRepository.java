package co.prime.mini.pos.repository;

import co.prime.mini.pos.models.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

     //When user deleted in filed in db, server show message not found with id but
    //    -- in data in columns found with status true(true:isDeleted)!=no have store recoded data for restore and backup
    //    Noted : on server we can to Auto backup Custom by Head of ID

    Optional<Brand> findByIdAndIsDeletedFalse(Long id);
    List<Brand> findByIsDeletedIsFalseOrderByIdDesc();
    Page<Brand> findByIsDeletedIsFalseOrderByIdDesc(Pageable page);

}
