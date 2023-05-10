package com.mayssa.demo.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nom", types = { Chanson.class })
public interface chansonProjection {
	public String getNom();

}
