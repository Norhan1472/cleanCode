package com.spring.gucms.repository;

import com.spring.gucms.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long> {
    public Topics findByInfoName(String infoName);

}