package com.rect2m.componentsforpc.persistence.repository.contracts;

import com.rect2m.componentsforpc.persistence.entity.impl.User;
import com.rect2m.componentsforpc.persistence.repository.Repository;
import java.util.Optional;

/**
 * Ми визначаємо з яким ентіті працювати (убираємо дженерік). Ми додаємо "кастомні" методі, які
 * актульні лише для цієї сутності!
 */
public interface UserRepository extends Repository<User> {

    Optional<User> findByUserName(String username);

    Optional<User> findByPhoneNumber(String phoneNumber);
}