package ua.opnu.practice1_template.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.opnu.practice1_template.entity.ClientEntity;
import ua.opnu.practice1_template.repo.ClientRepo;

@Service
public class ClientDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepo clientRepo;

    @Override
    public ClientEntity loadUserByUsername(String username) throws UsernameNotFoundException {
    return clientRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
