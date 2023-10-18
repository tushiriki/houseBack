package com.house.controller;

import com.house.convertDto.EnqueteConvertDto;
import com.house.dto.EnqueteDto;
import com.house.entity.EnqueteEntity;
import com.house.helper.MessageHelper;
import com.house.helper.ResponseHelper;
import com.house.repository.EnqueteRepository;
import com.house.service.EnqueteService;
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
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/enquete")
public class EnqueteController {
@Autowired
private EnqueteService enqueteService;
@Autowired
private EnqueteRepository repository;

private Sort.Direction getSortDirection(String direction) {
if (direction.equals("asc")) {
return Sort.Direction.ASC;
} else if (direction.equals("desc")) {
return Sort.Direction.DESC;
}

return Sort.Direction.ASC;
}
@RequestMapping(value = "/getAll/a", method = RequestMethod.GET)
public ResponseEntity<?> getAll() {

List<EnqueteDto> dtos = enqueteService.getAll();
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

List<EnqueteEntity> enqueteEntities = new ArrayList<>();
Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

Page<EnqueteEntity> entityPage = null;
    List<EnqueteDto> dtos = new ArrayList<>();
if (title == null)
entityPage = repository.findAll(pagingSort);

    for (EnqueteEntity entity: entityPage){
        EnqueteDto dto = EnqueteConvertDto.getInstance().toDto(entity);
        dtos.add(dto);
    }


Map<String, Object> response = new HashMap<>();
response.put("dtos", dtos);
response.put("currentPage", entityPage.getNumber());
response.put("totalItems", entityPage.getTotalElements());
response.put("totalPages", entityPage.getTotalPages());

return new ResponseEntity<>(response, HttpStatus.OK);
} catch (Exception e) {
return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
EnqueteDto dto = enqueteService.getById(id);

if (dto != null) {
return new ResponseEntity<>(new ResponseHelper(null, dto, true), HttpStatus.OK);
}
return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent()), HttpStatus.NO_CONTENT);
}

@PostMapping("/")
public ResponseEntity<?> create(@RequestBody EnqueteDto dto) {
EnqueteDto enqueteDto = enqueteService.create(dto);

if (enqueteDto != null) {
return new ResponseEntity<>(new ResponseHelper(null, enqueteDto, true), HttpStatus.OK);
}
return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent()), HttpStatus.NO_CONTENT);
}

@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
public ResponseEntity<?> update(@RequestBody EnqueteDto dto,
                                @PathVariable(name = "id", required = true) Integer id) {
EnqueteDto update = enqueteService.update(id, dto);
if (update != null) {
return new ResponseEntity<>(new ResponseHelper(null, update, true), HttpStatus.OK);
}
return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent()), HttpStatus.OK);
}

@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {
boolean dto = enqueteService.deleteById(id);

if (dto) {
return new ResponseEntity<>(new ResponseHelper(null, dto, true), HttpStatus.OK);
}
return new ResponseEntity<>(new ResponseHelper(MessageHelper.noContent()), HttpStatus.NO_CONTENT);
}



}
