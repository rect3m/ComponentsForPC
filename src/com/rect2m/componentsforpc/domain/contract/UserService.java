package com.rect2m.componentsforpc.domain.contract;

import com.rect2m.componentsforpc.domain.Service;
import com.rect2m.componentsforpc.persistence.entity.impl.User;
import java.util.Optional;

public interface UserService extends Service<User> {

    Optional<User> findByUserName(String username);

    Optional<User> findByPhoneNumber(String phoneNumber);
}