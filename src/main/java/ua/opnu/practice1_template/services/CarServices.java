package ua.opnu.practice1_template.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ua.opnu.practice1_template.entity.CarEntity;
import ua.opnu.practice1_template.entity.ClientEntity;
import ua.opnu.practice1_template.repo.CarRepo;
import ua.opnu.practice1_template.repo.ClientRepo;

@Service
public class CarServices {

    private CarRepo carRepo;
    private ClientRepo clientRepo;

    public CarServices(CarRepo carRepo, ClientRepo clientRepo) {
        this.carRepo = carRepo;
        this.clientRepo = clientRepo;
    }

    
    public CarEntity createCar(CarEntity carEntity) {
        ClientEntity client = clientRepo.findById(carEntity.getClient().getId())
            .orElseThrow(() -> new EntityNotFoundException("Client not found id=" + carEntity.getClient().getId()));

        return carRepo.save(CarEntity.builder()
        .client(client)
        .make(carEntity.getMake())
        .model(carEntity.getModel())
        .productionYear(carEntity.getProductionYear())
        .vin(carEntity.getVin())
        .build()
        );
    }

    public CarEntity updateCar(Long id, CarEntity carEntity) {
        CarEntity car = carRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Car not found, id=" + id));

        ClientEntity client = clientRepo.findById(carEntity.getClient().getId())
            .orElseThrow(() -> new EntityNotFoundException("Client not found id=" + carEntity.getClient().getId()));
        car.setClient(client);
        car.setMake(carEntity.getMake());
        car.setModel(carEntity.getModel());
        car.setProductionYear(carEntity.getProductionYear());
        car.setVin(carEntity.getVin());
        return carRepo.save(car);
    }

    public List<CarEntity> getAllCars(){
        return carRepo.findAll();
    }


    public void deleteCar(Long id){
        carRepo.deleteById(id);
    }
}