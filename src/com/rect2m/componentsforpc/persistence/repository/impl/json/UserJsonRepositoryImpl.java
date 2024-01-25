package com.rect2m.componentsforpc.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rect2m.componentsforpc.persistence.entity.impl.User;
import com.rect2m.componentsforpc.persistence.repository.contracts.UserRepository;
import java.util.Optional;
import java.util.Set;

public class UserJsonRepositoryImpl
        extends GenericJsonRepository<User>
        implements UserRepository {

    public UserJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.USERS.getPath(), TypeToken
                .getParameterized(Set.class, User.class)
                .getType());
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return entities.stream().filter(u -> u.getUserName().equals(username)).findFirst();
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return entities.stream().filter(u -> u.getPhoneNumber().equals(phoneNumber)).findFirst();
    }
}
