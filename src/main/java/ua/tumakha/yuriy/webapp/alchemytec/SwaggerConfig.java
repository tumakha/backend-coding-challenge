package ua.tumakha.yuriy.webapp.alchemytec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Yuriy Tumakha
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/expenses.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "AlchemyTec REST JSON API",
                "AlchemyTec - Back End Coding Challenge",
                "1.0",
                "TermsOfService",
                "Yuriy Tumakha (tumakha@gmail.com)",
                "GNU General Public License (GPL)",
                "https://gnu.org/licenses/gpl.html"
        );
        return apiInfo;
    }
}
