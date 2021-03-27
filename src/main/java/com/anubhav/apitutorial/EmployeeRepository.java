package com.anubhav.apitutorial;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
