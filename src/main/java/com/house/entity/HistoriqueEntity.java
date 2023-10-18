package com.house.entity; 

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "historique")
public class HistoriqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Long id;
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "id_menage")
    private Integer idMenage;
    @Column(name = "id_trimestre")
    private Integer idTrimestre;
    @Column(name = "id_exercice")
    private Integer idExercice;

    @Column(name = "type")
    private String type;

    @Column(name = "classe")
    private String classe;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menage", referencedColumnName = "id", insertable = false, updatable = false)
    private HouseHoldEntitty menage; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userEntity; 
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exercice", referencedColumnName = "id", insertable = false, updatable = false)
    private ExerciceEntity exerciceEntity; 
}
