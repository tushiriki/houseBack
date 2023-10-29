package com.house.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.house.entity.QuestionMenageEntity;
 
@Repository
public interface QuestionMenageRepository extends JpaRepository<QuestionMenageEntity, Long>{

  @Query("select count(p)>0 from QuestionMenageEntity p where p.idMenage=?1 and p.idTrimestre=?2 ")
  boolean getByIdMenageAndIdTrimestre(Integer idMenage, Integer idQuarter);

    @Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 ")
    Page<QuestionMenageEntity> getByIdExerciceAndIdTrimestre(Integer idExercice, Integer idTrimestre,
            Pageable pagingSort);
 
   @Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1")
     Page<QuestionMenageEntity> getByIdExercice(Integer idExercice, Pageable pagingSort);

    Page<QuestionMenageEntity> findByIdMenage(Integer idMenage, Pageable pagingSort);

    Page<QuestionMenageEntity> findByIdMenageAndIdTrimestre(Integer idMenage, Integer idTrimestre, Pageable pagingSort);

    Page<QuestionMenageEntity> findByIdTrimestre(Integer idTrimestre, Pageable pagingSort);
 
@Query("select idTrimestre from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 group by p.idTrimestre")
List<Integer> getTrimestreByExercice(Integer idExercice);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 ")
Integer getTotal(Integer idExercice, Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.combienFoisMangeParJour=4")
double getTotalMenageAssezMange(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.combienFoisMangeParJour=4")
Page<QuestionMenageEntity> getMenageAssezMange(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.combienFoisMangeParJour!=null")
Integer getTotal(Integer idExercice);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.combienFoisMangeParJour=4")
Page<QuestionMenageEntity> getMenageAssezMange(Integer idExercice, Pageable pagingSort);
 
@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.combienFoisMangeParJour=4")
double getTotalMenageAssezMange(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1")
Integer getTotalTrimestre(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.qualiteAlimentEauPotableEnfantHandicape=3")
double getTotalQualiteAlimentEauPotableEnfantHandicape(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.qualiteAlimentEauPotableEnfantHandicape=3")
Page<QuestionMenageEntity> getQualiteAlimentEauPotableEnfantHandicape(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.qualiteAlimentEauPotableEnfantHandicape=3")
Page<QuestionMenageEntity> getQualiteAlimentEauPotableEnfantHandicape(Integer idExercice, Pageable pagingSort);
  
@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.qualiteAlimentEauPotableEnfantHandicape!=null")
Integer getTotalListDeux(Integer idExercice, Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1")
Integer getTotalTrimestreQualiteAlimentEauPotableEnfantHandicape(Integer idTrimestre);


@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.qualiteAlimentEauPotableEnfantHandicape=3")
double getTotalQualiteAlimentEauPotableEnfantHandicape(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.activteEnfantHandicapeAGR=true")
double getTotalJeunesHandicapeAGR(Integer idExercice, Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.activteEnfantHandicapeAGR!=null")
Integer getTotalListTrois(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.activteEnfantHandicapeAGR=true")
Page<QuestionMenageEntity> getJeunesHandicapeAGR(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.activteEnfantHandicapeAGR=true")
Page<QuestionMenageEntity> getJeunesHandicapeAGR(Integer idExercice, Pageable pagingSort);
  

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.activteEnfantHandicapeAGR=true")
Integer getTotalTrimestreJeunesHandicapeAGR(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.activteEnfantHandicapeAGR=true")
double getTotalJeunesHandicapeAGR(Integer idTrimestre);


@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHandicapeDicussionGroupe=true")
double getTotalEnfantHandicapeDicussionGroupe(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHandicapeDicussionGroupe=true")
Page<QuestionMenageEntity> getEnfantHandicapeDicussionGroupe(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.enfantHandicapeDicussionGroupe=true")
Page<QuestionMenageEntity> getEnfantHandicapeDicussionGroupe(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.enfantHandicapeDicussionGroupe=true")
Integer getTotalTrimestreEnfantHandicapeDicussionGroupe(Integer idTrimestre);
@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and (p.enfantHandicapeDicussionGroupe=true OR p.enfantHandicapeDicussionGroupe=false)")
Integer getTotalListQuatre(Integer idExercice, Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.enfantHandicapeDicussionGroupe=true")
double getTotalEnfantHandicapeDicussionGroupe(Integer idTrimestre);


@Query("select ifnull(AVG(p.combienFoisMangeParJour) ,0) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.combienFoisMangeParJour!=null")
double getTotalCombienFoisMangeParJour(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.combienFoisMangeParJour!=null")
Page<QuestionMenageEntity> getCombienFoisMangeParJour(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

// @Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.combienFoisMangeParJour!=null")
@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1")

Page<QuestionMenageEntity> getCombienFoisMangeParJour(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.combienFoisMangeParJour!=null")
Integer getTotalTrimestreCombienFoisMangeParJour(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.combienFoisMangeParJour!=null")
double getTotalCombienFoisMangeParJour(Integer idTrimestre);

@Query("select ifnull(SUM(p.combienFoisMangeParJour) ,0)  from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.combienFoisMangeParJour!=null")
Integer getTotalListCinq(Integer idExercice, Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.penurieAlimentaire=true")
double getTotalPenurieAlimentaire(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.penurieAlimentaire=true")
Page<QuestionMenageEntity> getPenurieAlimentaire(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.penurieAlimentaire=true")
Page<QuestionMenageEntity> getPenurieAlimentaire(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.penurieAlimentaire=true")
Integer getTotalTrimestrePenurieAlimentaire(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.penurieAlimentaire=true")
double getTotalPenurieAlimentaire(Integer idTrimestre);///


@Query("select  count(p)  from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 ")
Integer getTotalPenurieAlimentaireList(Integer idExercice,Integer idTrimestre);

// 

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.manqueAlimentQuatreSemaine=true")
double getTotalManqueAlimentQuatreSemaine(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.manqueAlimentQuatreSemaine=true")
Page<QuestionMenageEntity> getManqueAlimentQuatreSemaine(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.manqueAlimentQuatreSemaine=true")
Page<QuestionMenageEntity> getManqueAlimentQuatreSemaine(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.manqueAlimentQuatreSemaine=true")
Integer getTotalTrimestreManqueAlimentQuatreSemaine(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.manqueAlimentQuatreSemaine=true")
double getTotalManqueAlimentQuatreSemaine(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.manqueAlimentQuatreSemaine!=null")
Integer getTotalListSeth(Integer idExercice, Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.menageTerrain=1")
int getTotalMenageTerrainActif(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.menageTerrain=1")
Page<QuestionMenageEntity> getMenageTerrainActif(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

// 

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHadicapeAbandonneEcole=true")
double getTotalEnfantHadicapeAbandonneEcole(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHadicapeAbandonneEcole=true")
Page<QuestionMenageEntity> getEnfantHadicapeAbandonneEcole(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.enfantHadicapeAbandonneEcole=true")
Page<QuestionMenageEntity> getEnfantHadicapeAbandonneEcole(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.enfantHadicapeAbandonneEcole=true")
Integer getTotalTrimestreEnfantHadicapeAbandonneEcole(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.enfantHadicapeAbandonneEcole=true")
double getTotalEnfantHadicapeAbandonneEcole(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHadicapeAbandonneEcole!=null")
Integer getTotalListAVH(Integer idExercice, Integer idTrimestre);
// 

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHadicapeScolariser=true")
double getTotalEnfantHadicapeScolariser(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHadicapeScolariser=true")
Page<QuestionMenageEntity> getEnfantHadicapeScolariser(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.enfantHadicapeScolariser=true")
Page<QuestionMenageEntity> getEnfantHadicapeScolariser(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.enfantHadicapeScolariser=true")
Integer getTotalTrimestreEnfantHadicapeScolariser(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.enfantHadicapeScolariser=true")
double getTotalEnfantHadicapeScolariser(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHadicapeScolariser!=null")
Integer getTotalAVHScolarise(Integer idExercice, Integer idTrimestre);

// 

@Query("select ifnull(SUM(resourceFinancierePourenfantHadicape),0)  from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2  and p.resourceFinancierePourenfantHadicape>0 AND p.resourceFinancierePourenfantHadicape!=null ")
double getTotalResourceFinancierePourenfantHadicape(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2  and p.resourceFinancierePourenfantHadicape>0")
Page<QuestionMenageEntity> getResourceFinancierePourenfantHadicape(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1  and p.resourceFinancierePourenfantHadicape>0")
Page<QuestionMenageEntity> getResourceFinancierePourenfantHadicape(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.resourceFinancierePourenfantHadicape>0")
Integer getTotalTrimestreResourceFinancierePourenfantHadicape(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1  and p.resourceFinancierePourenfantHadicape>0 AND p.resourceFinancierePourenfantHadicape!=null")
double getTotalResourceFinancierePourenfantHadicape(Integer idTrimestre);

@Query("select count(p)*96 from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.resourceFinancierePourenfantHadicape>0 AND p.resourceFinancierePourenfantHadicape!=null")
Integer getTotalInvestiAVH(Integer idExercice, Integer idTrimestre);

// pour rapport

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.evaluationParticipantionAuxFormarmation=?3")
double getTotalEvaluationParticipantionAuxFormarmationType(Integer idExercice, Integer idTrimestre, Integer type);

//

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.evaluationParticipantionAuxFormarmation is not null")
Integer getTotalProgrammeFormation(Integer idExercice, Integer idTrimestre);
@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.evaluationParticipantionAuxFormarmation=1")
double getTotalEvaluationParticipantionAuxFormarmation(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.evaluationParticipantionAuxFormarmation=1")
Page<QuestionMenageEntity> getEvaluationParticipantionAuxFormarmation(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.evaluationParticipantionAuxFormarmation=1")
Page<QuestionMenageEntity> getEvaluationParticipantionAuxFormarmation(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1")
Integer getTotalTrimestreEvaluationParticipantionAuxFormarmation(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.evaluationParticipantionAuxFormarmation=1")
double getTotalEvaluationParticipantionAuxFormarmation(Integer idTrimestre);

//

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 ")
Integer getTotalAVH(Integer idExercice, Integer idTrimestre);
@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.considerationPositiveAuxEnfantHendicape=1")
double getTotalConsiderationPositiveAuxEnfantHendicape(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.considerationPositiveAuxEnfantHendicape=1")
Page<QuestionMenageEntity> getConsiderationPositiveAuxEnfantHendicape(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.considerationPositiveAuxEnfantHendicape=1")
Page<QuestionMenageEntity> getConsiderationPositiveAuxEnfantHendicape(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1")
Integer getTotalTrimestreConsiderationPositiveAuxEnfantHendicape(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.considerationPositiveAuxEnfantHendicape=1")
double getTotalConsiderationPositiveAuxEnfantHendicape(Integer idTrimestre);

//pour le rapport
@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.menageHeure=?3")
double getTotalMenageHeureType(Integer idExercice, Integer idTrimestre,Integer nombre);
//

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.menageHeure=3")
double getTotalMenageHeure(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.menageHeure=3")
Page<QuestionMenageEntity> getMenageHeure(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.menageHeure=3")
Page<QuestionMenageEntity> getMenageHeure(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1")
Integer getTotalTrimestreMenageHeure(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.menageHeure=3")
double getTotalMenageHeure(Integer idTrimestre);

//

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHandicapeHeure=3")
double getTotalEnfantHandicapeHeure(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.enfantHandicapeHeure=3")
Page<QuestionMenageEntity> getEnfantHandicapeHeure(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.enfantHandicapeHeure=3")
Page<QuestionMenageEntity> getEnfantHandicapeHeure(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1")
Integer getTotalTrimestreEnfantHandicapeHeure(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.enfantHandicapeHeure=3")
double getTotalEnfantHandicapeHeure(Integer idTrimestre);

//

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.menageMieuxAccepteSocialementEconomiquementCommunaute=true")
double getTotalMenageMieuxAccepteSocialementEconomiquementCommunaute(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.menageMieuxAccepteSocialementEconomiquementCommunaute=true")
Page<QuestionMenageEntity> getMenageMieuxAccepteSocialementEconomiquementCommunaute(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.menageMieuxAccepteSocialementEconomiquementCommunaute=true")
Page<QuestionMenageEntity> getMenageMieuxAccepteSocialementEconomiquementCommunaute(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.menageMieuxAccepteSocialementEconomiquementCommunaute=true")
Integer getTotalTrimestreMenageMieuxAccepteSocialementEconomiquementCommunaute(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.menageMieuxAccepteSocialementEconomiquementCommunaute=true")
double getTotalMenageMieuxAccepteSocialementEconomiquementCommunaute(Integer idTrimestre);

//

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and (p.familleAugmentRevenuParFournie=1 or p.familleAugmentRevenuParFournie=2)")
double getTotalFamilleAugmentRevenuParFournie(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and (p.familleAugmentRevenuParFournie=1 or p.familleAugmentRevenuParFournie=2)")
Page<QuestionMenageEntity> getFamilleAugmentRevenuParFournie(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and (p.familleAugmentRevenuParFournie=1 or p.familleAugmentRevenuParFournie=2)")
Page<QuestionMenageEntity> getFamilleAugmentRevenuParFournie(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1")
Integer getTotalTrimestreFamilleAugmentRevenuParFournie(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and (p.familleAugmentRevenuParFournie=1 or p.familleAugmentRevenuParFournie=2)")
double getTotalFamilleAugmentRevenuParFournie(Integer idTrimestre);

// 

@Query("select SUM(epargneMnsuelleMoyenneParFemme) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2  and (p.epargneMnsuelleMoyenneParFemme>0 OR p.epargneMnsuelleMoyenneParFemme!=null)")
double getTotalEpargneMnsuelleMoyenneParFemme(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2  and p.epargneMnsuelleMoyenneParFemme>0 ")
Page<QuestionMenageEntity> getEpargneMnsuelleMoyenneParFemme(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1  and p.epargneMnsuelleMoyenneParFemme>0 ")
Page<QuestionMenageEntity> getEpargneMnsuelleMoyenneParFemme(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and (p.epargneMnsuelleMoyenneParFemme>0 OR p.epargneMnsuelleMoyenneParFemme!=null)")
Integer getTotalTrimestreEpargneMnsuelleMoyenneParFemme(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1  and (p.epargneMnsuelleMoyenneParFemme>0 OR p.epargneMnsuelleMoyenneParFemme!=null)")
double getTotalEpargneMnsuelleMoyenneParFemme(Integer idTrimestre);

//

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.femillePrete=true ")
double getTotalFemillePrete(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and p.femillePrete=true ")
Page<QuestionMenageEntity> getFemillePrete(Integer idExercice, Integer idTrimestre,Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.femillePrete=true ")
Page<QuestionMenageEntity> getFemillePrete(Integer idExercice,Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.femillePrete=true ")
Integer getTotalTrimestreFemillePrete(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and p.femillePrete=true ")
double getTotalFemillePrete(Integer idTrimestre);

//

@Query("select count(p) from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and (p.choixFamilleEnAGR=1 or p.choixFamilleEnAGR=2)")
double getTotalChoixFamilleEnAGR(Integer idExercice, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and p.idTrimestre=?2 and (p.choixFamilleEnAGR=1 or p.choixFamilleEnAGR=2)")
Page<QuestionMenageEntity> getChoixFamilleEnAGR(Integer idExercice, Integer idTrimestre, Pageable pagingSort);

@Query("select p from QuestionMenageEntity p JOIN HouseHoldEntitty  h ON p.idMenage=h.id where h.idExercise=?1 and (p.choixFamilleEnAGR=1 or p.choixFamilleEnAGR=2)")
Page<QuestionMenageEntity> getChoixFamilleEnAGR(Integer idExercice, Pageable pagingSort);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1")
Integer getTotalTrimestreChoixFamilleEnAGR(Integer idTrimestre);

@Query("select count(p) from QuestionMenageEntity p where p.idTrimestre=?1 and (p.choixFamilleEnAGR=1 or p.choixFamilleEnAGR=2)")
double getTotalChoixFamilleEnAGR(Integer idTrimestre);

@Query("select count(p) > 0 from QuestionMenageEntity p where  p.idMenage=?1 and p.idTrimestre=?2")
boolean checkMenageIfExistInTrimestre(Integer idMenage, Integer idTrimestre);

@Query("select p from QuestionMenageEntity p where p.idMenage=?1 and p.idTrimestre=?2 and p.id=?3")
Optional<QuestionMenageEntity> getByIdMenageAndIdTrimestre(Integer idMenage, Integer idTrimestre, Long id);

@Query("select count(p) > 0 from QuestionMenageEntity p where p.idMenage=?1 and p.idTrimestre=?2 and p.id!=?3")
boolean checkMenageIfExistInTrimestre(Integer idMenage, Integer idTrimestre, Long id); 
}
