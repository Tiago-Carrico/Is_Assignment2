package uc.dei.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import uc.dei.entity.Owner;

public interface OwnerRepository extends R2dbcRepository<Owner, Long>{
    
}
