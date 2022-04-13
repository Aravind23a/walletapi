package com.example.walletapi.service;

import com.example.walletapi.model.User;
import com.example.walletapi.repository.LoginRepository;
import com.example.walletapi.util.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

@Service
public class LoginService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoginService.class);

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String signUp(User user) {
        String result = "";
        try {
            List<User> userList = loginRepository.findAll();
            AtomicBoolean userExist = checkIfUserExist(user, userList);
            if (!userExist.get()) {
                loginRepository.save(user);
                result = Constants.SUCCESS;
            } else {
                result = Constants.USER_ALREADY_EXISTS;
            }
        } catch (Exception exc) {
            LOGGER.info("Exception in signUp Service");
            result = Constants.FAILURE;
        }
        return result;
    }

    private AtomicBoolean checkIfUserExist(User user, List<User> userList) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (user != null && !CollectionUtils.isEmpty(userList)) {
            userList.forEach( record -> {
                if (record.equals(user)) {
                    LOGGER.info("User Already exists!");
                    result.set(true);
                }
            });
        }
        return result;
    }
}
