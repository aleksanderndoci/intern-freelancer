package al.ikubinfo.internship.freelancer.config;

import al.ikubinfo.internship.freelancer.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

@Configuration
public class SwaggerConfig {
    private static final String AUTH_METHOD = "OAuth(JWT Access Token)";
    private static final String API_KEY_NAME = "Authorization";
    private static final String API_KEY_PASS_AS = "header";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(withClassAnnotation(RestController.class))
                .paths(any())
                .build()
                .securitySchemes(this.securityScheme())
                .securityContexts(this.securityContext());
    }

    private List<SecurityContext> securityContext() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(Collections.singletonList(
                        SecurityReference.builder()
                                .reference(AUTH_METHOD)
                                .scopes(scopes())
                                .build()))
                .build();
        return Collections.singletonList(securityContext);
    }

    private List<SecurityScheme> securityScheme() {
        return Collections.singletonList(new ApiKey(AUTH_METHOD, API_KEY_NAME, API_KEY_PASS_AS));
    }

    private AuthorizationScope[] scopes() {
        return Stream.of(Role.values())
                .map(a -> new AuthorizationScope(a.name(), a.name()))
                .toArray(AuthorizationScope[]::new);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Freelancer -Rest API")
                .version("1.0")
                .description("Freelancer - ikubINFO internship 2021:")
                .contact(new Contact("-",
                        "#",
                        "-")
                ).build();
    }
}
