package uc.dei;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PetRepository extends R2dbcRepository<Pet, Long>{
    
}
