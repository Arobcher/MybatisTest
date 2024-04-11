package com.ayou.mapper;

import com.ayou.model.Employee;

public interface EmployeeMapper {
    Employee selectEmployee(int id);
    void insertEmployee(Employee employee);
    int updateEmployee(Employee employee);
    int deleteEmployee(int id);
}
