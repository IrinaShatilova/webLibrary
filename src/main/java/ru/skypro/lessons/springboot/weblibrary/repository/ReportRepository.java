package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.model.Report;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {

    @Query(value = "SELECT p.position as position, count(e.id) as count, max(e.salary) as maxSalary,\n" +
            "min(e.salary) as minSalary, avg(e.salary) as averageSalary\n" +
            "FROM employees e\n" +
            "JOIN positions p ON e.position_id = p.position_id \n" +
            "GROUP BY p.position", nativeQuery = true)
    List<Object[]> getReport();
}

