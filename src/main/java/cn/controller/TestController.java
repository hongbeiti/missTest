package cn.controller;

import cn.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.service.ITestModelService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestModelService iTestModelService;

    @PostMapping
    public void add(@RequestBody TestModel testModel){
        iTestModelService.add(testModel);
    }
}
