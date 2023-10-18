package com.house.service;

import com.house.convertDto.HouseHoldConvertDto;
import com.house.dto.HouseHoldDto;
import com.house.entity.ExerciceEntity;
import com.house.entity.HouseHoldEntitty;
import com.house.exception.ResourceNotFoundException;
import com.house.helper.PagingAndSortingHelper;
import com.house.repository.ExerciceRepository;
import com.house.repository.HouseHoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HouseHoldService {
    @Autowired
    private HouseHoldRepository repository;
    @Autowired
    private ExerciceRepository exerciceRepository;
    public HouseHoldDto getById(Integer id) {
        HouseHoldDto dto = null;
        HouseHoldEntitty entity = null;
        try {
            entity = repository.getById(id);
            dto = HouseHoldConvertDto.getInstance().toDto(entity);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return dto;
    }

    public List<HouseHoldEntitty> filterByExerciseId(Integer idExercise) {
        return repository.filterByExerciseId(idExercise);
    }

    public List<HouseHoldDto> getAll(){
        List<HouseHoldEntitty> exerciseEntities = null;
        List<HouseHoldDto> exerciseDtos = new ArrayList<>();

        try {
            exerciseEntities = repository.findAll();

            for (HouseHoldEntitty entity : exerciseEntities){
                HouseHoldDto dto = HouseHoldConvertDto.getInstance().toDto(entity);
                exerciseDtos.add(dto);

            }
        }catch (Exception e){
            exerciseDtos = null;
            System.out.println(e.getMessage());
        }
        return exerciseDtos;
    }

    public Map<String, Object> getAllPagination(String title, int page, int size, String[] sort) {
        Map<String, Object> data = new HashMap<>();
        Pageable pagingSort = PagingAndSortingHelper.pagination(sort, page, size);
        Page<HouseHoldEntitty> entities = null;


        List<HouseHoldDto> dtos = new ArrayList<>();

        try {

            for (HouseHoldEntitty entity : entities) {
                HouseHoldDto dto = HouseHoldConvertDto.getInstance().toDto(entity);

                dtos.add(dto);

            }


        } catch (Exception e) {
            entities = null;
            System.out.println(e.getMessage());
        }
        data = PagingAndSortingHelper.filteredAndSortedResult(entities.getNumber(),
                entities.getTotalElements(), entities.getTotalPages(), dtos);
        return data;
    }
    public HouseHoldDto create1(HouseHoldDto exerciseDto)  {
        HouseHoldEntitty exercise = null;
        HouseHoldDto dto = null;
        try {
            exercise = HouseHoldConvertDto.getInstance().toEntity(exerciseDto);
            HouseHoldEntitty savedExercise = repository.save(exercise);
            dto =HouseHoldConvertDto.getInstance().toDto(savedExercise);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return dto;
    }


    public HouseHoldDto update(Integer id, HouseHoldDto dto){
        HouseHoldEntitty entity = null;
        HouseHoldDto dtoRetour = null;

        try {
            entity = repository.getById(id);
            dto.setId(entity.getId());
            entity = HouseHoldConvertDto.getInstance().toEntity(dto);

            HouseHoldEntitty updated = repository.save(entity);
            dto = HouseHoldConvertDto.getInstance().toDto(updated);
            dtoRetour = dto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            dtoRetour = null;
        }
        return dtoRetour;
    }

    public boolean delete(Integer id) {

        boolean result = false;
        try {
            HouseHoldDto dto = getById(id);

            if (dto != null){
                repository.deleteById(id);
                result = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }

 
}
