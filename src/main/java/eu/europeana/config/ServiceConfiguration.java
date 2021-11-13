package eu.europeana.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import eu.europeana.entities.core.LeastCommonMultipleSolver;
import eu.europeana.entities.core.LoopSolver;
import eu.europeana.entities.core.SmallestPositive;
import eu.europeana.service.Service;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring Configuration Class
 *
 * @author Jorge Ortiz
 */
@EnableSwagger2
@Configuration
public class ServiceConfiguration {

    @Bean
    public LeastCommonMultipleSolver leastCommonMultipleSolver() {
        return new LeastCommonMultipleSolver();
    }

    @Bean
    public SmallestPositive smallestPositive() {
        return new SmallestPositive(leastCommonMultipleSolver());
    }

    @Bean
    public Gson gsonService() {
        return new GsonBuilder().create();
    }

    @Bean
    public StopWatch stopWatch() {
        return new StopWatch();
    }

    @Bean
    public Service service() {
        return new Service(smallestPositive(), gsonService(), stopWatch());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
