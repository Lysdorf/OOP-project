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

import ua.opnu.practice1_template.entity.ClientEntity;
import ua.opnu.practice1_template.services.ClientServices;

@RestController
@RequestMapping("/client")
public class ClientController {
    
    private final ClientServices clientServices;

    public ClientController(ClientServices clientServices) {
        this.clientServices = clientServices;
    }

    @PostMapping
    public ResponseEntity<ClientEntity> createClient(@RequestBody ClientEntity clientEntity){
        return new ResponseEntity<>(clientServices.createEntity(clientEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        return new ResponseEntity<>(clientServices.getAllClients(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientEntity> updateClient(@PathVariable Long id,@RequestBody ClientEntity clientEntity) {
        return new ResponseEntity<>(clientServices.updateClient(id, clientEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteClient(@PathVariable Long id){
        clientServices.deleteClient(id);
        return HttpStatus.OK;
    }
}
