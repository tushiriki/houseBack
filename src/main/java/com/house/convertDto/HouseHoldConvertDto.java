package com.house.convertDto;

import com.house.dto.HouseHoldDto;
import com.house.entity.HouseHoldEntitty;
import com.house.helper.DateHelper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class HouseHoldConvertDto {
    private HouseHoldConvertDto() {
    };

    public static HouseHoldConvertDto getInstance() {
        return new HouseHoldConvertDto();
    }

    public HouseHoldEntitty toEntity(HouseHoldDto dto) throws ParseException {
        HouseHoldEntitty entity = new HouseHoldEntitty();
        if (dto.getExercise() != null) {
            entity.setIdExercise(dto.getExercise().getId());
        }
        entity.setId(dto.getId());
        entity.setIdExercise(dto.getIdExercise());
        entity.setQuestionnaire(dto.getQuestionnaire());
        entity.setDateCreation(DateHelper.toDate(dto.getDateCreation()));
        entity.setIdTrimestreConstant(dto.getIdTrimestreConstant());

        entity.setHouseholdStatus(dto.getHouseholdStatus());
        entity.setAgeOfHeadOfHouseHold(dto.getAgeOfHeadOfHouseHold());
        entity.setEducationLevelOfHeadOfHouseHold(dto.getEducationLevelOfHeadOfHouseHold());
        entity.setOccupationOfHeadOfHouseHold(dto.getOccupationOfHeadOfHouseHold());
        entity.setZeroToFiveYearsNumber(dto.getZeroToFiveYearsNumber());
        entity.setSixToTwelveYearsNumber(dto.getSixToTwelveYearsNumber());
        entity.setThirteenToEighteenYearsNumber(dto.getThirteenToEighteenYearsNumber());
        entity.setHeadOfHouseholdName(dto.getHeadOfHouseholdName());
        entity.setNineghteenToThirtyFiveYearsNumber(dto.getNineghteenToThirtyFiveYearsNumber());
        entity.setThirtySixToFiftyTwoYearsNumber(dto.getThirtySixToFiftyTwoYearsNumber());

        entity.setFiftyThreeYearsAndMoreNumber(dto.getFiftyThreeYearsAndMoreNumber());
        entity.setTotalMenInHousehold(dto.getTotalMenInHousehold());
        entity.setTotalWomenInHousehold(dto.getTotalWomenInHousehold());
        entity.setHPVMen(dto.getHPVMen());
        entity.setHPVWomen(dto.getHPVWomen());
        entity.setChildrenWithDisabilities(dto.getChildrenWithDisabilities());
        entity.setNumberOfChildrenWithDisabilities(dto.getNumberOfChildrenWithDisabilities());
        entity.setProvince(dto.getProvince());
        entity.setAireDeSante(dto.getAireDeSante());
        entity.setZoneDeSante(dto.getZoneDeSante());
        entity.setAvenue(dto.getAvenue());
        entity.setNumeroDeTelephone(dto.getNumeroDeTelephone());
        entity.setNumeroDeTelephone2(dto.getNumeroDeTelephone2());

        entity.setHeadOfHouseholdHavDisability(dto.getHeadOfHouseholdHavDisability());
        entity.setHeadOfHouseHoldDisabilityHaveIGA(dto.getHeadOfHouseHoldDisabilityHaveIGA());
        entity.setCombienPersonHandicapeEnfant(dto.getCombienPersonHandicapeEnfant());
        entity.setCombienPersonHandicapeJeunesse(dto.getCombienPersonHandicapeJeunesse());
        return entity;
    }

    public HouseHoldDto toDto(HouseHoldEntitty entity) {
        HouseHoldDto dto = new HouseHoldDto();

        if (entity.getExercise() != null) {
            dto.setExercise(ExerciceConvertDto.getInstance().toDto(entity.getExercise()));
        }
        dto.setId(entity.getId());
        dto.setQuestionnaire(entity.getQuestionnaire());
        dto.setDateCreation(DateHelper.toText(entity.getDateCreation()));
        dto.setIdExercise(entity.getIdExercise());
        dto.setIdTrimestreConstant(entity.getIdTrimestreConstant());

        dto.setNineghteenToThirtyFiveYearsNumber(entity.getNineghteenToThirtyFiveYearsNumber());
        dto.setThirtySixToFiftyTwoYearsNumber(entity.getThirtySixToFiftyTwoYearsNumber());
        dto.setHeadOfHouseholdName(entity.getHeadOfHouseholdName());

        dto.setHouseholdStatus(entity.getHouseholdStatus());
        dto.setAgeOfHeadOfHouseHold(entity.getAgeOfHeadOfHouseHold());
        dto.setEducationLevelOfHeadOfHouseHold(entity.getEducationLevelOfHeadOfHouseHold());
        dto.setOccupationOfHeadOfHouseHold(entity.getOccupationOfHeadOfHouseHold());
        dto.setZeroToFiveYearsNumber(entity.getZeroToFiveYearsNumber());
        dto.setSixToTwelveYearsNumber(entity.getSixToTwelveYearsNumber());
        dto.setThirteenToEighteenYearsNumber(entity.getThirteenToEighteenYearsNumber());
        dto.setFiftyThreeYearsAndMoreNumber(entity.getFiftyThreeYearsAndMoreNumber());
        dto.setTotalMenInHousehold(entity.getTotalMenInHousehold());
        dto.setTotalWomenInHousehold(entity.getTotalWomenInHousehold());
        dto.setHPVMen(entity.getHPVMen());
        dto.setHPVWomen(entity.getHPVWomen());

        dto.setChildrenWithDisabilities(entity.getChildrenWithDisabilities());
        dto.setNumberOfChildrenWithDisabilities(entity.getNumberOfChildrenWithDisabilities());

        dto.setProvince(entity.getProvince());
        dto.setAireDeSante(entity.getAireDeSante());
        dto.setZoneDeSante(entity.getZoneDeSante());
        dto.setAvenue(entity.getAvenue());
        dto.setNumeroDeTelephone(entity.getNumeroDeTelephone());
        dto.setNumeroDeTelephone2(entity.getNumeroDeTelephone2());

        dto.setHeadOfHouseholdHavDisability(entity.getHeadOfHouseholdHavDisability());
        dto.setHeadOfHouseHoldDisabilityHaveIGA(entity.getHeadOfHouseHoldDisabilityHaveIGA());
        dto.setCombienPersonHandicapeEnfant(entity.getCombienPersonHandicapeEnfant());
        dto.setCombienPersonHandicapeJeunesse(entity.getCombienPersonHandicapeJeunesse());
        return dto;
    }
}
