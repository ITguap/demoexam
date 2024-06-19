package org.example.demoexam.service;

import lombok.RequiredArgsConstructor;
import org.example.demoexam.model.Role;
import org.example.demoexam.model.User;
import org.example.demoexam.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void save(User user) {
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
