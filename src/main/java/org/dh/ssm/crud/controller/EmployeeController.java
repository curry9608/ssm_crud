package org.dh.ssm.crud.controller;

/**
 * @author donghan
 * @create 2022-03-19 18:06
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.dh.ssm.crud.bean.Employee;
import org.dh.ssm.crud.bean.Msg;
import org.dh.ssm.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 */
//web层
@Controller
public class EmployeeController {
    //Service层
    @Autowired
    EmployeeService employeeService;

    /**
     *单个删除与批量删除二合一
     * 批量删除 1-2-3
     * 单个删除1
     */
    @DeleteMapping("/emp/{ids}")
    @ResponseBody
    public Msg deleteEmpById(@PathVariable("ids") String ids){
        if (ids.contains("-")) {
            String[] strIds = ids.split("-");
            //组装ids数组
            List<Integer> del_ids = new ArrayList<>();
            for (String strId : strIds) {
                del_ids.add(Integer.parseInt(strId));
            }
            employeeService.deleteBatch(del_ids);
        } else {
            Integer empId = Integer.parseInt(ids);
            employeeService.deleteEmpById(empId);
        }

        return Msg.success();
    }

    /**
     * 修改员工信息
     */
    @PutMapping("/emp/{empId}")
    @ResponseBody
    public Msg updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 查询某用户信息
     */

    @GetMapping("/emp/{empId}")
    @ResponseBody
    public Msg getEmp(@PathVariable("empId") Integer empId){
        Employee employee = employeeService.getEmp(empId);
        return Msg.success().add("emp",employee);
    }


    /**
     * 用户名是否可用的方法
     */
    @GetMapping("/checkUser")
    @ResponseBody
    public Msg checkUser(@RequestParam("empName") String empName){
        //1、先判断用户名是否合法
        String regEx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regEx)) {
            return Msg.fail().add("va_msg", "用户名必须是2-5位中文或者6-16位英文和数字的组合");
        }
        //2、然后判断用户名是否已经存在
        if(employeeService.checkUser(empName)){
            return Msg.success();
        }

        return Msg.fail().add("va_msg", "用户名不可用");
    }


    /**
     * 点击弹出的模态框“保存”按钮 的功能：  并且校验：JSR 303校验  （后端校验）
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){

        if (result.hasErrors()) {
            //校验失败，在模态框中显示错误信息
            Map<String, Object> map = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }

        employeeService.saveEmp(employee);
        return Msg.success();
    }


    /**
     * 查询员工数据 (  返回json。实现客户端的无关性。 用链式编程思想)
     */
    @RequestMapping("/emps")
    @ResponseBody //用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){

        //查询出了全部结果，但不是分页查询,所以要引入分页pageHelper插件
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
        //startPage后紧跟的这个查询就是分页查询
        List<Employee> emps = employeeService.getAll();
        //查询之后 使用pageInfo来包装查询结果 pageInfo（）封装了详细的分页信息,5代表连续显示的页数
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo",page);//链式操作
    }


    /**
     * 查询员工数据（分页查询）
     * @return
     */
//    @RequestMapping("/emps")
   /* public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){

        //查询出了全部结果，但不是分页查询,所以要引入分页pageHelper插件
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
        //startPage后紧跟的这个查询就是分页查询
        List<Employee> emps = employeeService.getAll();
        //查询之后 使用pageInfo来包装查询结果 pageInfo（）封装了详细的分页信息,5代表连续显示的页数
        PageInfo page = new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);//使用Model向request域对象共享数据
        return "list";
    }*/
}
