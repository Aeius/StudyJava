package com.gradleJSP.demo.controller;

import com.gradleJSP.demo.entity.People;
import com.gradleJSP.demo.service.PeopleService;
import com.gradleJSP.demo.util.PoiUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
@Tag(name = "사람", description = "사람 관련 api 입니다.")
public class MainController {

    @Autowired
    private PeopleService peopleService;

    @Value("${test}")
    private String test;
    @Value("${thunder}")
    private String thunder;

    @Operation(summary = "인덱스 페이지", description = "인덱스 페이지 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = People.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = People.class)))
    })

    @GetMapping("/")
    public String index(Model model) {
        List<People> list = peopleService.selectAll();
        model.addAttribute("peopleList", list);
        model.addAttribute("test", test);
        model.addAttribute("thunder", thunder);
        PoiUtil.createExcelSheet(list, peopleService.getRowCount(), peopleService.getColCount());
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @Operation(summary = "사람추가", description = "사람 추가 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = People.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = People.class)))
    })
    @PostMapping("/add")
    public String add2(People people) {
        peopleService.insert(people);
        return "redirect:/";
    }

    @Operation(summary = "사람수정", description = "사람 수정 api")
    @PostMapping("/update")
    public String update(@RequestBody long num, @RequestBody String name, @RequestBody int age) {
        peopleService.update(num, new People(num, name, age));
        return "redirect:/";
    }

    @Operation(summary = "사람삭제", description = "사람 삭제 api")
    @PostMapping("/delete/{num}")
    public String delete(@PathVariable Long num) {
        peopleService.delete(num);
        return "redirect:/";
    }


}
