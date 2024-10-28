package com.nutech.ppob.repository;



import com.nutech.ppob.entity.Services;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ServiceRepository extends JpaRepository<Services, Long> {

    @EntityGraph(attributePaths = "banner")
    List<Services> findAll();

    Optional<Services> findByServiceCode(String code);


}
