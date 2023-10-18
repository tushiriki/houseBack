package com.house.service;

import com.house.convertDto.EnqueteConvertDto;
import com.house.convertDto.ExerciceConvertDto;
import com.house.dto.EnqueteDto;
import com.house.dto.ExerciceDto;
import com.house.entity.EnqueteEntity;
import com.house.entity.ExerciceEntity;
import com.house.helper.DateHelper;
import com.house.repository.EnqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnqueteService {

@Autowired
private EnqueteRepository repository;

public List<EnqueteDto> getAll() {

List<EnqueteEntity> all = repository.findAll();
List<EnqueteDto> dtos = new ArrayList<>();

try {
for (EnqueteEntity entity : all) {
EnqueteDto dto = EnqueteConvertDto.getInstance().toDto(entity);
dtos.add(dto);

}
} catch (Exception e) {
System.out.println(e.getMessage());
}
return dtos;
}




public EnqueteDto getById(Integer id) {

EnqueteEntity entity = null;
EnqueteDto dto = null;

try {
entity = repository.getById(id);
dto = EnqueteConvertDto.getInstance().toDto(entity);
} catch (Exception e) {
System.out.println(e.getMessage());
dto = null;
}
return dto;
}



public EnqueteDto create(EnqueteDto enqueteDto) {
EnqueteDto dto = null;

try {
EnqueteEntity entity = EnqueteConvertDto.getInstance()
        .toEntity(enqueteDto);
    entity.setDateCreation(DateHelper.now());
    EnqueteEntity fromBd = repository.save(entity);

dto = EnqueteConvertDto.getInstance().toDto(fromBd);

} catch (Exception e) {
System.out.println(e.getMessage());
dto = null;
}
return enqueteDto;
}

public boolean deleteById(Integer id) {
boolean result = false;
try {
EnqueteDto dto = getById(id);
if (dto != null) {
repository.deleteById(id);
result = true;
}
} catch (Exception e) {
System.out.println(e.getMessage());
result = false;
}
return result;

}

public EnqueteDto update(int id, EnqueteDto dto) {
EnqueteEntity entity = null;

try {
entity = repository.getById(id);
dto.setId(entity.getId());
entity = EnqueteConvertDto.getInstance().toEntity(dto);
EnqueteEntity updated = repository.save(entity);
dto = EnqueteConvertDto.getInstance().toDto(updated);
} catch (Exception e) {
dto = null;
System.out.println(e.getMessage());
}
return dto;
}

}
