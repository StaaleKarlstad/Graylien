package com.dev.graylien.advice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends JpaRepository<AdviceEntity, Integer>{
    
}
