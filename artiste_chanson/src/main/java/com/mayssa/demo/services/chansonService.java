package com.mayssa.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mayssa.demo.entities.Artiste;
import com.mayssa.demo.entities.Chanson;


public interface chansonService {
	Chanson saveChanson(Chanson p);
	Chanson updateChanson(Chanson p);
	void deleteChanson(Chanson p);
	 void deleteChansonById(Long id);
	 Chanson getChanson(Long id);
	List<Chanson> getAllChansons();
	Page<Chanson> getAllChansonsParPage(int page, int size);
	List<Artiste> getAllArtistes();
	List<Chanson> findByArtiste (Artiste artiste);
	List<Chanson> findByArtisteIdArtiste(Long idChanson);

}
