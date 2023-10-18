package com.house.repository;

import com.house.entity.HistoriqueEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 

@Repository
public interface HistoriqueRepository extends JpaRepository<HistoriqueEntity, Long> {

    Page<HistoriqueEntity> findByIdMenageAndIdExerciceAndIdTrimestreAndIdUser(Integer idMenage, Integer idExercice,
            Integer idTrimestre, Integer idUSer, Pageable pagingSort);

    Page<HistoriqueEntity> findByIdUser(Integer idUSer, Pageable pagingSort);

    Page<HistoriqueEntity> findByIdExerciceAndIdTrimestreAndIdUser(Integer idExercice, Integer idTrimestre,
            Integer idUSer, Pageable pagingSort);

    Page<HistoriqueEntity> findByIdExerciceAndIdTrimestre(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

    Page<HistoriqueEntity> findByIdMenageAndIdExerciceAndIdTrimestre(Integer idMenage, Integer idExercice,
            Integer idTrimestre, Pageable pagingSort);

    Page<HistoriqueEntity> findByIdMenage(Integer idMenage, Pageable pagingSort);

    Page<HistoriqueEntity> findByIdExercice(Integer idExercice, Pageable pagingSort); 
 
}