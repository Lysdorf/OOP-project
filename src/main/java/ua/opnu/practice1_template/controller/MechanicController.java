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

import ua.opnu.practice1_template.entity.MechanicEntity;
import ua.opnu.practice1_template.services.MechanicServices;

@RestController
@RequestMapping("/mechanic")
public class MechanicController {

    private final MechanicServices mechanicServices;

    public MechanicController(MechanicServices mechanicServices){
        this.mechanicServices = mechanicServices;
    }
    
    @PostMapping
    public ResponseEntity<MechanicEntity> createMechanic(@RequestBody MechanicEntity mechanic) {
        return new ResponseEntity<>(mechanicServices.createMechanic(mechanic), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MechanicEntity>> getAllMechanics() {
        return new ResponseEntity<>(mechanicServices.getAllMechanics(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MechanicEntity> updateMechanic(@PathVariable Long id, @RequestBody MechanicEntity mechanic) {
        return new ResponseEntity<>(mechanicServices.updateMechanic(id, mechanic), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteMechanic(@PathVariable Long id){
        mechanicServices.deleteMechanic(id);
        return HttpStatus.OK;
    }
    
}
