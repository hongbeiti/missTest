package cn.controller;

import cn.model.GameSkills;
import cn.model.TestModel;
import cn.service.IGameSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cn.service.ITestModelService;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestModelService iTestModelService;

    @Autowired
    private IGameSkillsService iGameSkillsService;

    @PostMapping
    public void add(@RequestBody TestModel testModel){
        iTestModelService.add(testModel);
    }

    @GetMapping(value = "/get")
    public String get(@RequestParam(name = "keyword") String keyword,
                      @RequestParam(name = "skipNum") Integer skipNum,
                      Model model){
        List<GameSkills> result = iGameSkillsService.search(keyword, skipNum);
        model.addAttribute("users", result);

        System.out.println(result);
        return "user";
    }
}
