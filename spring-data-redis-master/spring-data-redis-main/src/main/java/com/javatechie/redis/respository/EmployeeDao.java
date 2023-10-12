package com.javatechie.redis.respository;

import com.javatechie.redis.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    public static final String HASH_KEY = "Employee";
    @Autowired
    private RedisTemplate template;

    public Employee save(Employee employee) {
        template.opsForHash().put(HASH_KEY, employee.getId(), employee);
        return employee;
    }
  //  public List<Employee> saveListOfEmployees(){
      //  return template.opsForHash().values(HASH_KEY);
  //  }
    public Employee update(Employee employee){
        template.opsForHash().put(HASH_KEY,employee.getId(),employee);
        return employee;
    }
    public List<Employee> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Employee findEmployeeById(int id) {
        System.out.println("called findEmployeeById() from DB");
        return (Employee) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteEmployee(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        System.out.println("called from deleteEmployee() from DB");
        return "product removed !!";
    }

}
