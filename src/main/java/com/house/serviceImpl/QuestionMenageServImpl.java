package com.house.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.house.convertDto.QuestionMenageConvert;
import com.house.dto.QuestionMenageDto;
import com.house.entity.HistoriqueEntity;
import com.house.entity.QuestionMenageEntity; 
import com.house.helper.PagingAndSortingHelper;
import com.house.repository.HistoriqueRepository;
import com.house.repository.QuestionMenageRepository;
import com.house.service.QuestionMenageService;
 

@Service
public class QuestionMenageServImpl implements QuestionMenageService{
   
    @Autowired
    private QuestionMenageRepository menageRepository;

      @Autowired
     private HistoriqueRepository historiqueRepository;

    @Override
    public Map<String, Object> getAll(Long id, Integer idMenage, Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort) {
        Pageable pagingSort = PagingAndSortingHelper.pagination(sort, page, size);
        Page<QuestionMenageEntity> pg = null;   
        if (idMenage!=null && idExercice!=null && idTrimestre!=null) {
			pg = menageRepository.findByIdMenageAndIdTrimestre(idMenage,idTrimestre,pagingSort);
		} else if (idMenage==null && idExercice!=null && idTrimestre!=null) {
			pg = menageRepository.getByIdExerciceAndIdTrimestre(idExercice,idTrimestre,pagingSort);
		} else if (idMenage!=null && idExercice!=null && idTrimestre==null) {
			pg = menageRepository.findByIdMenage(idMenage,pagingSort);
		}else if (idMenage==null && idExercice!=null && idTrimestre==null) {
			pg = menageRepository.getByIdExercice(idExercice,pagingSort);
		} else if (idMenage!=null && idExercice==null && idTrimestre==null) {
			pg = menageRepository.findByIdMenage(idMenage,pagingSort);
		} else if (idMenage!=null && idExercice==null && idTrimestre!=null) {
			pg = menageRepository.findByIdMenageAndIdTrimestre(idMenage,idTrimestre,pagingSort);
		} else if (idMenage==null && idExercice==null && idTrimestre!=null) {
			pg = menageRepository.findByIdTrimestre(idTrimestre,pagingSort);
		} else {
			pg = menageRepository.findAll(pagingSort);
		}

        List<QuestionMenageDto> list = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list.add(questionMenageDto);
        } 
      Map<String, Object> dtos = PagingAndSortingHelper.filteredAndSortedResult(pg.getNumber(), pg.getSize(), pg.getTotalPages(),
        list);

