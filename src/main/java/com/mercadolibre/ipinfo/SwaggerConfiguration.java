package com.mercadolibre.ipinfo;

import com.fasterxml.classmate.TypeResolver;
import com.mercadolibre.ipinfo.dto.ResponseDTO;
import com.mercadolibre.ipinfo.dto.ResponseStats;
import com.mercadolibre.ipinfo.dto.ResponseTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  /**
   * Api info.
   *
   * @return ApiInfo
   */
  private static ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("IP Info API for Mercadolibre Test")
        .description("REST API developed to evaluate technical skills, it exposes 2 endpoints," +
                " one to consult the information about a particular IP and another to consult the usage statistics")
        .license("Apache 2.0")
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
        .termsOfServiceUrl("")
        .version("0.0.1")
        .build();
  }

  /**
   * Custom docket implementation
   *
   * @return docket
   */
  @Bean
  public Docket customImplementation(TypeResolver typeResolver) {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.mercadolibre.ipinfo"))
        .build()
        .apiInfo(apiInfo()).additionalModels(typeResolver.resolve(ResponseStats.class),
                    typeResolver.resolve(ResponseTrace.class),
                    typeResolver.resolve(ResponseDTO.class));
  }

}
