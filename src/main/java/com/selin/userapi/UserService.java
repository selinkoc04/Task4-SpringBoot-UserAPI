package com.selin.userapi;

import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    public User updateUser(Long id, User payload) {
        User u = getUserById(id); // not found -> exception
        if (payload.getName() != null && !payload.getName().isBlank()) {
            u.setName(payload.getName());
        }
        if (payload.getEmail() != null && !payload.getEmail().isBlank()) {
            u.setEmail(payload.getEmail());
        }
        return userRepository.save(u);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}


