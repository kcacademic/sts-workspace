package com.coc.payments.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@RefreshScope
@PropertySource(value = "classpath:application.yml")
@EnableSwagger2
public class SwaggerConfig {

    @Value("${api.version}")
    private String apiVersion;
    @Value("${swagger.enabled}")
    private String enabled = "false";
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.useDefaultResponseMessages}")
    private String useDefaultResponseMessages;
    @Value("${swagger.enableUrlTemplating}")
    private String enableUrlTemplating;
    @Value("${swagger.deepLinking}")
    private String deepLinking;
    @Value("${swagger.defaultModelsExpandDepth}")
    private String defaultModelsExpandDepth;
    @Value("${swagger.defaultModelExpandDepth}")
    private String defaultModelExpandDepth;
    @Value("${swagger.displayOperationId}")
    private String displayOperationId;
    @Value("${swagger.displayRequestDuration}")
    private String displayRequestDuration;
    @Value("${swagger.filter}")
    private String filter;
    @Value("${swagger.maxDisplayedTags}")
    private String maxDisplayedTags;
    @Value("${swagger.showExtensions}")
    private String showExtensions;

    @Bean
    public Docket eDesignApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
            .enable(Boolean.valueOf(enabled))
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .directModelSubstitute(LocalDate.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(Boolean.valueOf(useDefaultResponseMessages))
            .enableUrlTemplating(Boolean.valueOf(enableUrlTemplating));
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
            .deepLinking(Boolean.valueOf(deepLinking))
            .displayOperationId(Boolean.valueOf(displayOperationId))
            .defaultModelsExpandDepth(Integer.valueOf(defaultModelsExpandDepth))
            .defaultModelExpandDepth(Integer.valueOf(defaultModelExpandDepth))
            .defaultModelRendering(ModelRendering.EXAMPLE)
            .displayRequestDuration(Boolean.valueOf(displayRequestDuration))
            .docExpansion(DocExpansion.NONE)
            .filter(Boolean.valueOf(filter))
            .maxDisplayedTags(Integer.valueOf(maxDisplayedTags))
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(Boolean.valueOf(showExtensions))
            .tagsSorter(TagsSorter.ALPHA)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
            .description(description)
            .version(apiVersion)
            .build();
    }
}