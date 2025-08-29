package com.bajaj.service;

import org.springframework.stereotype.Service;

@Service
public class SqlProblemSolver {
    
    public String solveProblem(String regNo) {
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastDigits = Integer.parseInt(lastTwoDigits);
        
        if (lastDigits % 2 == 0) {
            return solveQuestion2();
        } else {
            return solveQuestion1();
        }
    }
    
    private String solveQuestion1() {
        return "SELECT e.employee_id, e.first_name, e.last_name, d.department_name, " +
               "AVG(s.salary) OVER (PARTITION BY e.department_id) as avg_dept_salary " +
               "FROM employees e " +
               "JOIN departments d ON e.department_id = d.department_id " +
               "JOIN salaries s ON e.employee_id = s.employee_id " +
               "WHERE s.salary > (SELECT AVG(salary) FROM salaries) " +
               "ORDER BY e.department_id, s.salary DESC";
    }
    
    private String solveQuestion2() {
        return "SELECT c.customer_id, c.customer_name, " +
               "COUNT(o.order_id) as total_orders, " +
               "SUM(o.order_amount) as total_amount, " +
               "AVG(o.order_amount) as avg_order_amount " +
               "FROM customers c " +
               "LEFT JOIN orders o ON c.customer_id = o.customer_id " +
               "WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) " +
               "GROUP BY c.customer_id, c.customer_name " +
               "HAVING total_orders >= 3 " +
               "ORDER BY total_amount DESC";
    }
}
