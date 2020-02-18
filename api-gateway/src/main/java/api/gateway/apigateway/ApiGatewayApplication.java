package api.gateway.apigateway;

import api.gateway.apigateway.filter.LoggingPreFilter;
import api.gateway.apigateway.filter.SampleErrorFilter;
import api.gateway.apigateway.filter.SamplePostFilter;
import api.gateway.apigateway.filter.SampleRouteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public LoggingPreFilter getLoggingPreFilter(){
		return new LoggingPreFilter();
	}

	@Bean
	public SampleErrorFilter getSampleErrorFilter(){
		return new SampleErrorFilter();
	}

	@Bean
	public SamplePostFilter getSamplePostFilter(){
		return new SamplePostFilter();
	}

	@Bean
	public SampleRouteFilter getSampleRouteFilter(){
		return new SampleRouteFilter();
	}

	@Bean
	public Logger getLogger(){
		return LoggerFactory.getLogger(LoggingPreFilter.class);
	}
}
