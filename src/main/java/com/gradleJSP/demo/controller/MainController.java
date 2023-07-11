package com.gradleJSP.demo.controller;

import com.gradleJSP.demo.entity.People;
import com.gradleJSP.demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/")
    public String index(Model model) {
        List<People> list = peopleService.selectAll();
        model.addAttribute("peopleList", list);
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @PostMapping("/add")
    public String add2(People people) {
        peopleService.insert(people);
        return "redirect:/";
    }
}
