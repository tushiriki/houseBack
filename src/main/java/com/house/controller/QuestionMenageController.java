package com.house.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.house.dto.QuestionMenageDto;
import com.house.helper.MessageHelper;
import com.house.helper.ResponseHelper;
import com.house.service.QuestionMenageService;  

@RestController
@RequestMapping("/question_menage")
@CrossOrigin(origins = "*")
public class QuestionMenageController {
        
    @Autowired
    private QuestionMenageService service;

     @GetMapping(value = "/all")
     public ResponseEntity<Object> getAll( 
                                @RequestHeader(name = "Accept-Language", required = false) String localeString,   
                                @RequestParam(required = false) Long id, 
                                 @RequestParam(required = false) Integer idMenage,
                                 @RequestParam(required = false) Integer idTrimestre,
                                 @RequestParam(required = false) Integer idExercice,
                                 @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id,asc") String[] sort) {
       
                if (size == 0)
               {
                size = Integer.MAX_VALUE;
               }   
  
        Map<String, Object> data = service.getAll(id,idMenage,idExercice,idTrimestre,page, size, sort);

        if (data.size() > 0) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent(new Locale(localeString)), false), HttpStatus.NOT_FOUND);
        }

 }

 

 @PostMapping("/")
 public ResponseEntity<Object> createExercice(
         @RequestHeader(name = "Accept-Language", required = false) String localeString,
         @RequestBody QuestionMenageDto questionMenageDto){
  
        if (service.checkQuarter(questionMenageDto.getIdMenage(),questionMenageDto.getIdTrimestre()) ) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.quarterExist(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        }

        if (service.checkMenageIfExistInTrimestre(questionMenageDto.getIdMenage(),questionMenageDto.getIdTrimestre()) ) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.quarterExist(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        } 
        if (questionMenageDto.getIdMenage()==null ) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.chooseHouseOld(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        }  
        if (questionMenageDto.getIdTrimestre()==null ) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.chooseQueter(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        } 
        boolean dataSave= service.createQuestion(questionMenageDto);

        if (dataSave) {
                   return new ResponseEntity<>(new ResponseHelper(MessageHelper.save(new Locale(localeString)), true),HttpStatus.CREATED);
            } else {
                  return new ResponseEntity<>(new ResponseHelper(MessageHelper.echec(new Locale(localeString)), false),HttpStatus.INTERNAL_SERVER_ERROR);
          }
   }

 @PutMapping("/{id}")
 public ResponseEntity<Object> upDate(
         @PathVariable(name = "id", required = true) Long id,
         @RequestHeader(name = "Accept-Language", required = false) String localeString,
        @RequestBody QuestionMenageDto questionMenageDto){ 

       
        if (id==null) { 
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.notFound(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        }  

        if (service.checkQuarter(questionMenageDto.getIdMenage(),questionMenageDto.getIdTrimestre()) && !service.checkQuarter(questionMenageDto.getIdMenage(),questionMenageDto.getIdTrimestre(),id) ) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.quarterExist(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        }  
      
        if (questionMenageDto.getIdMenage()==null ) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.chooseHouseOld(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        }

        if (questionMenageDto.getIdTrimestre()==null ) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.chooseQueter(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        }

        boolean dataSave= service.upDateQuestion(id,questionMenageDto);
    
      if (dataSave) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.update(new Locale(localeString)), true),HttpStatus.CREATED);
     } else {
           return new ResponseEntity<>(new ResponseHelper(MessageHelper.echec(new Locale(localeString)), false),HttpStatus.INTERNAL_SERVER_ERROR);
   }

   }

    @DeleteMapping("/{id}")
   public ResponseEntity<Object> delete( 
    @RequestHeader(name = "Accept-Language", required = false) String localeString,
                   @PathVariable(name = "id", required = true) Long id) {  

            if (id==null) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.message("id not found"), false),HttpStatus.NOT_ACCEPTABLE);
        }
 
                   boolean dataDelete = service.delete(id);
                   if (dataDelete) {
                           return new ResponseEntity<>(
                                           new ResponseHelper(MessageHelper.deleted(new Locale(localeString)), true),
                                           HttpStatus.OK);
                   } else {
                           return new ResponseEntity<>(new ResponseHelper(MessageHelper.deleteFeild(new Locale(localeString)), false),
                                           HttpStatus.INTERNAL_SERVER_ERROR);
                   }
           } 
 
}
