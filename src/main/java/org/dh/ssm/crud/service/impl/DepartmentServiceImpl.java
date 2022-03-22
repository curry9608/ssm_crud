package org.dh.ssm.crud.service.impl;

import org.dh.ssm.crud.bean.Department;
import org.dh.ssm.crud.dao.DepartmentMapper;
import org.dh.ssm.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author donghan
 * @create 2022-03-21 15:10
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
