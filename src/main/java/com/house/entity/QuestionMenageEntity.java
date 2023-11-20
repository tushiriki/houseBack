package com.house.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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

@Entity
@Data
@Table(name = "question_menage")
public class QuestionMenageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  

    @Column(name = "id_trimestre") 
    private Integer idTrimestre;  

    @Column(name = "id_menage")
    private Integer idMenage;

    @Column(name = "id_user")
    private Integer idUser;

    private Integer menageProprieteMaison; 
    private Integer etattoitmaison;
    private Integer typeToit;
    private Integer typerevêtementSol;
    private Integer chaise; 
    private Integer outilInformation;
    private Integer electricite;
    private Integer electriciteCuisine;
    private boolean membreMenagePorteChaussirLit;
    private Integer membreMenageDormeMemeChambre;
    private Integer maisonAccessiblePersonnesHandicape;  

    private Integer combienFoisMangeParJour;
    private Integer repasImportantJournee;
    private boolean changementAlimentaireHabituerLastTroisMois;
    private Integer mesureChangementAlimentaireLastTroisMois;
    private Integer raisonChangementAlimentaire;
    private boolean penurieAlimentaire;
    private Integer penurieAlimentaireParMois ;
    private Integer penurieAlimentaireRaison;
    private Integer criseAffecteMnage ;
    private boolean manqueAlimentQuatreSemaine;
    private Integer combienManqueAlimentQuatreSemaine;
    private boolean manqueAlimentSoir;
    private Integer nombreManqueAliment;
    private boolean manqueAlimentMembre;
    private Integer nombreManqueAlimentMembre;
    private boolean eauBoire;
    private Integer qualiteAlimentEauPotableEnfantHandicape;//
  
    private Integer mesureRevenue;
    private Integer revenueChangeDernieresAnnee;
    private Integer principaleActiviteDeuxDernierieSemaine ;
    private Integer sourceRevenueImportant;
    private Integer montantMinimumParAn ;
    private Integer contrainteActivite;
    private Integer niveauVie ;
    private Double montantEstimer;
    private Integer premiereBesoinManage;
    private boolean activteEnfantHandicapeAGR;//
    private Integer activteEnfantHandicapeAGR2;//
    private boolean enfantHandicapeDicussionGroupe; //
    private Integer enfantHandicapeDicussionGroupe2; // 
    private Integer familleAugmentRevenuParFournie;//
    private double epargneMnsuelleMoyenneParFemme;//
    private boolean femillePrete;//
    private Integer femillePrete2;// 
    private Integer choixFamilleEnAGR;//

    private Integer menageTerrain;
    private Integer menageSuperficieTerrain;
    private boolean proprieteFonciereEnregistre;
    private Integer menageAgriculture;

    private Integer accesAuMarche;
    private Integer accesAuMarcheSethDerniereJours;
    private Integer tempsArriveAuMarche;
    private Integer fluctuationPrixMarcheSethDerniereJours;
    private Integer accesPaiementMobile;
    
    private Integer hospitalSoigne;
    private boolean membreFimmilleAccidentMaladeQuatreDerniereSemaine;
    private Integer tempsMembreTombeMaladeQuatreDerniereSemaine;
    private Integer distanceEntreHospitalMaison;
    private boolean membreAssuranceMaladi;
    private boolean dormeMoustiquaire;
    private Integer ouAccouchezVousHabituellement ;
    private Integer tempsEntreHospitalMaisonAccoucher;
    private Integer problemePrincipalePourSointSante;
    private Integer menageHeure; //
    private Integer enfantHandicapeHeure; //
    private boolean menageMieuxAccepteSocialementEconomiquementCommunaute;//
   

    private Integer personHandicapeExist;
    private Integer combienPersonHandicapeEnfant;
    private Integer combienPersonHandicapeJeunesse;
    private Integer personHandicapeChefMenage;
    private Integer personHandicapeChefMenageActivite;
    private Integer evaluationParticipantionAuxFormarmation;//
    private Integer considerationPositiveAuxEnfantHendicape;//

    private Integer nombreGarconNeEtudierPas;
    private Integer nombreFilleNeEtudierPas;
    private Integer nombreGarconEtudierPrescolaire;
    private Integer nombreFilleEtudierPrescolaire;
    private Integer nombreGarconEtudierPremiereCycle;
    private Integer nombreFilleEtudierPremiereCycle;
    private Integer nombreGarconEtudierDeuxiemeCycle;
    private Integer nombreFilleEtudierDeuxiemeCycle;
    private Integer nombreGarconEtudierSecondaire;
    private Integer nombreFilleEtudierSecondaire;
    private Integer satisfeteServiceRecuScolaire;
    private Integer principalProblemeScolaire;
    private Integer chefDeEcole;
    private Integer principaleRaisonEnfantNonScolarise;
    private boolean enfantHadicapeAbandonneEcole;
    private Integer enfantHadicapeAbandonneEcole2;//
    private boolean enfantHadicapeScolariser;//
    private Integer enfantHadicapeScolariser2;//
    private double resourceFinancierePourenfantHadicape;//

    private Integer membreFoyer;
    private String partenaireOrganisation;
    private Integer typeOrganisation;
    private Integer membreSHG;
    private Integer membreStrictureCommunautaire;
    private String membreStrictureCommunautaireLequel;

    private Boolean uploaded;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menage", referencedColumnName = "id", insertable = false, updatable = false)
    private HouseHoldEntitty menage; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userEntity; 

    private Integer youthWithDisabilitiesNumber;
    private Integer childrenWithDisabilitiesNumber;


    private Integer roof;
    private Integer flooring;
    private Integer informationTool;
    private Integer mainSourceOfEnergy;
    private Boolean landOwnershipRegistered;
    private Boolean houseHoldPracticeAgriculture;
    private Integer houseHoldHaveMarketAccess;
    private Integer timeToGetToMarket;
    private Integer houseHoldHavAccessToMobilePayment;
    private Integer whereToGetTreatment;
    private Boolean healthProblemOrIllnessInLastThreeMonths;
    private Integer howLongIllnessPreventFromActivity;
    private Integer distanceBetweenResidenceAndConsultation;
    private Boolean houseHoldMembersHaveHealthInsurance;
    private Integer whoRunTheSchoolChildrenAttend;
}
