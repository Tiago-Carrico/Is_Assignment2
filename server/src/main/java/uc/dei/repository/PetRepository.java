package uc.dei.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import uc.dei.entity.Pet;

public interface PetRepository extends R2dbcRepository<Pet, Long>{
    
}
