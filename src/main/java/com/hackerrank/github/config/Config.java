package com.hackerrank.github.config;




import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration

@EnableJpaRepositories(basePackages = "com.hackerrank.github.repository",        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
@ComponentScan("com.hackerrank.github")
@EnableSwagger2
public class Config extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hackerrank.github"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()))
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API \"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("chandan", "https://springframework.guru/about/", "john@springfrmework.guru"))
                .build();
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("openid", "open_connect");
        authorizationScopes[1] = new AuthorizationScope("profile", "user_profile");
        authorizationScopes[2] = new AuthorizationScope("api", "for_api");;
        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder().title("Swagger API Documentation")
                .description("API documentation for  REST Services.").version("1.6.9").build();
    }

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopes=new ArrayList<>();
        authorizationScopes.add( new AuthorizationScope("openid", "open_connect"));
        authorizationScopes.add( new AuthorizationScope("profile", "user_profile"));
        authorizationScopes.add( new AuthorizationScope("api", "for_api"));
        List<GrantType> grantTypes=new ArrayList<>();
        GrantType grantType=new ImplicitGrant(new LoginEndpoint("http://localhost:8080/oauth/authorize"),"clientapp");
       grantTypes.add(grantType);
        return new OAuth("OAuth2",authorizationScopes,grantTypes);

    }
    private ApiKey apiKey(){
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }
}
