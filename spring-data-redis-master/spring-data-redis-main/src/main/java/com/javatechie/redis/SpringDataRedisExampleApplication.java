package com.javatechie.redis;

import com.javatechie.redis.entity.Employee;
import com.javatechie.redis.respository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Response;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employee")
@EnableCaching
public class SpringDataRedisExampleApplication {
    @Autowired
    private EmployeeDao dao;

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return dao.save(employee);
    }
   /* @PostMapping
    public String saveListOfEmployees(@RequestBody List<Employee> employeeList){
        for (Employee emp:employeeList) {
            dao.save(emp);
        }
        return "employee list addded successfuly....";
    }*/

    @PutMapping
    public Employee update(@RequestBody Employee employee){
        return dao.update(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Employee")
    public Employee findEmployee(@PathVariable int id) {
            return dao.findEmployeeById(id);
    }


    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Employee")
    public String remove(@PathVariable int id)   {
        return dao.deleteEmployee(id);
	}


    public static void main(String[] args) {

        SpringApplication.run(SpringDataRedisExampleApplication.class, args);
    }

}
