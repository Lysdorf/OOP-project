package ua.opnu.practice1_template.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ua.opnu.practice1_template.entity.MechanicEntity;
import ua.opnu.practice1_template.repo.MechanicRepo;

@Service
public class MechanicServices {

    private final MechanicRepo mechanicRepo;

    public MechanicServices(MechanicRepo mechanicRepo) {
        this.mechanicRepo = mechanicRepo;
    }
    
    public MechanicEntity createMechanic(MechanicEntity mechanic){
        return mechanicRepo.save(MechanicEntity.builder()
            .name(mechanic.getName())
            .specialization(mechanic.getSpecialization())
            .build()
        );
    }

    public List<MechanicEntity> getAllMechanics(){
        return mechanicRepo.findAll();
    }

    public MechanicEntity updateMechanic(Long id, MechanicEntity mechanicEntity) {
        MechanicEntity mechanic = mechanicRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client not found, id=" + id));
        mechanic.setName(mechanicEntity.getName());
        mechanic.setSpecialization(mechanicEntity.getSpecialization());
        return mechanicRepo.save(mechanic);
    }

    public void deleteMechanic(Long id){
        mechanicRepo.deleteById(id);
    }
}
