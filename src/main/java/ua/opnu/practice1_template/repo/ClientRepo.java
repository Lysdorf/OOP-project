package ua.opnu.practice1_template.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.opnu.practice1_template.entity.ClientEntity;

@Repository
public interface ClientRepo extends JpaRepository<ClientEntity, Long>{
    boolean existsByUsername(String username);
    
    Optional<ClientEntity> findByUsername(String username);
}