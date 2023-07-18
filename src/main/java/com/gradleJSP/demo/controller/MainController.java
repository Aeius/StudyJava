package com.gradleJSP.demo.controller;

import com.gradleJSP.demo.entity.People;
import com.gradleJSP.demo.service.PeopleService;
import com.gradleJSP.demo.util.PoiUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
public class MainController {

    @Autowired
    private PeopleService peopleService;

    @Value("${test}")
    private String test;
    @Value("${thunder}")
    private String thunder;

    @GetMapping("/")
    public String index(Model model) {
        List<People> list = peopleService.selectAll();
        model.addAttribute("peopleList", list);
        model.addAttribute("test", test);
        model.addAttribute("thunder", thunder);
        PoiUtil.CreateExcelSheet(list, peopleService.getRowCount(), peopleService.getColCount());
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }
    @ApiOperation(value="사람 추가", notes="사람이 추가된다.")
    @PostMapping("/add")
    public String add2(People people) {
        peopleService.insert(people);
        return "redirect:/";
    }
}
