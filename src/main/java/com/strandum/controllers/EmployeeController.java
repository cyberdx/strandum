package com.strandum.controllers;

import com.strandum.domains.Employee;
import com.strandum.services.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.Serializable;

@Controller
public class EmployeeController implements Serializable {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String list(Model model, HttpSession session) {
        model.addAttribute("employees", employeeService.listOfAllEmployees());
        model.addAttribute("role", session.getAttribute("role"));
        return "employees";
    }

    @RequestMapping("employee/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeform";
    }

    @PostMapping("/employee")
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employeeform";
        }

        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }
}
