package ua.opnu.practice1_template.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ua.opnu.practice1_template.entity.ServiceTypeEntity;
import ua.opnu.practice1_template.repo.ServiceTypeRepo;

@Service
public class ServiceTypeServices {

    private final ServiceTypeRepo serviceTypeRepo;

    public ServiceTypeServices(ServiceTypeRepo serviceTypeRepo){
        this.serviceTypeRepo = serviceTypeRepo;
    }
    
    public ServiceTypeEntity createServiceType(ServiceTypeEntity serviceTypeEntity){
        return serviceTypeRepo.save(ServiceTypeEntity.builder()
        .name(serviceTypeEntity.getName())
        .standardPrice(serviceTypeEntity.getStandardPrice())
        .build());
    }

    public List<ServiceTypeEntity> getAllServiceType() {
        return serviceTypeRepo.findAll();
    }

    public ServiceTypeEntity udateTypeEntity(Long id,ServiceTypeEntity serviceTypeEntity){
        ServiceTypeEntity serviceType = serviceTypeRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Service type  not found, id=" + id));
            serviceType.setName(serviceTypeEntity.getName());
            serviceType.setStandardPrice(serviceTypeEntity.getStandardPrice());
        return serviceTypeRepo.save(serviceType);
    }

    public void deleteServiceType(Long id){
        serviceTypeRepo.deleteById(id);
    }
}
