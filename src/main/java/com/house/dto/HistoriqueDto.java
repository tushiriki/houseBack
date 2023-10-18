package com.house.dto; 
 
 
import lombok.Data;
@Data
public class HistoriqueDto {
    private Long id;
    private Integer idUser;
    private Integer idMenage;
    private Integer idTrimestre;
    private Integer idExercice;
    private String date;  
    private String type; 
    private String classe;
    private HouseHoldDto menage;  
    private UserDto user;  
    private ExerciceDto exercice; 
}
