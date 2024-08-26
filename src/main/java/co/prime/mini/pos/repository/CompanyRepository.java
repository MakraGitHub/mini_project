package co.prime.mini.pos.repository;

import co.prime.mini.pos.model.entity.Brand;
import co.prime.mini.pos.model.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByIdAndIsDeletedFalse(Long id);
    List<Company> findByIsDeletedIsFalseOrderByIdDesc();
    Page<Company> findByIsDeletedIsFalseOrderByIdDesc(Pageable page);
}
