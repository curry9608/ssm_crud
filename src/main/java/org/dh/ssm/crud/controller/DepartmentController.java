package org.dh.ssm.crud.controller;

import org.dh.ssm.crud.bean.Department;
import org.dh.ssm.crud.bean.Msg;
import org.dh.ssm.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 实现“新增弹框”部门信息的展示
 * @author donghan
 * @create 2022-03-21 14:54
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getAllDepts(){
        List<Department> list = departmentService.getDepts();
        return Msg.success().add("depts",list);
    }
}
