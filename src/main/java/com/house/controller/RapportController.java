package com.house.controller;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.house.helper.MessageHelper;
import com.house.helper.ResponseHelper;
import com.house.service.QuestionMenageService;

@RestController
@RequestMapping("/rapport")
@CrossOrigin(origins = "*")
public class RapportController {

    @Autowired
    private QuestionMenageService questionMenageService;

     @GetMapping(value = "/amelioration_bien_etre/")
     public ResponseEntity<Object> getAmeliorationBienEtre( 
                                @RequestHeader(name = "Accept-Language", required = false) String localeString,  
                                 @RequestParam(required = false) Integer idTrimestre,
                                 @RequestParam(required = false) Integer idExercice,
                                 @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size,
                                 @RequestParam(defaultValue = "id,asc") String[] sort) {
       
                if (size == 0)
               {
                size = Integer.MAX_VALUE;
               }   
  
        Map<String, Object> data = questionMenageService.getAmeliorationBienEtre(idExercice,idTrimestre,page, size, sort);

        if (data.size() > 0) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), false), HttpStatus.NOT_FOUND);
        }

 }


 @GetMapping(value = "/amelioration_niveau_vie/")
     public ResponseEntity<Object> getAmeliorationNiveauVie( 
                                @RequestHeader(name = "Accept-Language", required = false) String localeString,  
                                 @RequestParam(required = false) Integer idTrimestre,
                                 @RequestParam(required = false) Integer idExercice,
                                 @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size,
                                 @RequestParam(defaultValue = "id,asc") String[] sort) {
       
                if (size == 0)
               {
                size = Integer.MAX_VALUE;
               }   
  
        Map<String, Object> data = questionMenageService.getAmeliorationNiveauVie(idExercice,idTrimestre,page, size, sort);

        if (data.size() > 0) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), false), HttpStatus.NOT_FOUND);
        }

 }


 @GetMapping(value = "/amelioration_competences/")
     public ResponseEntity<Object> getAmeliorationCompetences( 
                                @RequestHeader(name = "Accept-Language", required = false) String localeString,  
                                 @RequestParam(required = false) Integer idTrimestre,
                                 @RequestParam(required = false) Integer idExercice,
                                 @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size,
                                 @RequestParam(defaultValue = "id,asc") String[] sort) {
       
                if (size == 0)
               {
                size = Integer.MAX_VALUE;
               }   
  
        Map<String, Object> data = questionMenageService.getAmeliorationCompetences(idExercice,idTrimestre,page, size, sort);

        if (data.size() > 0) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), false), HttpStatus.NOT_FOUND);
        }
    }

@GetMapping(value = "/amelioration_capital_social/")
     public ResponseEntity<Object> getAmeliorationCapitalSocial( 
                                @RequestHeader(name = "Accept-Language", required = false) String localeString,  
                                 @RequestParam(required = false) Integer idTrimestre,
                                 @RequestParam(required = false) Integer idExercice,
                                 @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size,
                                 @RequestParam(defaultValue = "id,asc") String[] sort) {
       //
                if (size == 0)
               {
                size = Integer.MAX_VALUE;
               }   
  
        Map<String, Object> data = questionMenageService.getAmeliorationCapitalSocial(idExercice,idTrimestre,page, size, sort);

        if (data.size() > 0) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), false), HttpStatus.NOT_FOUND);
        }
    }


    
@GetMapping(value = "/revenus_actifs_productifs/")
     public ResponseEntity<Object> getRevenusActifsProductifs( 
                                @RequestHeader(name = "Accept-Language", required = false) String localeString,  
                                 @RequestParam(required = false) Integer idTrimestre,
                                 @RequestParam(required = false) Integer idExercice,
                                 @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size,
                                 @RequestParam(defaultValue = "id,asc") String[] sort) {
       
                if (size == 0)
               {
                size = Integer.MAX_VALUE;
               }   
  
        Map<String, Object> data = questionMenageService.getRevenusActifsProductifs(idExercice,idTrimestre,page, size, sort);

        if (data.size() > 0) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), false), HttpStatus.NOT_FOUND);
        }
    }


}
