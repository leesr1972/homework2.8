package sky.pro.java.course2.homework2_8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.java.course2.homework2_8.data.Employee;
import sky.pro.java.course2.homework2_8.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addNewEmployee(@RequestParam String lastName,
                                 @RequestParam String firstName,
                                 @RequestParam Float salary,
                                 @RequestParam Integer deprtmentId) {
        Employee newEmployee = employeeService.addEmployee(lastName, firstName, salary, deprtmentId);
        return newEmployee.getLastName() + " " + newEmployee.getFirstName() + " is hired.";
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee disMissedEmployee = employeeService.dismissEmployee(firstName, lastName);
        return disMissedEmployee.getLastName() + " " + disMissedEmployee.getFirstName() + " is dismissed.";
    }

    @GetMapping("/find")
    public String findStaff(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = employeeService.findEmloyee(firstName, lastName);
        return employee.getLastName() + " " + employee.getFirstName() + " is found.";
    }

    @GetMapping("/print")
    public String printAllStaff() {
        return employeeService.getAllStaff();
    }
}
