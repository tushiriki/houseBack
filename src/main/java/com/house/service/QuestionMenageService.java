package com.house.service;

import java.util.Map;

import com.house.dto.QuestionMenageDto; 

public interface QuestionMenageService {

    Map<String, Object> getAll(Long id, Integer idMenage, Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort);

    boolean upDateQuestion(Long id, QuestionMenageDto questionMenageDto);

    boolean createQuestion(QuestionMenageDto questionMenageDto);

    boolean delete(Long id);

    boolean checkQuarter(Integer idMenage, Integer idQuarter);

    Map<String, Object> getAmeliorationBienEtre(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort);

    Map<String, Object> getAmeliorationNiveauVie(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort);

    Map<String, Object> getAmeliorationCompetences(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort);

    Map<String, Object> getAmeliorationCapitalSocial(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort);

    Map<String, Object> getRevenusActifsProductifs(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort);//

    boolean checkMenageIfExistInTrimestre(Integer idMenage, Integer idTrimestre);

    boolean checkQuarter(Integer idMenage, Integer idTrimestre, Long id);

    boolean checkMenageIfExistInTrimestre(Integer idMenage, Integer idTrimestre, Long id);
    
}
