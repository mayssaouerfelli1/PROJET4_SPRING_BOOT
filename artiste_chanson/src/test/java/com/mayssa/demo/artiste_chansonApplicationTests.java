package com.mayssa.demo;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.mayssa.demo.entities.Artiste;
import com.mayssa.demo.entities.Chanson;
import com.mayssa.demo.repos.ChansonRepository;
import com.mayssa.demo.services.chansonService;


@SpringBootTest
class artiste_chansonApplicationTests {

	@Autowired
	private ChansonRepository chansonRepository;
	

	@Autowired
	private chansonService chansonService;
	@Test
	public void testCreateChanson() {
	Chanson fact = new Chanson(new Date(),"l",null);
	
	chansonRepository.save(fact);
	}
	
	@Test
	public void testFindChanson()
	{
	Chanson f = chansonRepository.findById(1L).get(); 
	System.out.println(f);
	}
	@Test
	public void testUpdateChanson()
	{
	Chanson f = chansonRepository.findById(1L).get();
	f.setNom("adele");
	chansonRepository.save(f);
	}
	@Test
	public void testDeleteChanson()
	{
		chansonRepository.deleteById(1L);;
	}
	 
	@Test
	public void testListerTousChansons()
	{
	List<Chanson> fact = chansonRepository.findAll();
	for (Chanson f : fact)
	{
	System.out.println(f);
	}
	}
	@Test
	public void testFindByNomChansonContains()
	{
	Page<Chanson> fact = chansonService.getAllChansonsParPage(0, 2);
	System.out.println(fact.getSize());
	System.out.println(fact.getTotalElements());
	System.out.println(fact.getTotalPages());
	fact.getContent().forEach(f -> {System.out.println(f.toString());
	 });
	
	}
	
	
	@Test
	public void testfindByChanson()
	{
	Artiste cl = new Artiste();
	cl.setIdArtiste(1L);
	
	List<Chanson> facts = chansonRepository.findByArtiste(cl);
	for (Chanson f : facts)
	{
		System.out.println(f);
	}
	}
	@Test
	public void findByArtisteIdArtiste()
	{
	List<Chanson> facts = chansonRepository.findByArtisteIdArtiste(2L);
	for (Chanson f : facts)
	{
	System.out.println(f);
	}
	 }
	
	
	}





