package com.child.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String getIndex(){
        return "admin/index";
    }

    @GetMapping("/list/employee")
    public String getEmployeeList(){
        return "admin/empList";
    }

    @GetMapping("/list/user")
    public String getUserList(){
        return "admin/userList";
    }

    @GetMapping("/request/user")
    public String getUserAccountRequest(){
        return "admin/userAccountRequest";
    }

    @GetMapping("/add/user")
    public String getAddUser(){
        return "admin/addUser";
    }

    @GetMapping("/add/employee")
    public String getAddEmp(){
        return "admin/addEmp";
    }

    @GetMapping(value = "/add/packages")
    public String getAddPackages(){
        return "admin/addPackages";
    }

    @GetMapping("/attendance")
    public String getAttendance(){
        return "admin/attendance";
    }

    @GetMapping("/assign-food")
    public String getAssignFood(){
        return "admin/assignFood";
    }
}
