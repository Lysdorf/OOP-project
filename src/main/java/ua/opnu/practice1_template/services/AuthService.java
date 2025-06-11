package ua.opnu.practice1_template.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ua.opnu.practice1_template.entity.ClientEntity;
import ua.opnu.practice1_template.repo.ClientRepo;

@Service
@AllArgsConstructor
public class AuthService {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;

    public ClientEntity register(String username, String rawPassword) {

        if (clientRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("Username is already taken");
        }
        ClientEntity client = new ClientEntity();
        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(rawPassword));

        return clientRepo.save(client);
    }
}
