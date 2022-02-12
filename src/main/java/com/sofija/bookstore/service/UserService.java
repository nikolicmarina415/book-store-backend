package com.sofija.bookstore.service;

import com.sofija.bookstore.constants.Constants;
import com.sofija.bookstore.exception.UserException;
import com.sofija.bookstore.model.*;
import com.sofija.bookstore.repository.RoleRepository;
import com.sofija.bookstore.repository.UserRepository;
import com.sofija.bookstore.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRoleRepository userRoleRepository;

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public UserModel getById(int userId) throws UserException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
    }

    public UserModel register(UserModel userModel) throws UserException {
        if (userRepository.findByEmail(userModel.getEmail()) != null) {
            throw new UserException("User already exists with the given email");
        }

        UserModel registeredUserModel = userRepository.save(userModel);
        addRoleToUser(Constants.Role.CUSTOMER, registeredUserModel.getId());
        return registeredUserModel;
    }

    public UserModel login(String email, String password) throws UserException {
        UserModel userModel = userRepository.findByEmailAndPassword(email, password);
        if (userModel == null) {
            throw new UserException("Invalid credentials");
        }
        return userModel;
    }

    public boolean isAdmin(int userId) {
        return userContainsRole(userId, Constants.Role.ADMIN);
    }

    public boolean isGoldenCustomer(int userId) {
        return userContainsRole(userId, Constants.Role.GOLDEN_CUSTOMER);
    }

    private boolean userContainsRole(int userId, String roleName) {
        try {
            return getById(userId).getRoleModels()
                    .stream()
                    .anyMatch(roleModel -> roleModel.getName().equals(roleName));
        } catch (UserException ex) {
            return false;
        }
    }

    public void addRoleToUser(String roleName, int userId) {
        RoleModel roleModel = roleRepository.findByName(roleName);
        UserRoleModelId userRoleModelId = new UserRoleModelId(userId, roleModel.getId());
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setUserRoleModelId(userRoleModelId);
        userRoleRepository.save(userRoleModel);
    }

    public UserModel getAnonymousUser() {
        return userRepository.findByEmail("anonymous");
    }
}
