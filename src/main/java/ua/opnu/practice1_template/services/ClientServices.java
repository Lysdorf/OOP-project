package ua.opnu.practice1_template.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ua.opnu.practice1_template.entity.ClientEntity;
import ua.opnu.practice1_template.repo.ClientRepo;

@Service
public class ClientServices {

    private final ClientRepo clientRepo;

    public ClientServices(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public ClientEntity createEntity(ClientEntity clientEntity) {
        return clientRepo.save(ClientEntity.builder()
        .name(clientEntity.getName())
        .phone(clientEntity.getPhone())
        .email(clientEntity.getEmail())
        .build()
        );
    }

    public List<ClientEntity> getAllClients() {
        return clientRepo.findAll();
    }

    public ClientEntity updateClient(Long id, ClientEntity clientEntity) {
        ClientEntity client = clientRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client not found, id=" + id));
        client.setName(clientEntity.getName());
        client.setPhone(clientEntity.getPhone());
        client.setEmail(clientEntity.getEmail());
        return clientRepo.save(client);
    }

    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }
}
