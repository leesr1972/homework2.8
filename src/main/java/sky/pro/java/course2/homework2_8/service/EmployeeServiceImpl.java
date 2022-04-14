package sky.pro.java.course2.homework2_8.service;

import org.springframework.stereotype.Service;
import sky.pro.java.course2.homework2_8.data.Employee;
import sky.pro.java.course2.homework2_8.exceptions.BadRequest;
import sky.pro.java.course2.homework2_8.exceptions.NotFound;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> staffOfEmployee = new HashMap<>(Map.of(
        "ПетровЮрий", new Employee("Петров", "Юрий", 200_000f, 0),
        "ЯкобсонИосиф", new Employee("Якобсон", "Иосиф", 180_000f, 0),
        "СтивенДжексон", new Employee("Стивен", "Джексон", 180_000f, 1),
        "КимМария", new Employee("Ким", "Мария", 150_000f, 1),
        "МансуроваАмира", new Employee("Мансурова", "Амира", 150_000f, 2),
        "ШевченкоИрина", new Employee("Шевченко", "Ирина", 120_000f, 2),
        "ЯцехироАнимото", new Employee("Яцехиро", "Анимото", 180_000f, 3),
        "ДорошенкоМатвей", new Employee("Дорошенко", "Матвей", 150_000f, 3),
        "АбдуллаевБахром", new Employee("Абдуллаев", "Бахром", 100_000f, 3),
        "ГригорянАшот", new Employee("Григорян", "Ашот", 180_000f, 4),
        "БочорошвилиГеоргий", new Employee("Бочорошвили", "Георгий", 120_000f, 4)
    ));

    private List<String> departments = new ArrayList<>(List.of("Руководство", "Бухгалтерия",
            "Отдел кадров", "Технический отдел", "Отдел продаж"));

    @Override
    public Employee addEmployee(String lastName, String firstName, Float salary, Integer departmentId) {
        Employee newEmployee = new Employee(lastName, firstName, salary, departmentId);
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            throw new BadRequest();
        } else {
            staffOfEmployee.put(lastName + firstName, newEmployee);
            return newEmployee;
        }
    }

    @Override
    public Employee dismissEmployee(String lastName, String firstName) {
        Employee dismissedEmployee = staffOfEmployee.get(lastName + firstName);
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            staffOfEmployee.remove(lastName + firstName, dismissedEmployee);
            return dismissedEmployee;
        } else {
            throw new NotFound();
        }
    }

    @Override
    public Employee findEmloyee(String lastName, String firstName) {
        Employee employee = staffOfEmployee.get(lastName + firstName);;
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            return employee;
        } else {
            throw new BadRequest();
        }
    }

    @Override
    public String getNameOfDepartment(Integer departmentId) {
        return departments.get(departmentId);
    }

    @Override
    public String getAllStaff() {
        if (staffOfEmployee.size() == 0) {
            throw new NotFound();
        }
        Set<String> allStaff = staffOfEmployee.keySet();
        return "Список сотрудников:" + allStaff;
    }
}
