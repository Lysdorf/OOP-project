package ua.opnu.practice1_template.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import ua.opnu.practice1_template.entity.CarEntity;
import ua.opnu.practice1_template.entity.MechanicEntity;
import ua.opnu.practice1_template.entity.ServiceRecordEntity;
import ua.opnu.practice1_template.entity.ServiceTypeEntity;
import ua.opnu.practice1_template.repo.CarRepo;
import ua.opnu.practice1_template.repo.MechanicRepo;
import ua.opnu.practice1_template.repo.ServiceRecordRepo;
import ua.opnu.practice1_template.repo.ServiceTypeRepo;

@Service
public class ServiceRecordServices {
    
    private final ServiceRecordRepo serviceRecordRepo;

    private final ServiceTypeRepo serviceTypeRepo;

    private final CarRepo carRepo;

    private final MechanicRepo mechanicRepo;

    public ServiceRecordServices(ServiceRecordRepo serviceRecordRepo, ServiceTypeRepo serviceTypeRepo, CarRepo carRepo, MechanicRepo mechanicRepo) {
        this.serviceRecordRepo = serviceRecordRepo;
        this.serviceTypeRepo = serviceTypeRepo;
        this.carRepo = carRepo;
        this.mechanicRepo = mechanicRepo;
    }
    

    @Transactional
    public ServiceRecordEntity createServiceRecord(ServiceRecordEntity serviceRecord) {
        CarEntity car = carRepo.findById(serviceRecord.getCar().getId())
            .orElseThrow(() -> new EntityNotFoundException("Car not found id=" + serviceRecord.getCar().getId()));
        MechanicEntity mech = mechanicRepo.findById(serviceRecord.getMechanic().getId())
            .orElseThrow(() -> new EntityNotFoundException("Mechanic not found id=" + serviceRecord.getMechanic().getId()));

        Set<ServiceTypeEntity> types = serviceRecord.getServiceTypes().stream()
            .map(t -> serviceTypeRepo.findById(t.getId())
                .orElseThrow(() -> new EntityNotFoundException("ServiceType not found id=" + t.getId()))
            )
            .collect(Collectors.toSet());

        ServiceRecordEntity rec = ServiceRecordEntity.builder()
            .car(car)
            .mechanic(mech)
            .serviceTypes(types)
            .date(serviceRecord.getDate())
            .description(serviceRecord.getDescription())
            .build();

        return serviceRecordRepo.save(rec);
    }

    @Transactional
    public ServiceRecordEntity updateServiceRecord(Long id, ServiceRecordEntity in) {
        ServiceRecordEntity existing = serviceRecordRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Record not found id=" + id));

        CarEntity car = carRepo.getReferenceById(in.getCar().getId());
        MechanicEntity mech = mechanicRepo.getReferenceById(in.getMechanic().getId());
        Set<ServiceTypeEntity> types = in.getServiceTypes().stream()
            .map(t -> serviceTypeRepo.getReferenceById(t.getId()))
            .collect(Collectors.toSet());

        ServiceRecordEntity updated = existing.toBuilder()
            .car(car)
            .mechanic(mech)
            .serviceTypes(types)
            .date(in.getDate())
            .description(in.getDescription())
            .build();

        return serviceRecordRepo.save(updated);
    }

    public void deleteServiceRecord(Long id){
        serviceRecordRepo.deleteById(id);
    }

    @Transactional
    public void assignType(Long recordId, Long typeId) {
        ServiceRecordEntity rec = serviceRecordRepo.findById(recordId)
            .orElseThrow(() -> new EntityNotFoundException("Record not found"));
        ServiceTypeEntity type = serviceTypeRepo.findById(typeId)
            .orElseThrow(() -> new EntityNotFoundException("ServiceType not found"));
        rec.getServiceTypes().add(type);
    }

    public BigDecimal totalPrice(Long id){
        return serviceRecordRepo.findTotalServiceCostByCarId(id); 
    }

    public List<ServiceRecordEntity> findAllByMechanicId(Long id){
        return serviceRecordRepo.findAllByMechanicId(id);
    }

    public List<ServiceRecordEntity> getAllServiceRecords() {
        return serviceRecordRepo.findAll();
    }

    public List<ServiceRecordEntity> getRecordsBetweenDates(LocalDate start, LocalDate end) {
        return serviceRecordRepo.findByDateBetween(start, end);
    }

    public List<CarEntity> getTopNCars(int n) {
        return serviceRecordRepo.findTopCarsByServiceCount(PageRequest.of(0, n));
    }

    public List<ServiceTypeEntity> getTopNServiceTypes(int n) {
        Pageable pageable = PageRequest.of(0, n);
        return serviceRecordRepo.findTopServiceTypes(pageable);
    }
}
