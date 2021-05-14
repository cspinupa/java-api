package io.helidon.service.employee;

import io.helidon.config.Config;
import java.util.List;

public interface EmployeeRepository {

    public static EmployeeRepository create(String driverType,Config config) {
        switch (driverType) {
            case "Array":
                return new EmployeeRepositoryImpl();
                break;
            /*case "Oracle":
                return new EmployeeRepositoryImplDB(config);
                break;*/
            default:
                //Array is the default
                return new EmployeeRepositoryImpl();
                break;
        }

    }

    public List<Employee> getAll();
    public List<Employee> getByLastName(String lastName);
    public List<Employee> getByTitle(String title);
    public List<Employee> getByDepartment(String department);
    public Employee save(Employee employee);
    public Employee update(Employee updatedEmployee, String id);
    public void deleteById(String id);
    public Employee getById(String id);
    public boolean isIdFound(String id);

}