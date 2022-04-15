package com.example.walletapi.repository;

import com.example.walletapi.model.LoginModel;
import com.example.walletapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

    User findOneByEmailIgnoreCaseAndPassword(String email, String password);
}
