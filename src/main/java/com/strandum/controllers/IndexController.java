package com.strandum.controllers;

import com.strandum.services.ClockingService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Scope("session")
@SessionAttributes("role")
@Controller
public class IndexController implements ErrorController {

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;
    @Autowired
    private ClockingService clockingService;

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = userDetailsManager.loadUserByUsername(userName).getAuthorities().iterator().next().getAuthority();
        model.addAttribute("role", role);
        session.setAttribute("role", "role");

        if (role.equals("ROLE_ADMIN")) {
            return "index";
        }else {
            model.addAttribute("clockings", clockingService.getClockingForEmployee(userName));
            return "editClocking";
        }
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }
        return "index";
    }
}
