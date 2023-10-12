package com.javatechie.redis;

import com.javatechie.redis.entity.Employee;
import com.javatechie.redis.respository.EmployeeDao;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataRedisExampleApplicationTests {
	@Autowired
	private EmployeeDao dao;
	@Test
	public void save(){
		Employee employee = dao.save(new Employee(12,"kk","jojo",565));
		Assert.assertEquals(12,employee);
	}
	@Test
	public void addme(){
		int a =1;
		int b= 4;
		int c = a+b;
		Assert.assertEquals(5,c);
	}

	@Test
	public void  findEmployee() {
		Employee employee = dao.findEmployeeById(98656);
		Assert.assertEquals(98656,employee.getId());

	}



	@Test
	public void remove(){
		Assert.assertEquals(98656, 98656);
	}

}
