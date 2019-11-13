package com.alex.generator.controller;

import com.alex.core.http.HttpResult;
import com.alex.dbms.vo.ConnParam;
import com.alex.generator.service.GenerateService;
import com.alex.generator.vo.GenerateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 代码生成器
 * @Author:     alex
 * @CreateDate: 2019/11/13 16:22
 * @Version:    1.0
 *
*/
@RestController
@RequestMapping("")
public class GeneratorController {

    @Autowired
    private GenerateService generateService;

    @PostMapping("/testConnection")
    public HttpResult testConnection(@RequestBody ConnParam connParam) {
        boolean success = generateService.testConnection(connParam);
        if (success)
            return HttpResult.ok(true);
        return HttpResult.error("连接失败，请检查数据库及连接。");
    }

    @PostMapping("/getTables")
    public HttpResult getTables(@RequestBody ConnParam connParam) {
        return HttpResult.ok(generateService.getTables(connParam));
    }

    @PostMapping("/getGenerateModel")
    public HttpResult getGenerateModel(@RequestBody GenerateModel generateModel) {
        return HttpResult.ok(generateService.getGenerateModel(generateModel));
    }

    @PostMapping("/generateModels")
    public HttpResult generateModels(@RequestBody GenerateModel generateModel) throws Exception {
        return HttpResult.ok(generateService.generateModels(generateModel));
    }
}
