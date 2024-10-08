package co.prime.mini.pos.repository;

import co.prime.mini.pos.models.entity.GeneralSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralSettingRepository extends JpaRepository<GeneralSetting, Long> {

    Optional<GeneralSetting> findByIdAndIsDeletedFalse(Long id);
    List<GeneralSetting> findByIsDeletedIsFalseOrderByIdDesc();
    Page<GeneralSetting> findByIsDeletedIsFalseOrderByIdDesc(Pageable pageable);
}
