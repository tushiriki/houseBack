package com.house.controller;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.house.dto.HistoriqueDto; 
import com.house.helper.MessageHelper;
import com.house.helper.ResponseHelper;
import com.house.service.HistoriqueService; 


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/historique")
public class HistoriqueController {

    @Autowired
    private HistoriqueService service;

     @GetMapping(value = "/")
     public ResponseEntity<Object> getHistorique( 
                                @RequestHeader(name = "Accept-Language", required = false) String localeString,  
                                 @RequestParam(required = false) Integer idTrimestre,
                                 @RequestParam(required = false) Integer idExercice,
                                 @RequestParam(required = false) Integer idMenage,
                                  @RequestParam(required = false) Integer idUSer,
                                 @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id,asc") String[] sort) {
       
                if (size == 0)
               {
                size = Integer.MAX_VALUE;
               }   
  
        Map<String, Object> data = service.getHistorique(idMenage,idUSer,idExercice,idTrimestre,page, size, sort);

        if (data.size() > 0) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent(new Locale(localeString)), false), HttpStatus.NOT_FOUND);
        }

 } 

   @GetMapping(value = "/a")
     public ResponseEntity<Object> getHistorique(@RequestHeader(name = "Accept-Language", required = false) String localeString) {
        
  
        List<HistoriqueDto> data = service.getAlls();

        if (!data.isEmpty()) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent(new Locale(localeString)), false), HttpStatus.NOT_FOUND);
        }

 } 


   @DeleteMapping("/{id}")
   public ResponseEntity<Object> delete( 
    @RequestHeader(name = "Accept-Language", required = false) String localeString,
                   @PathVariable(name = "id", required = true) Long id) {  

            if (id==null) {
        return new ResponseEntity<>(
        new ResponseHelper(MessageHelper.notFound(new Locale(localeString)), false),HttpStatus.NOT_ACCEPTABLE);
        }
 
                   boolean dataDelete = service.delete(id);
                   if (dataDelete) {
                           return new ResponseEntity<>(
                                           new ResponseHelper(MessageHelper.deleted(new Locale(localeString)), true),
                                           HttpStatus.OK);
                   } else {
                           return new ResponseEntity<>(new ResponseHelper(MessageHelper.echec(new Locale(localeString)), false),
                                           HttpStatus.INTERNAL_SERVER_ERROR);
                   }
           } 
 
    @DeleteMapping("/delete/all/")
   public ResponseEntity<Object> deleteAll( 
    @RequestHeader(name = "Accept-Language", required = false) String localeString) {  
                   
        boolean dataDelete = service.deleteAll();
                   if (dataDelete) {
                           return new ResponseEntity<>(
                                           new ResponseHelper(MessageHelper.message("Deleted successful"), true),
                                           HttpStatus.OK);
                   } else {
                           return new ResponseEntity<>(new ResponseHelper(MessageHelper.message("Delete failed because this item is linked to other data"), false),
                                           HttpStatus.INTERNAL_SERVER_ERROR);
                   }
           } 

}
