package ar.com.todo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*** @author Andres Gonzalez ***/

@SpringBootApplication
public class HulkStoreApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HulkStoreApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HulkStoreApiApplication.class);
	}

}
