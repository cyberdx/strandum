package com.strandum.controllers;

import com.strandum.domains.Clocking;
import com.strandum.services.ClockingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.Serializable;

@Controller
@RequestMapping("/employees")
public class ClockingController implements Serializable {

    @Autowired
    private ClockingService clockingService;

    @GetMapping("/showClockings/{employeeId}")
    public String redirectToClockings(@PathVariable Long employeeId, Model model, HttpSession session) {
        model.addAttribute("clockings", clockingService.getClockingForEmployee(employeeId));
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("role", session.getAttribute("role"));
        return "editClocking";
    }

    @PostMapping("/addClocking/{employeeId}")
    public String addClocking(@Valid Clocking clocking, @PathVariable Long employeeId, RedirectAttributes redirectAttributes) {
        clockingService.addClocking(clocking, employeeId);
        redirectAttributes.addAttribute("employeeId", employeeId);
        return "redirect:/employees/showClockings/{employeeId}";
    }

    @GetMapping("/deleteClocking/{id}")
    public String deleteClocking(@PathVariable Long id, @RequestParam Long employeeId, RedirectAttributes redirectAttributes) {
        clockingService.deleteClockingById(id);
        redirectAttributes.addAttribute("employeeId", employeeId);
        return "redirect:/employees/showClockings/{employeeId}";
    }

}
