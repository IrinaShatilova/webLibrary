package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer> {

    @Query(value = "SELECT * FROM employees", nativeQuery = true)
    List<Employee> findAllEmployees();

    @Query(value = "SELECT * FROM employees WHERE salary > :salary", nativeQuery = true)
    List<Employee> getEmployeesWithSalaryHigherThan(Integer salary);

    @Query(value = "SELECT * FROM employees WHERE salary = (SELECT MAX(salary) FROM employees)",nativeQuery = true )
    List<Employee> getEmployeesWithHighestSalary();

    @Query(value = "SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.name, e.salary, p.position) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p AND e.id = ?1")
    EmployeeFullInfo getEmployeeFullInfoById(int id);
}
