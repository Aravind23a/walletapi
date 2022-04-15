package com.example.walletapi.service;

import com.example.walletapi.model.User;
import com.example.walletapi.repository.LoginRepository;
import com.example.walletapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String signUp(User user) {
      String status = "";
        try {
            List<User> userList = loginRepository.findAll();
            AtomicBoolean userExist = checkIfUserExist(user, userList);
            if (!userExist.get()) {
                user.setIsActive(Constants.YES);
                user.setDateCreated(LocalDateTime.now());
                user.setLastUpdated(LocalDateTime.now());
                loginRepository.save(user);
                status = Constants.SUCCESS;
            } else {
                status = Constants.USER_ALREADY_EXISTS;
            }
        } catch (Exception exc) {
            status = Constants.FAILURE;
        }
        return status;
    }

    private AtomicBoolean checkIfUserExist(User user, List<User> userList) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (user != null && !CollectionUtils.isEmpty(userList)) {
            userList.forEach( record -> {
                String email = record.getEmail();
                if (email.equalsIgnoreCase(user.getEmail())) {
                    result.set(true);
                }
            });
        }
        return result;
    }
}
