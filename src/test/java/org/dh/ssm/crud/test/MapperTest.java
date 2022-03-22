package org.dh.ssm.crud.test;

/**
 * @author donghan
 * @create 2022-03-19 16:55
 */

import org.apache.ibatis.session.SqlSession;
import org.dh.ssm.crud.bean.Department;
import org.dh.ssm.crud.bean.Employee;
import org.dh.ssm.crud.dao.DepartmentMapper;
import org.dh.ssm.crud.dao.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

/**
 * 测试dao层的工作
 */
@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    //注入sqlSession 在applicationContext.xml 一个可以执行批量操作的 sqlSession
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCURD(){
        System.out.println(departmentMapper);
        //1.插入几个部门
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
//       2. 生成员工数据，测试员工插入
//        employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@qq.com",1));
        //3.批量插入多个员工:使用可以执行批量操作的sqlSession（这样批量操作 效率高！）
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        for (int i = 0;i < 500;i++){
//            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
//            mapper.insertSelective(new Employee(null,uid,"M",uid + "@qq.com",1));
//        }
//        System.out.println("批量执行完成");
        //4.测试删除
//        int i = employeeMapper.deleteByPrimaryKey(501);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int i = mapper.deleteByPrimaryKey(499);
        System.out.println(i + "删除成功");
    }

}
