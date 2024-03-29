package com.rect2m.componentsforpc.persistence.entity;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {

    protected UUID id;

    protected Entity(UUID id) {
        this.id = id;
    }
    
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}