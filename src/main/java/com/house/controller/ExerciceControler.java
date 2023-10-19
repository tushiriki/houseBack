package com.house.controller;

import com.house.dto.ExerciceDto;
import com.house.entity.ExerciceEntity; 
import com.house.helper.MessageHelper;
import com.house.helper.ResponseHelper;
import com.house.repository.ExerciceRepository;
import com.house.service.ExerciceService;
import com.house.service.HouseHoldService;
import com.house.service.NewUSers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/exercice")
public class ExerciceControler {
    @Autowired
    private ExerciceService exerciceService;

    @Autowired
    private HouseHoldService houseHoldService;
    @Autowired
    private NewUSers uSers;
    
    @Autowired
    private ExerciceRepository repository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }


//    @GetMapping("/getExerciceByLibelle/{libelle}")
//    public ResponseEntity<List<ExerciceEntity>> filterByExerciseId(@PathVariable String libelle) {
//        List<ExerciceEntity> houseHolds = exerciceService.filterByExerciseId(libelle);
//        if (houseHolds.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(houseHolds);
//    }

    @RequestMapping(value = "/getAll/a", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestHeader(name = "Accept-Language", required = false) String localeString) {
        boolean admExist=uSers.verifyUserExist();
        if (!admExist) {
           uSers.createFestAdmin();
        }
        List<ExerciceDto> dtos = exerciceService.getAll();
        if (dtos != null) {
            return new ResponseEntity<>(new ResponseHelper(null, dtos, true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent()), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllWithPagination(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {
                if (size == 0)
                size = Integer.MAX_VALUE;
        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {

                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<ExerciceEntity> exerciceEntities = new ArrayList<ExerciceEntity>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<ExerciceEntity> pageTuts = null;
            if (title == null)
            {
                    pageTuts = repository.findAll(pagingSort);
            }else{
                    pageTuts = repository.getByDesignation(title,pagingSort);
            }


            exerciceEntities = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("exercices", exerciceEntities);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        ExerciceDto dto = exerciceService.getById(id);

        if (dto != null) {
            return new ResponseEntity<>(new ResponseHelper(null, dto, true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent()), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestHeader(name = "Accept-Language", required = false) String localeString,@RequestBody ExerciceDto dto) {

         if (repository.existsByLibelle(dto.getLibelle()))
          {
              return new ResponseEntity<>(new ResponseHelper(MessageHelper.dataExist("libelle"), false),
                    HttpStatus.BAD_REQUEST);
          }
 
        ExerciceDto exerciceDto = exerciceService.create(dto);

        if (exerciceDto != null) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), exerciceDto, true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseHelper(MessageHelper.echec(new Locale(localeString)),false), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestHeader(name = "Accept-Language", required = false) String localeString,@RequestBody ExerciceDto dto,
                                    @PathVariable(name = "id", required = true) Integer id) {
       if (repository.existsByLibelle(dto.getLibelle(),id))
          {
              return new ResponseEntity<>(new ResponseHelper(MessageHelper.dataExist("libelle"), false),
                    HttpStatus.BAD_REQUEST);
          } 
        ExerciceDto exerciceDto = exerciceService.update(id, dto);
        if (exerciceDto != null) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.update(new Locale(localeString)), exerciceDto, true), HttpStatus.OK);
        }else
        return new ResponseEntity<>(new ResponseHelper(MessageHelper.echec(new Locale(localeString)),false), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@RequestHeader(name = "Accept-Language", required = false) String localeString,@PathVariable(name = "id") Integer id) {
        if (houseHoldService.checkExercice(id)) {
            return new ResponseEntity<>(
            new ResponseHelper(MessageHelper.deleteFeild(new Locale(localeString))),HttpStatus.NOT_ACCEPTABLE);
            }  

        boolean dto = exerciceService.deleteById(id);

        if (dto) {
            return new ResponseEntity<>(new ResponseHelper(MessageHelper.deleted(new Locale(localeString)), dto, true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseHelper(MessageHelper.deleteFeild(new Locale(localeString))), HttpStatus.INTERNAL_SERVER_ERROR);
    }


   
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<?> deleteAll() {
        boolean dto = uSers.deleteAll();

        if (dto) {
            return new ResponseEntity<>(new ResponseHelper(null, dto, true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent()), HttpStatus.NO_CONTENT);
    } 


}
