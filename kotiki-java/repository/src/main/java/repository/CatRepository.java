package repository;

import entity.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<CatEntity, Integer> {
}
