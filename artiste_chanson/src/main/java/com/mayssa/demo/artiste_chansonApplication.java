package com.mayssa.demo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.mayssa.demo.entities.Chanson;
import com.mayssa.demo.services.chansonService;

@SpringBootApplication
public class artiste_chansonApplication implements CommandLineRunner{
	@Autowired
	chansonService chansonService;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(artiste_chansonApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		repositoryRestConfiguration.exposeIdsFor(Chanson.class);

		
	}

}
