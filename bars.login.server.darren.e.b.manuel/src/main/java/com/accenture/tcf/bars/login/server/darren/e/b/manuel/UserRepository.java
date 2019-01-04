package com.accenture.tcf.bars.login.server.darren.e.b.manuel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    User findByUserId(int userId);
    User findByUserName(String userName);
    User findByRole(String role);
    boolean existsByUserId(int userId);
    void deleteByUserId(int userId);
}
