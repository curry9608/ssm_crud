package org.dh.ssm.crud.service;

import org.dh.ssm.crud.bean.Employee;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author donghan
 * @create 2022-03-19 18:13
 */
//service层
@Service
public interface EmployeeService {
    List<Employee> getAll();

    void saveEmp(Employee employee);

    Boolean checkUser(String empName);


    Employee getEmp(Integer empId);

    void updateEmp(Employee employee);

    void deleteEmpById(Integer ids);

    void deleteBatch(List<Integer> ids);
}
