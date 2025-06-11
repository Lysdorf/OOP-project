package ua.opnu.practice1_template.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.opnu.practice1_template.entity.ServiceTypeEntity;

public interface ServiceTypeRepo extends JpaRepository<ServiceTypeEntity, Long>{
}
