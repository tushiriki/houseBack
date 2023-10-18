package com.house.service;

import com.house.convertDto.ExerciceConvertDto;
import com.house.dto.ExerciceDto;
import com.house.entity.ExerciceEntity; 
import com.house.repository.ExerciceRepository;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import java.util.*;

@Service
public class ExerciceService {
@Autowired
private ExerciceRepository repository;


//public List<ExerciceEntity> filterByExerciseId(String libelle) {
//return repository.filterByExerciseId(libelle);
//}


public List<ExerciceDto> getAll() {

List<ExerciceEntity> all = repository.findAll();
List<ExerciceDto> dtos = new ArrayList<>();

try {
for (ExerciceEntity entity : all) {
ExerciceDto dto = ExerciceConvertDto.getInstance().toDto(entity);
dtos.add(dto);

}
} catch (Exception e) {
System.out.println(e.getMessage());
}
return dtos;
}




public ExerciceDto getById(Integer id) {

ExerciceEntity entity = null;
ExerciceDto dto = null;

try {
entity = repository.getById(id);
dto = ExerciceConvertDto.getInstance().toDto(entity);
} catch (Exception e) {
System.out.println(e.getMessage());
dto = null;
}
return dto;
}



public ExerciceDto create(ExerciceDto exerciceDto) {
ExerciceDto dto = null;

try {
ExerciceEntity entity = ExerciceConvertDto.getInstance()
        .toEntity(exerciceDto);
//            imoEntity.setDateCreation(DateHelper.now());
ExerciceEntity fromBd = repository.save(entity);

dto = ExerciceConvertDto.getInstance().toDto(fromBd);

} catch (Exception e) {
System.out.println(e.getMessage());
dto = null;
}
return exerciceDto;
}

public boolean deleteById(Integer id)  {
    boolean result = false;
try {
ExerciceDto dto = getById(id);

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

public ExerciceDto update(Integer id, ExerciceDto dto) {
ExerciceEntity entity = null;

try {
entity = repository.findById(id).get();
dto.setId(entity.getId());
entity = ExerciceConvertDto.getInstance().toEntity(dto);
ExerciceEntity updated = repository.save(entity);
dto = ExerciceConvertDto.getInstance().toDto(updated);
} catch (Exception e) {
dto = null;
System.out.println(e.getMessage());
}
return dto;
} 

}