     return dtos;
    }

    @Override
    public boolean upDateQuestion(Long id, QuestionMenageDto questionMenageDto) {
      
        questionMenageDto.setId(id);
		QuestionMenageEntity dataQuestion = QuestionMenageConvert.getInstance().toEntity(questionMenageDto);
        
        QuestionMenageEntity  data = menageRepository.findById(id).get();
		try { 
               if (data!=null) {
                 QuestionMenageDto question = QuestionMenageConvert.getInstance().toDto(data);
                HistoriqueEntity entity = new HistoriqueEntity(); 
                entity.setIdUser(question.getIdUser()); 
                entity.setIdMenage(question.getIdMenage());
                entity.setIdExercice(question.getMenageDto().getIdExercise());
                entity.setIdTrimestre(question.getIdTrimestre());   
                 entity.setType("update"); 
                entity.setClasse("questionnaire"); 
                entity.setDate(new Date());
                   historiqueRepository.save(entity); 
               } 
            dataQuestion.setDateTime(new Date());
           menageRepository.save(dataQuestion);
                    return true; 
		} catch (Exception e) {
			return false;
		}
    }

    @Override
    public boolean createQuestion(QuestionMenageDto questionMenageDto) {
           QuestionMenageEntity data = QuestionMenageConvert.getInstance().toEntity(questionMenageDto);  
		try { 
            data.setDateTime(new Date());
		   menageRepository.save(data); 
           return true;
		} catch (Exception e) {
			return false;
		} 
    }

    @Override
    public boolean delete(Long id) {
         QuestionMenageEntity  data = menageRepository.findById(id).get();
        try {  
                 QuestionMenageDto question = QuestionMenageConvert.getInstance().toDto(data);
                HistoriqueEntity entity = new HistoriqueEntity(); 
                entity.setIdUser(question.getIdUser()); 
                entity.setIdMenage(question.getIdMenage());
                entity.setIdExercice(question.getMenageDto().getIdExercise());
                entity.setIdTrimestre(question.getIdTrimestre());   
                 entity.setType("delete"); 
                entity.setClasse("questionnaire"); 
                entity.setDate(new Date());
                              
                historiqueRepository.save(entity);  
                menageRepository.deleteById(id);
            
            return true;
        } catch (Exception e) {
            return false;
        }
    } 
    
    @Override
    public boolean checkQuarter(Integer idMenage,Integer idQuarter) { 
          return menageRepository.getByIdMenageAndIdTrimestre(idMenage,idQuarter);  
    }

    public List<Integer> listTrimestre(Integer idExercice)
    {
         List<Integer> trimestre=new ArrayList<>();
         if (idExercice!=null) {
            trimestre=menageRepository.getTrimestreByExercice(idExercice);
         } 
        return trimestre;

    } 
    
      public double pourcentage(double nombre,Integer nombreTotal)
    { 
           if (nombre>0 && nombreTotal>0) {
               return (nombre*100)/nombreTotal;  
            } else{
               return 0;
            }
       

    }

      public double moyenne(List<Double> nombre)
    {
        Integer count= nombre.size(); 
       
        double somme=0; 
        for (int i = 0; i < nombre.size(); i++) {
            if (nombre.get(i)!=null && nombre.get(i)>0) {
              somme+=nombre.get(i)/count;  
            } else{
               somme+=0;
            } 
        } 
         
        return somme; 
    }

    

    @Override
    public Map<String, Object> getAmeliorationBienEtre(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort) {

       Map<String,Object> dataReturn=new HashMap<>();
     Pageable pagingSort = PagingAndSortingHelper.pagination(sort, page, size);
        Page<QuestionMenageEntity> pg = null; 
        Integer nombreTotal=0;
        double nombre=0;
        List<Double> sommeMoyene=new ArrayList<>(); 
        List<Double> sommeMoyenePourcentage=new ArrayList<>(); 

        double pourcentage=0;
        double moyenne=0;
        double moyennePourcentage=0;

        // PERSONNE ASSEZ A MANGES
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalMenageAssezMange(idExercice,idTrimestre);
            if (nombre>0 || nombreTotal>0) { 
           pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(nombre);
            sommeMoyene.add(nombre); 
		      }
           
            pg = menageRepository.getMenageAssezMange(idExercice,idTrimestre,pagingSort); 
           
		      
         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
        nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalMenageAssezMange(idExercice,trimestre.get(index));
                if (nombre>0 || nombreTotal>0) {
                 pourcentage=pourcentage(nombre, nombreTotal);
                 sommeMoyenePourcentage.add(pourcentage);
                 sommeMoyene.add(nombre); 
		         } 
             
            }
			pg = menageRepository.getMenageAssezMange(idExercice,pagingSort); 
           
		    
         }else{
            List<Integer> trimestre=listTrimestre(null); 
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestre(trimestre.get(index));
            nombre=menageRepository.getTotalMenageAssezMange(trimestre.get(index));
               if (nombre>0 || nombreTotal>0) {
               pourcentage=pourcentage(nombre, nombreTotal);
               sommeMoyenePourcentage.add(pourcentage);
               sommeMoyene.add(nombre); 
		      }
              
         }
             
			pg = menageRepository.getMenageAssezMange(idExercice,pagingSort);
		    
         }  
        // moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);

         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donneeAssezMange=new HashMap<>();

        List<QuestionMenageDto> list = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list.add(questionMenageDto);
        } 

      
         if (!list.isEmpty()) {
          donneeAssezMange.put("moyenneTrimestre", sommeMoyene); 
          donneeAssezMange.put("moyenneTotal",  moyenne);   
          donneeAssezMange.put("pourcentageTrimestre", sommeMoyenePourcentage); 
          donneeAssezMange.put("PourcentageTotalMoyenne", moyennePourcentage);
         }else{
           donneeAssezMange.put("moyenneTrimestre", 0.0); 
          donneeAssezMange.put("moyenneTotal",  0.0);   
          donneeAssezMange.put("pourcentageTrimestre", 0.0); 
          donneeAssezMange.put("PourcentageTotalMoyenne", 0.0); 
         }
 
        donneeAssezMange.put("details",list );

        dataReturn.put("menageAssezMange", donneeAssezMange);
           nombreTotal=0;
           sommeMoyenePourcentage.clear();
           sommeMoyene.clear(); 
        //2. Nombre moyen d'aliments et d'eau potable de qualité pour les enfants handicapés de la famille 
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalListDeux(idExercice,idTrimestre);
            nombre=menageRepository.getTotalQualiteAlimentEauPotableEnfantHandicape(idExercice,idTrimestre); 
            pourcentage=pourcentage(nombre, nombreTotal)+0; 
			pg = menageRepository.getQualiteAlimentEauPotableEnfantHandicape(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalListDeux(idExercice,trimestre.get(index));
            nombre=menageRepository.getTotalQualiteAlimentEauPotableEnfantHandicape(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getQualiteAlimentEauPotableEnfantHandicape(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreQualiteAlimentEauPotableEnfantHandicape(trimestre.get(index));
            nombre=menageRepository.getTotalQualiteAlimentEauPotableEnfantHandicape(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getQualiteAlimentEauPotableEnfantHandicape(idExercice,pagingSort);
		    
         } 
         
         moyennePourcentage=moyenne(sommeMoyenePourcentage); 
         moyenne=moyenne(sommeMoyene); 
         Map<String,Object> donnee2=new HashMap<>();

        List<QuestionMenageDto> list2 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity2 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity2) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list2.add(questionMenageDto);
        } 

      
         if (!list2.isEmpty()) {
        donnee2.put("moyenneTrimestre", sommeMoyene); 
        donnee2.put("moyenneTotal",  moyenne);   
        donnee2.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee2.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee2.put("details",list2);
         }else{
        donnee2.put("moyenneTrimestre", 0.0); 
        donnee2.put("moyenneTotal",  0.0);   
        donnee2.put("pourcentageTrimestre", 0.0); 
        donnee2.put("PourcentageTotalMoyenne", 0.0);
        donnee2.put("details",list2);  
         }

        dataReturn.put("qualiteAlimentEauEvh", donnee2);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();
         // 3.	Pourcentage de jeunes handicapés ayant des activités génératrices de revenus (AGR)
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalListTrois(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalJeunesHandicapeAGR(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getJeunesHandicapeAGR(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
         nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) { 
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalListTrois(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalJeunesHandicapeAGR(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getJeunesHandicapeAGR(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(0);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreJeunesHandicapeAGR(trimestre.get(index));
            nombre=menageRepository.getTotalJeunesHandicapeAGR(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getJeunesHandicapeAGR(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee3=new HashMap<>();

        List<QuestionMenageDto> list3 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity3 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity3) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list3.add(questionMenageDto);
        } 

      
       if (!list3.isEmpty()) {
        donnee3.put("moyenneTrimestre", sommeMoyene); 
        donnee3.put("moyenneTotal",  moyenne);   
        donnee3.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee3.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee3.put("details",list3);
         }else{
        donnee3.put("moyenneTrimestre", 0.0); 
        donnee3.put("moyenneTotal",  0.0);   
        donnee3.put("pourcentageTrimestre", 0.0); 
        donnee3.put("PourcentageTotalMoyenne", 0.0);
        donnee3.put("details",list3);  
         }

        dataReturn.put("JeuneEvhAuAGR", donnee3);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();
         // 4.	Moyenne Dans les discussions de groupe sur les questions concernant les enfants handicapés (EVH)
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalListQuatre(idExercice,idTrimestre); 
            nombre=menageRepository.getTotalEnfantHandicapeDicussionGroupe(idExercice,idTrimestre); 
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getEnfantHandicapeDicussionGroupe(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalListQuatre(idExercice,trimestre.get(index));
            nombre=menageRepository.getTotalEnfantHandicapeDicussionGroupe(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEnfantHandicapeDicussionGroupe(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreEnfantHandicapeDicussionGroupe(trimestre.get(index));
            nombre=menageRepository.getTotalEnfantHandicapeDicussionGroupe(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEnfantHandicapeDicussionGroupe(idExercice,pagingSort);
		    
         } 
         
         moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee4=new HashMap<>();

        List<QuestionMenageDto> list4 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity4 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity4) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list4.add(questionMenageDto);
        }  

        if (!list4.isEmpty()) {
        donnee4.put("moyenneTrimestre", sommeMoyene); 
        donnee4.put("moyenneTotal",  moyenne);   
        donnee4.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee4.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee4.put("details",list4);
         }else{
        donnee4.put("moyenneTrimestre", 0.0); 
        donnee4.put("moyenneTotal",  0.0);   
        donnee4.put("pourcentageTrimestre", 0.0); 
        donnee4.put("PourcentageTotalMoyenne", 0.0);
        donnee4.put("details",list4);  
         } 
         
        dataReturn.put("enfantHandicapeDicussionGroupe", donnee4);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();
       // 5.	Le nombre moyen de repas familiaux par jour (3.1)
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalListCinq(idExercice,idTrimestre); 
             moyenne=menageRepository.getTotalCombienFoisMangeParJour(idExercice,idTrimestre); 
             pourcentage=pourcentage(moyenne, nombreTotal);
			pg = menageRepository.getCombienFoisMangeParJour(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(moyenne);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalListCinq(idExercice,trimestre.get(index));
            moyenne=menageRepository.getTotalCombienFoisMangeParJour(idExercice,trimestre.get(index));
            pourcentage=pourcentage(moyenne, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(moyenne); 
             }
			pg = menageRepository.getCombienFoisMangeParJour(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreCombienFoisMangeParJour(trimestre.get(index));
            moyenne=menageRepository.getTotalCombienFoisMangeParJour(trimestre.get(index));
            pourcentage=pourcentage(moyenne, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(moyenne); 
             }
			pg = menageRepository.getCombienFoisMangeParJour(idExercice,pagingSort);
		    
         } 
            
         moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyenne=moyenne(sommeMoyene);
      
         Map<String,Object> donnee5=new HashMap<>();

        List<QuestionMenageDto> list5 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity5 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity5) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list5.add(questionMenageDto);
        } 

      
        if (!list5.isEmpty()) {
        donnee5.put("moyenneTrimestre", sommeMoyene); 
        donnee5.put("moyenneTotal",  moyenne);   
        donnee5.put("pourcentageTrimestre", sommeMoyenePourcentage);  
        donnee5.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee5.put("details",list5);
         }else{
        donnee5.put("moyenneTrimestre", 0.0); 
        donnee5.put("moyenneTotal",  0.0);   
        donnee5.put("pourcentageTrimestre", 0.0); 
        donnee5.put("PourcentageTotalMoyenne", 0.0);
        donnee5.put("details",list5);  
         }

        dataReturn.put("NombreMoyenMangeParJour", donnee5);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();
  // 6.	Durée moyenne d'une période au cours des 12 derniers mois pendant laquelle les ménages ciblés ont eu suffisamment de nourriture pour répondre aux besoins de leurs membres (3.3)
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalPenurieAlimentaireList(idExercice,idTrimestre);
            nombre=menageRepository.getTotalPenurieAlimentaire(idExercice,idTrimestre); 
			pg = menageRepository.getPenurieAlimentaire(idExercice,idTrimestre,pagingSort); 
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalPenurieAlimentaireList(idExercice,trimestre.get(index));
            nombre=menageRepository.getTotalPenurieAlimentaire(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getPenurieAlimentaire(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestrePenurieAlimentaire(trimestre.get(index));
            nombre=menageRepository.getTotalPenurieAlimentaire(trimestre.get(index));
          
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);  
             }
			pg = menageRepository.getPenurieAlimentaire(idExercice,pagingSort);
		    
         } 
         
         moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee6=new HashMap<>();

        List<QuestionMenageDto> list6 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity6 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity6) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list6.add(questionMenageDto);
        } 

      
        if (!list6.isEmpty()) {
        donnee6.put("moyenneTrimestre", sommeMoyene); 
        donnee6.put("moyenneTotal",  moyenne);   
        donnee6.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee6.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee6.put("details",list6);
         }else{
        donnee6.put("moyenneTrimestre", 0.0); 
        donnee6.put("moyenneTotal",  0.0);   
        donnee6.put("pourcentageTrimestre", 0.0); 
        donnee6.put("PourcentageTotalMoyenne", 0.0);
        donnee6.put("details",list6);  
         }

        dataReturn.put("penurieAlimentaire", donnee6);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();
  // 7.	% de ménages ciblés dont les membres ont dû se coucher le soir le ventre vide au cours des 4 dernières semaines en raison d'un manque de nourriture 
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalListSeth(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalManqueAlimentQuatreSemaine(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getManqueAlimentQuatreSemaine(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
       nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalListSeth(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalManqueAlimentQuatreSemaine(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getManqueAlimentQuatreSemaine(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalTrimestreManqueAlimentQuatreSemaine(trimestre.get(index));
            nombre+=menageRepository.getTotalManqueAlimentQuatreSemaine(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getManqueAlimentQuatreSemaine(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
          moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee7=new HashMap<>();

        List<QuestionMenageDto> list7 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity7 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity7) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list7.add(questionMenageDto);
        } 

      
        if (!list7.isEmpty()) {
        donnee7.put("moyenneTrimestre", sommeMoyene); 
        donnee7.put("moyenneTotal",  moyenne);   
        donnee7.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee7.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee7.put("details",list7);
         }else{
        donnee7.put("moyenneTrimestre", 0.0); 
        donnee7.put("moyenneTotal",  0.0);   
        donnee7.put("pourcentageTrimestre", 0.0); 
        donnee7.put("PourcentageTotalMoyenne", 0.0);
        donnee7.put("details",list7);  
         }

        dataReturn.put("manqueNourritureQuatreDernierreSemaine", donnee7);



      Map<String, Object> dtos = PagingAndSortingHelper.filteredAndSortedResult(pg.getNumber(), pg.getSize(), pg.getTotalPages(),
        dataReturn);

     return dtos;    
        
        }

    @Override
    public Map<String, Object> getAmeliorationNiveauVie(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort) {
        Map<String,Object> dataReturn=new HashMap<>();
     Pageable pagingSort = PagingAndSortingHelper.pagination(sort, page, size);
        Page<QuestionMenageEntity> pg = null; 
        Integer nombreTotal=0;
        double nombre=0;
        List<Double> sommeMoyene=new ArrayList<>(); 
        List<Double> sommeMoyenePourcentage=new ArrayList<>(); 

        double pourcentage=0;
        double moyenne=0;
        double moyennePourcentage=0;
        // 1.	Nombre d'actifs possédés par ménage (2.5, 5.1)
        int nombreTerrain=0;
        Map<String,Object> donnee1=new HashMap<>();

        if (idExercice!=null && idTrimestre!=null) {  
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
           nombreTerrain=menageRepository.getTotalMenageTerrainActif(idExercice,idTrimestre); 
			pg = menageRepository.getMenageTerrainActif(idExercice,idTrimestre,pagingSort); 
            donnee1.put("nombreTrimestre", nombreTerrain);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTerrain=menageRepository.getTotalMenageTerrainActif(idExercice,idTrimestre); 
                 donnee1.put("nombreTrimestre", nombreTerrain); 
             }  
			pg = menageRepository.getMenageTerrainActif(idExercice,idTrimestre,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
             nombreTerrain=menageRepository.getTotalMenageTerrainActif(idExercice,idTrimestre);  
                donnee1.put("nombreTrimestre", nombreTerrain);
             }  
			pg = menageRepository.getMenageTerrainActif(idExercice,idTrimestre,pagingSort);
		    
         }  

      

        List<QuestionMenageDto> list = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list.add(questionMenageDto);
        } 
        
        donnee1.put("details",list );
        dataReturn.put("menageTerrain", donnee1);

           sommeMoyenePourcentage.clear();
           sommeMoyene.clear(); 
        // 2.	Pourcentage d'enfants et de jeunes handicapés ayant abandonné l'école L'un de vos enfants ou jeunes handicapés a-t-il abandonné l'école (oui/non) ?
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalListAVH(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalEnfantHadicapeAbandonneEcole(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getEnfantHadicapeAbandonneEcole(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
        nombreTotal=0;
        nombre=0;
          for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalListAVH(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalEnfantHadicapeAbandonneEcole(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEnfantHadicapeAbandonneEcole(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreEnfantHadicapeAbandonneEcole(trimestre.get(index));
            nombre=menageRepository.getTotalEnfantHadicapeAbandonneEcole(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEnfantHadicapeAbandonneEcole(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee2=new HashMap<>();

        List<QuestionMenageDto> list2 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity2 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity2) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list2.add(questionMenageDto);
        } 

      
        if (!list2.isEmpty()) {
        donnee2.put("moyenneTrimestre", sommeMoyene); 
        donnee2.put("moyenneTotal",  moyenne);   
        donnee2.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee2.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee2.put("details",list2);
         }else{
        donnee2.put("moyenneTrimestre", 0.0); 
        donnee2.put("moyenneTotal",  0.0);   
        donnee2.put("pourcentageTrimestre", 0.0); 
        donnee2.put("PourcentageTotalMoyenne", 0.0);
        donnee2.put("details",list2);  
         }

        dataReturn.put("enfantHadicapeAbandonneEcole", donnee2);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();

         // 3.	% de ménages dont les enfants handicapés en âge d'aller à l'école sont scolarisés vos enfants handicapés en âge d'aller à l'école sont-ils actuellement scolarisés (oui/non) ?

        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalAVHScolarise(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalEnfantHadicapeScolariser(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getEnfantHadicapeScolariser(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
       nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalAVHScolarise(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalEnfantHadicapeScolariser(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEnfantHadicapeScolariser(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
        nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreEnfantHadicapeScolariser(trimestre.get(index));
            nombre=menageRepository.getTotalEnfantHadicapeScolariser(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEnfantHadicapeScolariser(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee3=new HashMap<>();

        List<QuestionMenageDto> list3 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity3 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity3) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list3.add(questionMenageDto);
        } 

      
        if (!list3.isEmpty()) {
        donnee3.put("moyenneTrimestre", sommeMoyene); 
        donnee3.put("moyenneTotal",  moyenne);   
        donnee3.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee3.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee3.put("details",list3);
         }else{
        donnee3.put("moyenneTrimestre", 0.0); 
        donnee3.put("moyenneTotal",  0.0);   
        donnee3.put("pourcentageTrimestre", 0.0); 
        donnee3.put("PourcentageTotalMoyenne", 0.0);
        donnee3.put("details",list3);  
         }

        dataReturn.put("enfantHadicapeScolariser", donnee3);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();

         // 4.	Pourcentage de l'argent directement investi dans les questions relatives aux enfants handicapés
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalInvestiAVH(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalResourceFinancierePourenfantHadicape(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getResourceFinancierePourenfantHadicape(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
        nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalInvestiAVH(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalResourceFinancierePourenfantHadicape(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getResourceFinancierePourenfantHadicape(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreResourceFinancierePourenfantHadicape(trimestre.get(index));
            nombre=menageRepository.getTotalResourceFinancierePourenfantHadicape(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getResourceFinancierePourenfantHadicape(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee4=new HashMap<>();

        List<QuestionMenageDto> list4 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity4 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity4) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list4.add(questionMenageDto);
        } 

      
            if (!list4.isEmpty()) {
        donnee4.put("moyenneTrimestre", sommeMoyene); 
        donnee4.put("moyenneTotal",  moyenne);   
        donnee4.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee4.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee4.put("details",list4);
         }else{
        donnee4.put("moyenneTrimestre", 0.0); 
        donnee4.put("moyenneTotal",  0.0);   
        donnee4.put("pourcentageTrimestre", 0.0); 
        donnee4.put("PourcentageTotalMoyenne", 0.0);
        donnee4.put("details",list4);  
         }
        
        dataReturn.put("resourceFinancierePourenfantHadicape", donnee4);
       
        Map<String, Object> dtos = PagingAndSortingHelper.filteredAndSortedResult(pg.getNumber(), pg.getSize(), pg.getTotalPages(),
        dataReturn);

     return dtos;   
         
    }

    @Override
    public Map<String, Object> getAmeliorationCompetences(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort) {
       
         Map<String,Object> dataReturn=new HashMap<>();
     Pageable pagingSort = PagingAndSortingHelper.pagination(sort, page, size);
        Page<QuestionMenageEntity> pg = null; 
        Integer nombreTotal=0;
        double nombre=0;
        List<Double> sommeMoyene=new ArrayList<>(); 
        List<Double> sommeMoyenePourcentage=new ArrayList<>(); 

        double pourcentage=0;
        double moyenne=0;
        double moyennePourcentage=0;

        // 1.	% de participants ayant trouvé le programme de formation très utile
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalProgrammeFormation(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalEvaluationParticipantionAuxFormarmation(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getEvaluationParticipantionAuxFormarmation(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
        nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) { 
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalProgrammeFormation(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalEvaluationParticipantionAuxFormarmation(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEvaluationParticipantionAuxFormarmation(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestre(trimestre.get(index));
            nombre=menageRepository.getTotalEvaluationParticipantionAuxFormarmation(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEvaluationParticipantionAuxFormarmation(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donneeAssezMange=new HashMap<>();

        List<QuestionMenageDto> list = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list.add(questionMenageDto);
        } 

      
            if (!list.isEmpty()) {
        donneeAssezMange.put("moyenneTrimestre", sommeMoyene); 
        donneeAssezMange.put("moyenneTotal",  moyenne);   
        donneeAssezMange.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donneeAssezMange.put("PourcentageTotalMoyenne", moyennePourcentage);
        donneeAssezMange.put("details",list );
         }else{
        donneeAssezMange.put("moyenneTrimestre", 0.0); 
        donneeAssezMange.put("moyenneTotal",  0.0);   
        donneeAssezMange.put("pourcentageTrimestre", 0.0); 
        donneeAssezMange.put("PourcentageTotalMoyenne", 0.0);
        donneeAssezMange.put("details",list);  
         }

  

        dataReturn.put("evaluationParticipantionAuxFormation", donneeAssezMange);

           sommeMoyenePourcentage.clear();
           sommeMoyene.clear(); 

        // 2.	% Sur la famille EVH (enfant vivant avec handicape) Considérations sur la pensée positive 
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalAVH(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalConsiderationPositiveAuxEnfantHendicape(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getConsiderationPositiveAuxEnfantHendicape(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
         nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalAVH(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalConsiderationPositiveAuxEnfantHendicape(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getConsiderationPositiveAuxEnfantHendicape(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
         nombreTotal=0;
        nombre=0;
        for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreConsiderationPositiveAuxEnfantHendicape(trimestre.get(index));
            nombre=menageRepository.getTotalConsiderationPositiveAuxEnfantHendicape(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getConsiderationPositiveAuxEnfantHendicape(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee2=new HashMap<>();

        List<QuestionMenageDto> list2 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity2 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity2) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list2.add(questionMenageDto);
        } 

      
           if (!list2.isEmpty()) {
        donnee2.put("moyenneTrimestre", sommeMoyene); 
        donnee2.put("moyenneTotal",  moyenne);   
        donnee2.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee2.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee2.put("details",list2);
         }else{
        donnee2.put("moyenneTrimestre", 0.0); 
        donnee2.put("moyenneTotal",  0.0);   
        donnee2.put("pourcentageTrimestre", 0.0); 
        donnee2.put("PourcentageTotalMoyenne", 0.0);
        donnee2.put("details",list2);  
         }
        dataReturn.put("considerationPositiveAuxEnfantHendicape", donnee2);

    Map<String, Object> dtos = PagingAndSortingHelper.filteredAndSortedResult(pg.getNumber(), pg.getSize(), pg.getTotalPages(),
        dataReturn);

     return dtos;   
    }

    @Override
    public Map<String, Object> getAmeliorationCapitalSocial(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort) {

    Map<String,Object> dataReturn=new HashMap<>();
     Pageable pagingSort = PagingAndSortingHelper.pagination(sort, page, size);
        Page<QuestionMenageEntity> pg = null; 
        Integer nombreTotal=0;
        double nombre=0;
        List<Double> sommeMoyene=new ArrayList<>(); 
        List<Double> sommeMoyenePourcentage=new ArrayList<>(); 

        double pourcentage=0;
        double moyenne=0;
        double moyennePourcentage=0;

        //1.	 % de ménages déclarant être plus heureux
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalMenageHeure(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getMenageHeure(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
         nombreTotal=0;
        nombre=0;
            for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalMenageHeure(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getMenageHeure(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestre(trimestre.get(index));
            nombre=menageRepository.getTotalMenageHeure(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getMenageHeure(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donneeAssezMange=new HashMap<>();

        List<QuestionMenageDto> list = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list.add(questionMenageDto);
        } 
 
              if (!list.isEmpty()) {
      donneeAssezMange.put("moyenneTrimestre", sommeMoyene); 
        donneeAssezMange.put("moyenneTotal",  moyenne);   
        donneeAssezMange.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donneeAssezMange.put("PourcentageTotalMoyenne", moyennePourcentage);
        donneeAssezMange.put("details",list );
         }else{
        donneeAssezMange.put("moyenneTrimestre", 0.0); 
        donneeAssezMange.put("moyenneTotal",  0.0);   
        donneeAssezMange.put("pourcentageTrimestre", 0.0); 
        donneeAssezMange.put("PourcentageTotalMoyenne", 0.0);
        donneeAssezMange.put("details",list);  
         }

        dataReturn.put("menageHeure", donneeAssezMange);

           sommeMoyenePourcentage.clear();
           sommeMoyene.clear(); 
        //2.	% d'enfants et de jeunes handicapés déclarant être plus heureux 
        if (idExercice!=null && idTrimestre!=null) {
        nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalEnfantHandicapeHeure(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getEnfantHandicapeHeure(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
        nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalEnfantHandicapeHeure(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEnfantHandicapeHeure(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreEnfantHandicapeHeure(trimestre.get(index));
            nombre=menageRepository.getTotalEnfantHandicapeHeure(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEnfantHandicapeHeure(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee2=new HashMap<>();

        List<QuestionMenageDto> list2 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity2 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity2) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list2.add(questionMenageDto);
        } 

      
             if (!list2.isEmpty()) {
        donnee2.put("moyenneTrimestre", sommeMoyene); 
        donnee2.put("moyenneTotal",  moyenne);   
        donnee2.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee2.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee2.put("details",list2);
         }else{
        donnee2.put("moyenneTrimestre", 0.0); 
        donnee2.put("moyenneTotal",  0.0);   
        donnee2.put("pourcentageTrimestre", 0.0); 
        donnee2.put("PourcentageTotalMoyenne", 0.0);
        donnee2.put("details",list2);  
         }

        dataReturn.put("enfantHandicapeHeure", donnee2);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();
         //3.	% de ménages déclarant être mieux acceptés socialement et économiquement au sein de la communauté
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalMenageMieuxAccepteSocialementEconomiquementCommunaute(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getMenageMieuxAccepteSocialementEconomiquementCommunaute(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
        nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalMenageMieuxAccepteSocialementEconomiquementCommunaute(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getMenageMieuxAccepteSocialementEconomiquementCommunaute(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreMenageMieuxAccepteSocialementEconomiquementCommunaute(trimestre.get(index));
            nombre=menageRepository.getTotalMenageMieuxAccepteSocialementEconomiquementCommunaute(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getMenageMieuxAccepteSocialementEconomiquementCommunaute(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee3=new HashMap<>();

        List<QuestionMenageDto> list3 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity3 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity3) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list3.add(questionMenageDto);
        } 

      
        if (!list3.isEmpty()) {
        donnee3.put("moyenneTrimestre", sommeMoyene); 
        donnee3.put("moyenneTotal",  moyenne);   
        donnee3.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee3.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee3.put("details",list3);
         }else{
        donnee3.put("moyenneTrimestre", 0.0); 
        donnee3.put("moyenneTotal",  0.0);   
        donnee3.put("pourcentageTrimestre", 0.0); 
        donnee3.put("PourcentageTotalMoyenne", 0.0);
        donnee3.put("details",list3);  
         }

        dataReturn.put("menageMieuxAccepteSocialementEconomiquementCommunaute", donnee3);

    Map<String, Object> dtos = PagingAndSortingHelper.filteredAndSortedResult(pg.getNumber(), pg.getSize(), pg.getTotalPages(),
        dataReturn);

     return dtos; 

    }

    @Override
    public Map<String, Object> getRevenusActifsProductifs(Integer idExercice, Integer idTrimestre, int page, int size,
            String[] sort) {
       
       Map<String,Object> dataReturn=new HashMap<>();
     Pageable pagingSort = PagingAndSortingHelper.pagination(sort, page, size);
        Page<QuestionMenageEntity> pg = null; 
        Integer nombreTotal=0;
        double nombre=0;
        List<Double> sommeMoyene=new ArrayList<>(); 
        List<Double> sommeMoyenePourcentage=new ArrayList<>(); 

        double pourcentage=0;
        double moyenne=0;
        double moyennePourcentage=0;

        // 1.	% de familles ciblées ayant augmenté leur revenu grâce à l'aide fournie
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,idTrimestre); 
            nombre+=menageRepository.getTotalFamilleAugmentRevenuParFournie(idExercice,idTrimestre); 
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getFamilleAugmentRevenuParFournie(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
        nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalFamilleAugmentRevenuParFournie(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getFamilleAugmentRevenuParFournie(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestre(trimestre.get(index));
            nombre=menageRepository.getTotalFamilleAugmentRevenuParFournie(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getFamilleAugmentRevenuParFournie(idExercice,pagingSort);
		    
         } 

         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donne1=new HashMap<>();

        List<QuestionMenageDto> list = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list.add(questionMenageDto);
        } 

      
             if (!list.isEmpty()) {
        donne1.put("moyenneTrimestre", sommeMoyene); 
        donne1.put("moyenneTotal",  moyenne);   
        donne1.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donne1.put("PourcentageTotalMoyenne", moyennePourcentage);
        donne1.put("details",list);
         }else{
        donne1.put("moyenneTrimestre", 0.0); 
        donne1.put("moyenneTotal",  0.0);   
        donne1.put("pourcentageTrimestre", 0.0); 
        donne1.put("PourcentageTotalMoyenne", 0.0);
        donne1.put("details",list);  
         }

        dataReturn.put("familleAugmentRevenuParFournie", donne1);

           sommeMoyenePourcentage.clear();
           sommeMoyene.clear(); 
        // 2.	Épargne mensuelle moyenne par femme 
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotal(idExercice,idTrimestre);
            nombre=menageRepository.getTotalEpargneMnsuelleMoyenneParFemme(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getEpargneMnsuelleMoyenneParFemme(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
             for (int index = 0; index < trimestre.size(); index++) {
        nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotal(idExercice,trimestre.get(index));
            nombre=menageRepository.getTotalEpargneMnsuelleMoyenneParFemme(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEpargneMnsuelleMoyenneParFemme(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreEpargneMnsuelleMoyenneParFemme(trimestre.get(index));
            nombre=menageRepository.getTotalEpargneMnsuelleMoyenneParFemme(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getEpargneMnsuelleMoyenneParFemme(idExercice,pagingSort);
		    
         } 
         
         moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee2=new HashMap<>();

        List<QuestionMenageDto> list2 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity2 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity2) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list2.add(questionMenageDto);
        } 

      
        if (!list2.isEmpty()) {
        donnee2.put("moyenneTrimestre", sommeMoyene); 
        donnee2.put("moyenneTotal",  moyenne);   
        donnee2.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee2.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee2.put("details",list2);
         }else{
        donnee2.put("moyenneTrimestre", 0.0); 
        donnee2.put("moyenneTotal",  0.0);   
        donnee2.put("pourcentageTrimestre", 0.0); 
        donnee2.put("PourcentageTotalMoyenne", 0.0);
        donnee2.put("details",list2);  
         }

        dataReturn.put("epargneMnsuelleMoyenneParFemme", donnee2);

        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();
         // 3.	% de familles de personnes handicapées ayant obtenu un prêt
         if (idExercice!=null && idTrimestre!=null) {
             nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotalAVH(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalFemillePrete(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getFemillePrete(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
        nombreTotal=0;
        nombre=0;
             for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalFemillePrete(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getFemillePrete(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreFemillePrete(trimestre.get(index));
            nombre=menageRepository.getTotalFemillePrete(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getFemillePrete(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee3=new HashMap<>();

        List<QuestionMenageDto> list3 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity3 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity3) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list3.add(questionMenageDto);
        } 

      
          if (!list3.isEmpty()) {
        donnee3.put("moyenneTrimestre", sommeMoyene); 
        donnee3.put("moyenneTotal",  moyenne);   
        donnee3.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee3.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee3.put("details",list3);
         }else{
        donnee3.put("moyenneTrimestre", 0.0); 
        donnee3.put("moyenneTotal",  0.0);   
        donnee3.put("pourcentageTrimestre", 0.0); 
        donnee3.put("PourcentageTotalMoyenne", 0.0);
        donnee3.put("details",list3);  
         }

        dataReturn.put("femilleEVHPrete", donnee3);


        sommeMoyenePourcentage.clear();
        sommeMoyene.clear();
         //4.	%  de ménages faisant des choix et prenant des décisions en connaissance de causes
        if (idExercice!=null && idTrimestre!=null) {
          nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,idTrimestre);
            nombre+=menageRepository.getTotalChoixFamilleEnAGR(idExercice,idTrimestre);
            pourcentage=pourcentage(nombre, nombreTotal);
			pg = menageRepository.getChoixFamilleEnAGR(idExercice,idTrimestre,pagingSort);
		    sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre);

         }else   if (idExercice!=null && idTrimestre==null) {
            List<Integer> trimestre=listTrimestre(idExercice);
             for (int index = 0; index < trimestre.size(); index++) {
                nombreTotal=0;
        nombre=0;
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal+=menageRepository.getTotal(idExercice,trimestre.get(index));
            nombre+=menageRepository.getTotalChoixFamilleEnAGR(idExercice,trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getChoixFamilleEnAGR(idExercice,pagingSort);
		    
         }else{
            List<Integer> trimestre=listTrimestre(null);
        nombreTotal=0;
        nombre=0;
            for (int index = 0; index < trimestre.size(); index++) {
        pourcentage=0;
        moyenne=0;
        moyennePourcentage=0;
            nombreTotal=menageRepository.getTotalTrimestreChoixFamilleEnAGR(trimestre.get(index));
            nombre=menageRepository.getTotalChoixFamilleEnAGR(trimestre.get(index));
            pourcentage=pourcentage(nombre, nombreTotal);
            sommeMoyenePourcentage.add(pourcentage);
            sommeMoyene.add(nombre); 
             }
			pg = menageRepository.getChoixFamilleEnAGR(idExercice,pagingSort);
		    
         } 
         
         //moyennePourcentage=moyenne(sommeMoyenePourcentage);
         moyennePourcentage=pourcentage(nombre, nombreTotal);
         moyenne=moyenne(sommeMoyene);

         Map<String,Object> donnee4=new HashMap<>();

        List<QuestionMenageDto> list4 = new ArrayList<>(); 
        List<QuestionMenageEntity> dataEntity4 = pg.getContent();
        for (QuestionMenageEntity questionMenageEntity : dataEntity4) {
            QuestionMenageDto questionMenageDto = QuestionMenageConvert.getInstance().toDto(questionMenageEntity);
            list4.add(questionMenageDto);
        } 

      
          if (!list4.isEmpty()) {
        donnee4.put("moyenneTrimestre", sommeMoyene); 
        donnee4.put("moyenneTotal",  moyenne);   
        donnee4.put("pourcentageTrimestre", sommeMoyenePourcentage); 
        donnee4.put("PourcentageTotalMoyenne", moyennePourcentage);
        donnee4.put("details",list4);
         }else{
        donnee4.put("moyenneTrimestre", 0.0); 
        donnee4.put("moyenneTotal",  0.0);   
        donnee4.put("pourcentageTrimestre", 0.0); 
        donnee4.put("PourcentageTotalMoyenne", 0.0);
        donnee4.put("details",list4);  
         }

        dataReturn.put("choixFamilleEnAGR", donnee4);

    Map<String, Object> dtos = PagingAndSortingHelper.filteredAndSortedResult(pg.getNumber(), pg.getSize(), pg.getTotalPages(),
        dataReturn);

     return dtos; 

    }

   @Override
   public boolean checkMenageIfExistInTrimestre(Integer idMenage, Integer idTrimestre) {
      return menageRepository.checkMenageIfExistInTrimestre(idMenage,idTrimestre);
   }

@Override
public boolean checkQuarter(Integer idMenage, Integer idTrimestre, Long id) { 
    Optional<QuestionMenageEntity> checkdata = menageRepository.getByIdMenageAndIdTrimestre(idMenage,idTrimestre,id);  
		return checkdata.isPresent(); 
}

@Override
public boolean checkMenageIfExistInTrimestre(Integer idMenage, Integer idTrimestre, Long id) {
        return menageRepository.checkMenageIfExistInTrimestre(idMenage,idTrimestre,id);
}
    
}
