package com.anubhav.apitutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class EmployeeResource {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/employee")
    public List<Employee> getData(){
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        return employeeList;
    }

    @RequestMapping(value= "/id")
    public Optional<Employee> searchData(@RequestParam int id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;

    }

    @RequestMapping(
            value = "new",
            method = RequestMethod.POST,
            consumes = {"application/JSON"}
    )
    public Employee enterData(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return employee;
    }



    @RequestMapping(
            value = "update",
            method = RequestMethod.PUT,
            consumes = {"application/JSON"}
    )
    public Employee updateData(@RequestBody Employee employee){
        if( employeeRepository.existsById(employee.getId()) ){

            Employee employee1 = new Employee();
            employee1.setId(employee.getId());
            employee1.setName(employee.getName());
            employeeRepository.save(employee1);
            return employee;
        }
        return null;
    }



    @RequestMapping(
            value = "remove",
            method = RequestMethod.DELETE
    )
    public Optional<Employee> deleteData(@RequestParam int id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee!=null){
            employeeRepository.deleteById(id);
            return employee;
        }


        return null;
    }

}
