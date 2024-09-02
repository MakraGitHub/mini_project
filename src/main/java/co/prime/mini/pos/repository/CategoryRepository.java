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
  //Mybatis like this @Select("SELECT * FROM Category WHERE parent_id IS NULL AND is_deleted = FALSE ORDER BY id DESC")
  List<Category> findAllByParentIsNullAndIsDeletedFalseOrderByIdDesc();
  Page<Category> findAllByParentIsNullAndIsDeletedFalseOrderByIdDesc(Pageable pageable);
}
