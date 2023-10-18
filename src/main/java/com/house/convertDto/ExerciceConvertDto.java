package com.house.convertDto;

import com.house.dto.ExerciceDto;
import com.house.entity.ExerciceEntity;
import com.house.helper.DateHelper;

import java.text.ParseException;

public class ExerciceConvertDto {
    private ExerciceConvertDto(){};

    public static ExerciceConvertDto getInstance(){
        return new ExerciceConvertDto();
    }

    public ExerciceEntity toEntity(ExerciceDto dto) throws ParseException {
        ExerciceEntity entity = new ExerciceEntity();
        entity.setId(dto.getId());

        entity.setLibelle(dto.getLibelle());
        entity.setStartDate(DateHelper.toDate(dto.getStartDate()));
        entity.setEndDate(DateHelper.toDate(dto.getEndDate()));

        return entity;
    }

    public ExerciceDto toDto(ExerciceEntity entity){
        ExerciceDto dto = new ExerciceDto();
        dto.setId(entity.getId());

        dto.setLibelle(entity.getLibelle());
        dto.setStartDate(DateHelper.toText(entity.getStartDate()));
        dto.setEndDate(DateHelper.toText(entity.getEndDate()));

        return dto;
    }
}
