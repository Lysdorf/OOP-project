package ua.opnu.practice1_template.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.opnu.practice1_template.entity.CarEntity;

@Repository
public interface CarRepo extends JpaRepository<CarEntity, Long>{
    
}
