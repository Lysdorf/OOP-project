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

import ua.opnu.practice1_template.entity.ServiceTypeEntity;
import ua.opnu.practice1_template.services.ServiceTypeServices;

@RestController
@RequestMapping("/servicetype")
public class ServiceTypeController {
    
    private final ServiceTypeServices serviceTypeServices;

    public ServiceTypeController(ServiceTypeServices serviceTypeServices) {
        this.serviceTypeServices = serviceTypeServices;
    }

    @PostMapping
    public ResponseEntity<ServiceTypeEntity> createMechanic(@RequestBody ServiceTypeEntity servicerecord) {
        return new ResponseEntity<>(serviceTypeServices.createServiceType(servicerecord), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServiceTypeEntity>> getAllMechanics() {
        return new ResponseEntity<>(serviceTypeServices.getAllServiceType(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceTypeEntity> updateMechanic(@PathVariable Long id, @RequestBody ServiceTypeEntity servicerecord) {
        return new ResponseEntity<>(serviceTypeServices.udateTypeEntity(id, servicerecord), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteMechanic(@PathVariable Long id){
        serviceTypeServices.deleteServiceType(id);
        return HttpStatus.OK;
    }
}