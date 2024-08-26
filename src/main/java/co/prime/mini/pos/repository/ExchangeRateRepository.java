package co.prime.mini.pos.repository;

import co.prime.mini.pos.model.entity.Customer;
import co.prime.mini.pos.model.entity.ExchangeRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findByIdAndIsDeletedFalse(Long id);
    List<ExchangeRate> findByIsDeletedIsFalseOrderByIdDesc();
    Page<ExchangeRate> findByIsDeletedIsFalseOrderByIdDesc(Pageable pageable);
}
