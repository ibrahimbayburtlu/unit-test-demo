package com.ibrahimbayburtlu.unit_test_demo.repository;

import com.ibrahimbayburtlu.unit_test_demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
