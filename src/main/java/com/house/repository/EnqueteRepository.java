package com.house.repository;

import com.house.entity.EnqueteEntity;
import com.house.entity.ExerciceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnqueteRepository  extends JpaRepository<EnqueteEntity, Integer> {
}
