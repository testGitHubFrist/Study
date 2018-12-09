package com.zsc.study.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: zhangshanchuang
 * @Date: 18/12/8 22:19
 * @Description: swagger 配置
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.zsc.study.controller"})
public class ApiSwaggerConfig  {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.any()).build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("服务平台 API").description("")
        		.termsOfServiceUrl("http://localhost:8080").version("1.0").build();
    }
}
