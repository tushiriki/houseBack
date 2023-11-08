package com.house.convertDto;

import com.house.constante.StaticListOfValues;
import com.house.constante.StaticValue;
import com.house.dto.QuestionMenageDto;
import com.house.entity.QuestionMenageEntity;
import com.house.helper.DateHelper;

public class QuestionMenageConvert {
        public static QuestionMenageConvert getInstance() {
                return new QuestionMenageConvert();
        }

        public QuestionMenageDto toDto(QuestionMenageEntity questionMenageEntity) {
                QuestionMenageDto dto = new QuestionMenageDto();
                dto.setId(questionMenageEntity.getId()); 
        dto.setIdMenage(questionMenageEntity.getIdMenage()); 
         dto.setIdUser(questionMenageEntity.getIdUser()); 
         dto.setIdTrimestre(questionMenageEntity.getIdTrimestre()); 
         dto.setIdUser(questionMenageEntity.getIdUser()); 
        if (questionMenageEntity.getIdTrimestre()!=null && questionMenageEntity.getIdTrimestre()<8) {
			StaticListOfValues slove = new StaticListOfValues();
			StaticValue sv = slove.getTypeTrimestres().get(questionMenageEntity.getIdTrimestre());
			dto.setQuarter(sv); 
		}

        dto.setMenageDto(HouseHoldConvertDto.getInstance().toDto(questionMenageEntity.getMenage()));
        if (questionMenageEntity.getUserEntity()!=null) { 
                questionMenageEntity.getUserEntity().setAcces(null);
                questionMenageEntity.getUserEntity().setPassword(null);  
                   dto.setUserDto(UserConvertDto.getInstance().toDto(questionMenageEntity.getUserEntity())); 
        }

        dto.setMenageProprieteMaison(questionMenageEntity.getMenageProprieteMaison());
        dto.setEtattoitmaison(questionMenageEntity.getEtattoitmaison());
        dto.setTypeToit(questionMenageEntity.getTypeToit());
        dto.setTyperevêtementSol(questionMenageEntity.getTyperevêtementSol());
        dto.setChaise(questionMenageEntity.getChaise());
        dto.setOutilInformation(questionMenageEntity.getOutilInformation());
        dto.setOutilTransport(questionMenageEntity.getOutilTransport());
        dto.setElectricite(questionMenageEntity.getElectricite());
        dto.setElectriciteCuisine(questionMenageEntity.getElectriciteCuisine());
        dto.setMembreMenagePorteChaussirLit(questionMenageEntity.isMembreMenagePorteChaussirLit());
        dto.setMembreMenageDormeMemeChambre(questionMenageEntity.getMembreMenageDormeMemeChambre()); 
        dto.setMaisonAccessiblePersonnesHandicape(questionMenageEntity.getMaisonAccessiblePersonnesHandicape()); 

        dto.setCombienFoisMangeParJour(questionMenageEntity.getCombienFoisMangeParJour()); 
        dto.setRepasImportantJournee(questionMenageEntity.getRepasImportantJournee());
        dto.setChangementAlimentaireHabituerLastTroisMois(questionMenageEntity.isChangementAlimentaireHabituerLastTroisMois());
        dto.setMesureChangementAlimentaireLastTroisMois(questionMenageEntity.getMesureChangementAlimentaireLastTroisMois()); 
        dto.setRaisonChangementAlimentaire(questionMenageEntity.getRaisonChangementAlimentaire());
        dto.setPenurieAlimentaire(questionMenageEntity.isPenurieAlimentaire());
        dto.setPenurieAlimentaireParMois(questionMenageEntity.getPenurieAlimentaireParMois()); 
        dto.setPenurieAlimentaireRaison(questionMenageEntity.getPenurieAlimentaireRaison());
        dto.setCriseAffecteMnage(questionMenageEntity.getCriseAffecteMnage());
        dto.setManqueAlimentQuatreSemaine(questionMenageEntity.isManqueAlimentQuatreSemaine());
        dto.setCombienManqueAlimentQuatreSemaine(questionMenageEntity.getCombienManqueAlimentQuatreSemaine()); 
        dto.setManqueAlimentSoir(questionMenageEntity.isManqueAlimentSoir());
        dto.setNombreManqueAliment(questionMenageEntity.getNombreManqueAliment());
        dto.setManqueAlimentMembre(questionMenageEntity.isManqueAlimentMembre()); 
        dto.setNombreManqueAlimentMembre(questionMenageEntity.getNombreManqueAlimentMembre());
        dto.setEauBoire(questionMenageEntity.isEauBoire()); 
        dto.setQualiteAlimentEauPotableEnfantHandicape(questionMenageEntity.getQualiteAlimentEauPotableEnfantHandicape());    

        dto.setMesureRevenue(questionMenageEntity.getMesureRevenue()); 
        dto.setRevenueChangeDernieresAnnee(questionMenageEntity.getRevenueChangeDernieresAnnee());
        dto.setPrincipaleActiviteDeuxDernierieSemaine(questionMenageEntity.getPrincipaleActiviteDeuxDernierieSemaine());
        dto.setSourceRevenueImportant(questionMenageEntity.getSourceRevenueImportant()); 
        dto.setMontantMinimumParAn(questionMenageEntity.getMontantMinimumParAn());
        dto.setContrainteActivite(questionMenageEntity.getContrainteActivite());
        dto.setNiveauVie(questionMenageEntity.getNiveauVie()); 
        dto.setMontantEstimer(questionMenageEntity.getMontantEstimer());
        dto.setPremiereBesoinManage(questionMenageEntity.getPremiereBesoinManage()); 
        dto.setActivteEnfantHandicapeAGR(questionMenageEntity.isActivteEnfantHandicapeAGR());
        dto.setEnfantHandicapeDicussionGroupe(questionMenageEntity.isEnfantHandicapeDicussionGroupe());
        dto.setFamilleAugmentRevenuParFournie(questionMenageEntity.getFamilleAugmentRevenuParFournie());    
        dto.setEpargneMnsuelleMoyenneParFemme(questionMenageEntity.getEpargneMnsuelleMoyenneParFemme());   
        dto.setFemillePrete(questionMenageEntity.isFemillePrete());   
        dto.setChoixFamilleEnAGR(questionMenageEntity.getChoixFamilleEnAGR()); 
        
        dto.setMenageTerrain(questionMenageEntity.getMenageTerrain()); 
        dto.setMenageSuperficieTerrain(questionMenageEntity.getMenageSuperficieTerrain());
        dto.setProprieteFonciereEnregistre(questionMenageEntity.isProprieteFonciereEnregistre());
        dto.setMenageAgriculture(questionMenageEntity.getMenageAgriculture());    

        dto.setAccesAuMarche(questionMenageEntity.getAccesAuMarche()); 
        dto.setAccesAuMarcheSethDerniereJours(questionMenageEntity.getAccesAuMarcheSethDerniereJours());
        dto.setTempsArriveAuMarche(questionMenageEntity.getTempsArriveAuMarche());
        dto.setFluctuationPrixMarcheSethDerniereJours(questionMenageEntity.getFluctuationPrixMarcheSethDerniereJours());   
        dto.setAccesPaiementMobile(questionMenageEntity.getAccesPaiementMobile());  

        dto.setHospitalSoigne(questionMenageEntity.getHospitalSoigne()); 
        dto.setMembreFimmilleAccidentMaladeQuatreDerniereSemaine(questionMenageEntity.isMembreFimmilleAccidentMaladeQuatreDerniereSemaine());
        dto.setTempsMembreTombeMaladeQuatreDerniereSemaine(questionMenageEntity.getTempsMembreTombeMaladeQuatreDerniereSemaine()); 
        dto.setDistanceEntreHospitalMaison(questionMenageEntity.getDistanceEntreHospitalMaison()); 
        dto.setMembreAssuranceMaladi(questionMenageEntity.isMembreAssuranceMaladi()); 
        dto.setDormeMoustiquaire(questionMenageEntity.isDormeMoustiquaire()); 
        dto.setOuAccouchezVousHabituellement(questionMenageEntity.getOuAccouchezVousHabituellement()); 
        dto.setTempsEntreHospitalMaisonAccoucher(questionMenageEntity.getTempsEntreHospitalMaisonAccoucher()); 
        dto.setProblemePrincipalePourSointSante(questionMenageEntity.getProblemePrincipalePourSointSante()); 
        dto.setMenageHeure(questionMenageEntity.getMenageHeure()); 
        dto.setEnfantHandicapeHeure(questionMenageEntity.getEnfantHandicapeHeure()); 
        dto.setMenageMieuxAccepteSocialementEconomiquementCommunaute(questionMenageEntity.isMenageMieuxAccepteSocialementEconomiquementCommunaute()); 

        dto.setPersonHandicapeExist(questionMenageEntity.getPersonHandicapeExist());
        dto.setCombienPersonHandicapeEnfant(questionMenageEntity.getCombienPersonHandicapeEnfant());
        dto.setCombienPersonHandicapeJeunesse(questionMenageEntity.getCombienPersonHandicapeJeunesse());
        dto.setPersonHandicapeChefMenage(questionMenageEntity.getPersonHandicapeChefMenage());
        dto.setPersonHandicapeChefMenageActivite(questionMenageEntity.getPersonHandicapeChefMenageActivite()); 
        dto.setEvaluationParticipantionAuxFormation(questionMenageEntity.getEvaluationParticipantionAuxFormarmation());//
        dto.setConsiderationPositiveAuxEnfantHendicape(questionMenageEntity.getConsiderationPositiveAuxEnfantHendicape());//

        dto.setNombreGarconNeEtudierPas(questionMenageEntity.getNombreGarconNeEtudierPas());
        dto.setNombreFilleNeEtudierPas(questionMenageEntity.getNombreFilleNeEtudierPas());
        dto.setNombreGarconEtudierPrescolaire(questionMenageEntity.getNombreGarconEtudierPrescolaire());
        dto.setNombreFilleEtudierPrescolaire(questionMenageEntity.getNombreFilleEtudierPrescolaire());
        dto.setNombreGarconEtudierPremiereCycle(questionMenageEntity.getNombreGarconEtudierPremiereCycle());
        dto.setNombreFilleEtudierPremiereCycle(questionMenageEntity.getNombreFilleEtudierPremiereCycle());
        dto.setNombreGarconEtudierDeuxiemeCycle(questionMenageEntity.getNombreGarconEtudierDeuxiemeCycle());
        dto.setNombreFilleEtudierDeuxiemeCycle(questionMenageEntity.getNombreFilleEtudierDeuxiemeCycle());
        dto.setNombreGarconEtudierSecondaire(questionMenageEntity.getNombreGarconEtudierSecondaire());
        dto.setNombreFilleEtudierSecondaire(questionMenageEntity.getNombreFilleEtudierSecondaire());
        dto.setSatisfeteServiceRecuScolaire(questionMenageEntity.getSatisfeteServiceRecuScolaire());
        dto.setPrincipalProblemeScolaire(questionMenageEntity.getPrincipalProblemeScolaire());
        dto.setChefDeEcole(questionMenageEntity.getChefDeEcole());
        dto.setPrincipaleRaisonEnfantNonScolarise(questionMenageEntity.getPrincipaleRaisonEnfantNonScolarise());    
        dto.setEnfantHadicapeAbandonneEcole(questionMenageEntity.isEnfantHadicapeAbandonneEcole());//
        dto.setEnfantHadicapeScolariser(questionMenageEntity.isEnfantHadicapeScolariser());//
        dto.setResourceFinancierePourenfantHadicape(questionMenageEntity.getResourceFinancierePourenfantHadicape());//  

        dto.setMembreFoyer(questionMenageEntity.getMembreFoyer());   
        dto.setPartenaireOrganisation(questionMenageEntity.getPartenaireOrganisation());  
        dto.setTypeOrganisation(questionMenageEntity.getTypeOrganisation());  
        dto.setMembreSHG(questionMenageEntity.getMembreSHG());  
        dto.setMembreStrictureCommunautaire(questionMenageEntity.getMembreStrictureCommunautaire());  
        dto.setMembreStrictureCommunautaireLequel(questionMenageEntity.getMembreStrictureCommunautaireLequel()); 
        dto.setUploaded(questionMenageEntity.getUploaded());
        dto.setDateTime(DateHelper.toText(questionMenageEntity.getDateTime()));  


  
                dto.setYouthWithDisabilitiesNumber(questionMenageEntity.getYouthWithDisabilitiesNumber());
                dto.setChildrenWithDisabilitiesNumber(questionMenageEntity.getChildrenWithDisabilitiesNumber());


                dto.setRoof(questionMenageEntity.getRoof());
                dto.setFlooring(questionMenageEntity.getFlooring());
                dto.setInformationTool(questionMenageEntity.getInformationTool());
                dto.setMainSourceOfEnergy(questionMenageEntity.getMainSourceOfEnergy());
                dto.setLandOwnershipRegistered(questionMenageEntity.getLandOwnershipRegistered());
                dto.setHouseHoldPracticeAgriculture(questionMenageEntity.getHouseHoldPracticeAgriculture());
                dto.setHouseHoldHaveMarketAccess(questionMenageEntity.getHouseHoldHaveMarketAccess());
                dto.setTimeToGetToMarket(questionMenageEntity.getTimeToGetToMarket());
                dto.setHouseHoldHavAccessToMobilePayment(questionMenageEntity.getHouseHoldHavAccessToMobilePayment());
                dto.setWhereToGetTreatment(questionMenageEntity.getWhereToGetTreatment());
                dto.setHealthProblemOrIllnessInLastThreeMonths(questionMenageEntity.getHealthProblemOrIllnessInLastThreeMonths());
                dto.setHowLongIllnessPreventFromActivity(questionMenageEntity.getHowLongIllnessPreventFromActivity());
                dto.setDistanceBetweenResidenceAndConsultation(questionMenageEntity.getDistanceBetweenResidenceAndConsultation());
                dto.setHouseHoldMembersHaveHealthInsurance(questionMenageEntity.getHouseHoldMembersHaveHealthInsurance());
                dto.setWhoRunTheSchoolChildrenAttend(questionMenageEntity.getWhoRunTheSchoolChildrenAttend());

                return dto;
        }

