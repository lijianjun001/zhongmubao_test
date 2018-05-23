package com.zhongmubao.api.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/***
 * @author 孙阿龙
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
@ComponentScan(basePackages = "com.zhongmubao.api")
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhongmubao.api.web"))
                .paths(PathSelectors.any())
                .build()
                .enable(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SSM-Swagger2 APIs")
                .version("1.0.0")
                .build();
    }
}