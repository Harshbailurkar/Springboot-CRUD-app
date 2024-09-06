package com.harsh.ecommers.Repository;
import com.harsh.ecommers.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
}
