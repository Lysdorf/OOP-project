package ua.opnu.practice1_template.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.opnu.practice1_template.entity.CarEntity;
import ua.opnu.practice1_template.services.CarServices;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarServices carServices;

    public CarController(CarServices carServices) {
        this.carServices = carServices;
    }

    @PostMapping
    public ResponseEntity<CarEntity> createCar(@RequestBody CarEntity carEntity){
        return new ResponseEntity<>(carServices.createCar(carEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarEntity>> getAllCars() {
        return new ResponseEntity<>(carServices.getAllCars(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarEntity> updateCar(@PathVariable Long id, @RequestBody CarEntity carEntity) {
        return new ResponseEntity<>(carServices.updateCar(id, carEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteClient(@PathVariable Long id){
        carServices.deleteCar(id);
        return HttpStatus.OK;
    }
}
    