        public QuestionMenageEntity toEntity(QuestionMenageDto questionMenageEntity) {
                QuestionMenageEntity dto = new QuestionMenageEntity();
                dto.setId(questionMenageEntity.getId());
                dto.setIdMenage(questionMenageEntity.getIdMenage());
                dto.setIdTrimestre(questionMenageEntity.getIdTrimestre());
                dto.setIdUser(questionMenageEntity.getIdUser());
                dto.setMenageProprieteMaison(questionMenageEntity.getMenageProprieteMaison());
                dto.setEtattoitmaison(questionMenageEntity.getEtattoitmaison());
                dto.setTypeToit(questionMenageEntity.getTypeToit());
                dto.setTyperevêtementSol(questionMenageEntity.getTyperevêtementSol());
                dto.setChaise(questionMenageEntity.getChaise());
                dto.setOutilInformation(questionMenageEntity.getOutilInformation());
                dto.setOutilTransport(questionMenageEntity.getOutilTransport());
                dto.setElectricite(questionMenageEntity.getElectricite());
                dto.setElectriciteCuisine(questionMenageEntity.getElectriciteCuisine());
                dto.setMembreMenagePorteChaussirLit(questionMenageEntity.isMembreMenagePorteChaussirLit());
                dto.setMembreMenageDormeMemeChambre(questionMenageEntity.getMembreMenageDormeMemeChambre());
                dto.setMaisonAccessiblePersonnesHandicape(questionMenageEntity.getMaisonAccessiblePersonnesHandicape());

                dto.setCombienFoisMangeParJour(questionMenageEntity.getCombienFoisMangeParJour());
                dto.setRepasImportantJournee(questionMenageEntity.getRepasImportantJournee());
                dto.setChangementAlimentaireHabituerLastTroisMois(
                                questionMenageEntity.isChangementAlimentaireHabituerLastTroisMois());
                dto.setMesureChangementAlimentaireLastTroisMois(
                                questionMenageEntity.getMesureChangementAlimentaireLastTroisMois());
                dto.setRaisonChangementAlimentaire(questionMenageEntity.getRaisonChangementAlimentaire());
                dto.setPenurieAlimentaire(questionMenageEntity.isPenurieAlimentaire());
                dto.setPenurieAlimentaireParMois(questionMenageEntity.getPenurieAlimentaireParMois());
                dto.setPenurieAlimentaireRaison(questionMenageEntity.getPenurieAlimentaireRaison());
                dto.setCriseAffecteMnage(questionMenageEntity.getCriseAffecteMnage());
                dto.setManqueAlimentQuatreSemaine(questionMenageEntity.isManqueAlimentQuatreSemaine());
                dto.setCombienManqueAlimentQuatreSemaine(questionMenageEntity.getCombienManqueAlimentQuatreSemaine());
                dto.setManqueAlimentSoir(questionMenageEntity.isManqueAlimentSoir());
                dto.setNombreManqueAliment(questionMenageEntity.getNombreManqueAliment());
                dto.setManqueAlimentMembre(questionMenageEntity.isManqueAlimentMembre());
                dto.setNombreManqueAlimentMembre(questionMenageEntity.getNombreManqueAlimentMembre());
                dto.setEauBoire(questionMenageEntity.isEauBoire());
                dto.setQualiteAlimentEauPotableEnfantHandicape(
                                questionMenageEntity.getQualiteAlimentEauPotableEnfantHandicape());

                dto.setMesureRevenue(questionMenageEntity.getMesureRevenue());
                dto.setRevenueChangeDernieresAnnee(questionMenageEntity.getRevenueChangeDernieresAnnee());
                dto.setPrincipaleActiviteDeuxDernierieSemaine(
                                questionMenageEntity.getPrincipaleActiviteDeuxDernierieSemaine());
                dto.setSourceRevenueImportant(questionMenageEntity.getSourceRevenueImportant());
                dto.setMontantMinimumParAn(questionMenageEntity.getMontantMinimumParAn());
                dto.setContrainteActivite(questionMenageEntity.getContrainteActivite());
                dto.setNiveauVie(questionMenageEntity.getNiveauVie());
                dto.setMontantEstimer(questionMenageEntity.getMontantEstimer());
                dto.setPremiereBesoinManage(questionMenageEntity.getPremiereBesoinManage());
                dto.setActivteEnfantHandicapeAGR(questionMenageEntity.isActivteEnfantHandicapeAGR());
                dto.setEnfantHandicapeDicussionGroupe(questionMenageEntity.isEnfantHandicapeDicussionGroupe());
                dto.setFamilleAugmentRevenuParFournie(questionMenageEntity.getFamilleAugmentRevenuParFournie());
                dto.setEpargneMnsuelleMoyenneParFemme(questionMenageEntity.getEpargneMnsuelleMoyenneParFemme());
                dto.setFemillePrete(questionMenageEntity.isFemillePrete());
                dto.setChoixFamilleEnAGR(questionMenageEntity.getChoixFamilleEnAGR());

                dto.setMenageTerrain(questionMenageEntity.getMenageTerrain());
                dto.setMenageSuperficieTerrain(questionMenageEntity.getMenageSuperficieTerrain());
                dto.setProprieteFonciereEnregistre(questionMenageEntity.isProprieteFonciereEnregistre());
                dto.setMenageAgriculture(questionMenageEntity.getMenageAgriculture());

                dto.setAccesAuMarche(questionMenageEntity.getAccesAuMarche());
                dto.setAccesAuMarcheSethDerniereJours(questionMenageEntity.getAccesAuMarcheSethDerniereJours());
                dto.setTempsArriveAuMarche(questionMenageEntity.getTempsArriveAuMarche());
                dto.setFluctuationPrixMarcheSethDerniereJours(
                                questionMenageEntity.getFluctuationPrixMarcheSethDerniereJours());
                dto.setAccesPaiementMobile(questionMenageEntity.getAccesPaiementMobile());

                dto.setHospitalSoigne(questionMenageEntity.getHospitalSoigne());
                dto.setMembreFimmilleAccidentMaladeQuatreDerniereSemaine(
                                questionMenageEntity.isMembreFimmilleAccidentMaladeQuatreDerniereSemaine());
                dto.setTempsMembreTombeMaladeQuatreDerniereSemaine(
                                questionMenageEntity.getTempsMembreTombeMaladeQuatreDerniereSemaine());
                dto.setDistanceEntreHospitalMaison(questionMenageEntity.getDistanceEntreHospitalMaison());
                dto.setMembreAssuranceMaladi(questionMenageEntity.isMembreAssuranceMaladi());
                dto.setDormeMoustiquaire(questionMenageEntity.isDormeMoustiquaire());
                dto.setOuAccouchezVousHabituellement(questionMenageEntity.getOuAccouchezVousHabituellement());
                dto.setTempsEntreHospitalMaisonAccoucher(questionMenageEntity.getTempsEntreHospitalMaisonAccoucher());
                dto.setProblemePrincipalePourSointSante(questionMenageEntity.getProblemePrincipalePourSointSante());
                dto.setMenageHeure(questionMenageEntity.getMenageHeure());
                dto.setEnfantHandicapeHeure(questionMenageEntity.getEnfantHandicapeHeure());
                dto.setMenageMieuxAccepteSocialementEconomiquementCommunaute(
                                questionMenageEntity.isMenageMieuxAccepteSocialementEconomiquementCommunaute());

                dto.setPersonHandicapeExist(questionMenageEntity.getPersonHandicapeExist());
                dto.setPersonHandicapeChefMenage(questionMenageEntity.getPersonHandicapeChefMenage());
                dto.setPersonHandicapeChefMenageActivite(questionMenageEntity.getPersonHandicapeChefMenageActivite());
                dto.setEvaluationParticipantionAuxFormarmation(
                                questionMenageEntity.getEvaluationParticipantionAuxFormation());//
                dto.setConsiderationPositiveAuxEnfantHendicape(
                                questionMenageEntity.getConsiderationPositiveAuxEnfantHendicape());//

                dto.setNombreGarconNeEtudierPas(questionMenageEntity.getNombreGarconNeEtudierPas());
                dto.setNombreFilleNeEtudierPas(questionMenageEntity.getNombreFilleNeEtudierPas());
                dto.setNombreGarconEtudierPrescolaire(questionMenageEntity.getNombreGarconEtudierPrescolaire());
                dto.setNombreFilleEtudierPrescolaire(questionMenageEntity.getNombreFilleEtudierPrescolaire());
                dto.setNombreGarconEtudierPremiereCycle(questionMenageEntity.getNombreGarconEtudierPremiereCycle());
                dto.setNombreFilleEtudierPremiereCycle(questionMenageEntity.getNombreFilleEtudierPremiereCycle());
                dto.setNombreGarconEtudierDeuxiemeCycle(questionMenageEntity.getNombreGarconEtudierDeuxiemeCycle());
                dto.setNombreFilleEtudierDeuxiemeCycle(questionMenageEntity.getNombreFilleEtudierDeuxiemeCycle());
                dto.setNombreGarconEtudierSecondaire(questionMenageEntity.getNombreGarconEtudierSecondaire());
                dto.setNombreFilleEtudierSecondaire(questionMenageEntity.getNombreFilleEtudierSecondaire());
                dto.setSatisfeteServiceRecuScolaire(questionMenageEntity.getSatisfeteServiceRecuScolaire());
                dto.setPrincipalProblemeScolaire(questionMenageEntity.getPrincipalProblemeScolaire());
                dto.setChefDeEcole(questionMenageEntity.getChefDeEcole());
                dto.setPrincipaleRaisonEnfantNonScolarise(questionMenageEntity.getPrincipaleRaisonEnfantNonScolarise());
                dto.setEnfantHadicapeAbandonneEcole(questionMenageEntity.isEnfantHadicapeAbandonneEcole());//
                dto.setEnfantHadicapeScolariser(questionMenageEntity.isEnfantHadicapeScolariser());//
                dto.setResourceFinancierePourenfantHadicape(
                                questionMenageEntity.getResourceFinancierePourenfantHadicape());//

                dto.setMembreFoyer(questionMenageEntity.getMembreFoyer());
                dto.setPartenaireOrganisation(questionMenageEntity.getPartenaireOrganisation());
                dto.setTypeOrganisation(questionMenageEntity.getTypeOrganisation());
                dto.setMembreSHG(questionMenageEntity.getMembreSHG());
                dto.setMembreStrictureCommunautaire(questionMenageEntity.getMembreStrictureCommunautaire());
                dto.setMembreStrictureCommunautaireLequel(questionMenageEntity.getMembreStrictureCommunautaireLequel());
                dto.setUploaded(questionMenageEntity.getUploaded());

                dto.setDateTime(DateHelper.toDate(questionMenageEntity.getDateTime()));

                dto.setYouthWithDisabilitiesNumber(questionMenageEntity.getYouthWithDisabilitiesNumber());
                dto.setChildrenWithDisabilitiesNumber(questionMenageEntity.getChildrenWithDisabilitiesNumber());



                dto.setRoof(questionMenageEntity.getRoof());
                dto.setFlooring(questionMenageEntity.getFlooring());
                dto.setInformationTool(questionMenageEntity.getInformationTool());
                dto.setMainSourceOfEnergy(questionMenageEntity.getMainSourceOfEnergy());
                dto.setLandOwnershipRegistered(questionMenageEntity.getLandOwnershipRegistered());
                dto.setHouseHoldPracticeAgriculture(questionMenageEntity.getHouseHoldPracticeAgriculture());
                dto.setHouseHoldHaveMarketAccess(questionMenageEntity.getHouseHoldHaveMarketAccess());
                dto.setTimeToGetToMarket(questionMenageEntity.getTimeToGetToMarket());
                dto.setHouseHoldHavAccessToMobilePayment(questionMenageEntity.getHouseHoldHavAccessToMobilePayment());
                dto.setWhereToGetTreatment(questionMenageEntity.getWhereToGetTreatment());
                dto.setHealthProblemOrIllnessInLastThreeMonths(questionMenageEntity.getHealthProblemOrIllnessInLastThreeMonths());
                dto.setHowLongIllnessPreventFromActivity(questionMenageEntity.getHowLongIllnessPreventFromActivity());
                dto.setDistanceBetweenResidenceAndConsultation(questionMenageEntity.getDistanceBetweenResidenceAndConsultation());
                dto.setHouseHoldMembersHaveHealthInsurance(questionMenageEntity.getHouseHoldMembersHaveHealthInsurance());
                dto.setWhoRunTheSchoolChildrenAttend(questionMenageEntity.getWhoRunTheSchoolChildrenAttend());
                return dto;
        }
}
