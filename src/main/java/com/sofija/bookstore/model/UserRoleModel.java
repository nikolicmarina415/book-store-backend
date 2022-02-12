package com.sofija.bookstore.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role")
public class UserRoleModel {

    @EmbeddedId
    private UserRoleModelId userRoleModelId;

    public UserRoleModel() {
    }

    public UserRoleModelId getUserRoleModelId() {
        return userRoleModelId;
    }

    public void setUserRoleModelId(UserRoleModelId userRoleModelId) {
        this.userRoleModelId = userRoleModelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleModel userRoleModel = (UserRoleModel) o;
        return Objects.equals(userRoleModelId, userRoleModel.userRoleModelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleModelId);
    }
}
