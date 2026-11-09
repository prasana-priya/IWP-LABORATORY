    sadhagayahastri-code
    IWP_LAB

Repository navigation

    Code
    Issues
    Pull requests
    Agents
    Actions
    Projects
    Security and quality
    Insights

Files
tT

    Ex 1
    Ex 10
    Ex 2
    Ex 3
    Ex 4
    Ex 5
    Ex 7
    Ex 8
    Ex 9
        src/main
            java/com/ifet/exp9
                controller
                    EmployeeController.java
                model
                repository
                service
                EmployeeCrudApplication.java
            resources
        target
        pom.xml
    ex06
    README.md

    IWP_LAB/Ex 9/src/main/java/com/ifet/exp9/controller

/EmployeeController.java
sadhagayahastri-code
sadhagayahastri-code
Add files via upload
4bf1a05
 · 
last month

    Code
    Blame

56 lines (47 loc) · 1.73 KB
package com.ifet.exp9.controller;

import com.ifet.exp9.model.Employee;
import com.ifet.exp9.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service=service;
    }

    @GetMapping({"/", "/employees"})
    public String home(Model model){
        model.addAttribute("employees", service.getAll());
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @GetMapping("/employees/new")
    public String form(Model model){
        model.addAttribute("employees", service.getAll());
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/employees/save")
    public String save(@ModelAttribute Employee emp){
        if (emp.getEmail() == null || emp.getEmail().trim().isEmpty()) {
            if (emp.getName() != null && !emp.getName().trim().isEmpty()) {
                String cleanName = emp.getName().trim().toLowerCase().replaceAll("\\s+", "");
                emp.setEmail(cleanName + "@example.com");
            }
        }
        service.save(emp);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("employees", service.getAll());
        model.addAttribute("employee", service.get(id));
        return "index";
    }

    @GetMapping("/employees/delete/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/employees";
    }
}
target content loaded
