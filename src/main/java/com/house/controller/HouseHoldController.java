package com.house.controller;

import com.house.dto.ExerciceDto;
import com.house.dto.HouseHoldDto;
import com.house.entity.HouseHoldEntitty;
import com.house.exception.ResourceNotFoundException;
import com.house.helper.MessageHelper;
import com.house.helper.ResponseHelper;
import com.house.repository.HouseHoldRepository;
import com.house.service.HouseHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/houseHold")
public class HouseHoldController {
    @Autowired
    private HouseHoldService houseHoldService;
    @Autowired
    private HouseHoldRepository repository;
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }


    @GetMapping("/getHouseholdsByIdExercice/{idExercise}")
    public ResponseEntity<?> getSortieDeFond(@PathVariable Integer idExercise) {

        List<HouseHoldEntitty> sortieFond = houseHoldService.filterByExerciseId(idExercise);
        if (sortieFond.size() >= 0)
            return new ResponseEntity<>(new ResponseHelper("success", sortieFond, true), HttpStatus.OK);
        else
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent()), HttpStatus.NO_CONTENT);
    }


    @GetMapping("/getHouseholdsByIdExercice")
    public ResponseEntity<Map<String, Object>> getAllHouseHoldPage(
            @RequestParam(required = false) Integer idExercise,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {

                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<HouseHoldEntitty> tutorials = new ArrayList<HouseHoldEntitty>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<HouseHoldEntitty> pageTuts = null;
            if (title == null && idExercise!=null)
                {
                    pageTuts = repository.findByIdExercise(idExercise,pagingSort); 
                }else if (title != null && idExercise!=null) {
                   pageTuts = repository.getByIdExerciseAndTitre(idExercise,title,pagingSort);  
                }else{
                  pageTuts = repository.findByIdExercise(null,pagingSort);   
                }


            tutorials = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put("s", tutorials);
            if ( tutorials == null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }


            response.put("tutorials", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable(name = "id", required = true) int id) {
        HouseHoldDto dto = houseHoldService.getById(id);
        if (dto != null) {
            return new ResponseEntity<>(new ResponseHelper(dto, true), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.notFound(), false), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<HouseHoldDto> createExercise(@RequestBody HouseHoldDto exerciseDTO) {
        return ResponseEntity.ok(houseHoldService.create1(exerciseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseHoldDto> updateExercise(@PathVariable Integer id, @RequestBody HouseHoldDto exerciseDTO) {
        return ResponseEntity.ok(houseHoldService.update(
                id, exerciseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExercise(@RequestHeader(name = "Accept-Language", required = false) String localeString,@PathVariable Integer id) {
         boolean dataDelete=houseHoldService.delete(id); 
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
