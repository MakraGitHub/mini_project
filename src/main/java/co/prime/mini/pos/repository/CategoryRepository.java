package co.prime.mini.pos.repository;

import co.prime.mini.pos.models.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByIdIsAndIsDeletedFalse(Long id);
  List<Category> findByIsDeletedIsFalseOrderByIdDesc();

  Page<Category> findByIsDeletedIsFalseOrderByIdDesc(Pageable pageable);
}
