package com.rect2m.componentsforpc.domain.impl;

import com.rect2m.componentsforpc.domain.exception.AuthException;
import com.rect2m.componentsforpc.domain.exception.UserAlreadyAuthException;
import com.rect2m.componentsforpc.persistence.entity.impl.User;
import com.rect2m.componentsforpc.persistence.repository.contracts.UserRepository;
import org.mindrot.bcrypt.BCrypt;

public class AuthService {

    private final UserRepository userRepository;
    private User user;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        // Перевіряємо, чи вже існує аутентифікований користувач
        if (user != null) {
            throw new UserAlreadyAuthException("Ви вже авторизувалися як: %s"
                    .formatted(user.getUserName()));
        }

        User foundedUser = userRepository.findByUserName(username)
                .orElseThrow(AuthException::new);

        if (!BCrypt.checkpw(password, foundedUser.getPassword())) {
            return false;
        }

        user = foundedUser;
        return true;
    }

    public boolean isAuthenticated() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void logout() {
        if (user == null) {
            throw new UserAlreadyAuthException("Ви ще не автентифіковані.");
        }
        user = null;
    }
}