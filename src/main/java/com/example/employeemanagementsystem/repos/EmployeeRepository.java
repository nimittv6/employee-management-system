package com.example.employeemanagementsystem.repos;

import com.example.employeemanagementsystem.domains.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByFirstName(String firstName);
    Optional<Employee> findByLastName(String lastName);

    @Transactional
    @Modifying
    @Query(value = "delete from Employee emp WHERE SubString(cast(emp.joiningDate as text)1,4) =:joiningYear")
    void deleteByJoiningYear(@Param("joiningYear")String joiningYear);
}
