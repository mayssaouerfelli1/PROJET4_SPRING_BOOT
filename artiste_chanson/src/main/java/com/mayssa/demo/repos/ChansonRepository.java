package com.mayssa.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mayssa.demo.entities.Artiste;
import com.mayssa.demo.entities.Chanson;

@RepositoryRestResource(path = "rest")
public interface ChansonRepository extends JpaRepository<Chanson,Long>{

    @Query("SELECT c FROM Chanson c WHERE c.artiste = ?1")
    List<Chanson> findByArtiste(Artiste artiste);
    
    List<Chanson> findByArtisteIdArtiste(Long id);
}
