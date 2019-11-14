package com.alex.generator.config;

import com.ibeetl.starter.BeetlTemplateCustomize;
import org.beetl.core.GroupTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: beetl模板配置
 * @Author:     alex
 * @CreateDate: 2019/11/14 17:37
 * @Version:    1.0
 *
*/
@Configuration
public class MyBeetlConfig {

    @Bean
    public BeetlTemplateCustomize beetlTemplateCustomize() {
        return new BeetlTemplateCustomize() {
            @Override
            public void customize(GroupTemplate groupTemplate) {

            }
        };
    }
}
