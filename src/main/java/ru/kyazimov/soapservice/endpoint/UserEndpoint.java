package ru.kyazimov.soapservice.endpoint;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.kyazimov.soapservice.entity.Role;
import ru.kyazimov.soapservice.entity.User;
import ru.kyazimov.soapservice.gs_ws.*;
import ru.kyazimov.soapservice.service.RoleService;
import ru.kyazimov.soapservice.service.UserService;
import ru.kyazimov.soapservice.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.kyazimov.soapservice.validation.UserValidation.*;

@Endpoint
public class UserEndpoint {
    public static final String NAMESPACE_URI = "http://soapservice.kyazimov.ru/users-ws";

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserEndpoint(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
        AddUserResponse response = new AddUserResponse();
        isUserAlreadyExist(userService.getUserByLogin(request.getUsers().getLogin()));
        User user = new User();
        userValidation(request.getUsers().getLogin(), request.getUsers().getPassword(), request.getUsers().getName());
        user.setLogin(request.getUsers().getLogin());
        user.setName(request.getUsers().getName());
        user.setPassword(request.getUsers().getPassword());
        Set<ru.kyazimov.soapservice.entity.Role> roleSet = new HashSet<>();
        request.getUsers().getRoleID()
                .forEach(id -> roleSet.add(roleService.getRoleById(id)));
        user.setRoles(roleSet);
        userService.addUser(user);
        response.setMessage("User has been created successfully");

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByLoginRequest")
    @ResponsePayload
    public GetUserByLoginResponse getUserByLogin(@RequestPayload GetUserByLoginRequest request) {
        isUserExist(userService.getUserByLogin(request.getLogin()));
        GetUserByLoginResponse response = new GetUserByLoginResponse();
        Users user = new Users();
        BeanUtils.copyProperties(userService.getUserByLogin(request.getLogin()), user);
        response.setLogin(user.getLogin());
        response.setName(user.getName());
        response.setPassword(user.getPassword());
        List<String> roleList = new ArrayList<>();
        userService.getUserByLogin(request.getLogin()).getRoles()
                .forEach(role -> roleList.add(role.getName()));
        response.getRoles().addAll(roleList);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserByLoginRequest")
    @ResponsePayload
    public UpdateUserByLoginResponse updateUser(@RequestPayload UpdateUserByLoginRequest request) {
        isUserExist(userService.getUserByLogin(request.getUsers().getLogin()));
        userValidation(request.getUsers().getLogin(), request.getUsers().getPassword(), request.getUsers().getName());
        UpdateUserByLoginResponse response = new UpdateUserByLoginResponse();
        Users data = request.getUsers();

        if (request.getUsers().getRoleID().size() != 0) {
            Set<Role> roleSet = new HashSet<>();
            data.getRoleID()
                    .forEach(id -> roleSet.add(roleService.getRoleById(id)));
            User toUpdate = userService.getUserByLogin(data.getLogin());
            toUpdate.setRoles(roleSet);
            toUpdate.setName(data.getName());
            toUpdate.setPassword(data.getPassword());
            userService.addUser(toUpdate);
        } else {
            User toUpdate = userService.getUserByLogin(data.getLogin());
            toUpdate.setName(data.getName());
            toUpdate.setPassword(data.getPassword());
            userService.addUser(toUpdate);
        }

        response.setMessage("User " + data.getLogin() + " updated successfully");

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        List<User> listUsers = userService.getAllUsers();
        List<String> result = new ArrayList<>();
        listUsers.forEach(users -> result.add(users.getName()));
        GetAllUsersResponse response = new GetAllUsersResponse();
        response.getUsers().addAll(result);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserByLoginRequest")
    @ResponsePayload
    public DeleteUserByLoginResponse deleteUserByLogin(@RequestPayload DeleteUserByLoginRequest request) {
        isUserExist(userService.getUserByLogin(request.getLogin()));
        DeleteUserByLoginResponse response = new DeleteUserByLoginResponse();
        userService.deleteUserByLogin(request.getLogin());
        response.setMessage("User with login: " + request.getLogin() + " was deleted successfully");

        return response;
    }
}