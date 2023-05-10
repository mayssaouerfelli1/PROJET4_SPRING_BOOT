package com.mayssa.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayssa.demo.entities.Artiste;

public interface ArtisteRepository extends JpaRepository<Artiste,Long> {
    
}
