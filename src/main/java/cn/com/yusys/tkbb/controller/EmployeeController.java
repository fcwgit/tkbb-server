package cn.com.yusys.tkbb.controller;

import cn.com.yusys.tkbb.dao.DepartmentDao;
import cn.com.yusys.tkbb.dao.EmployeeDao;
import cn.com.yusys.tkbb.po.Department;
import cn.com.yusys.tkbb.po.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@Controller
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查询出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //来到员工列表页面
        //redirect：表示重定向到一个地址 /代表当前项目路径
        //forward：表示转发到一个地址
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        //查询出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "add";
    }

    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("==============================================");
        logger.debug(employee.toString());
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id")Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
