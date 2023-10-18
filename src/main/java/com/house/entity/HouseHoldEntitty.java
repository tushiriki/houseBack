package com.house.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name = "house")
public class HouseHoldEntitty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11)
    private Integer id;
    @Column(name = "id_exercise", length = 50)
    private Integer idExercise;
    @Column(name = "id_trimestre_constant")
    private Integer idTrimestreConstant;
    @Column(name = "date_debut")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateCreation;
    @Column(name = "questionnaire")
    private Boolean questionnaire;




    @Column(name = "house_hold_status")
    private Integer householdStatus;
    @Column(name = "age_of_head_of_house_hold")
    private Integer   ageOfHeadOfHouseHold;
    @Column(name = "education_level_of_head_of_house_hold")
    private Integer educationLevelOfHeadOfHouseHold;
    @Column(name = "occupation_of_head_of_house_hold")
    private Integer    occupationOfHeadOfHouseHold;
    @Column(name = "zero_to_five_years_number")
    private Integer  zeroToFiveYearsNumber;
    @Column(name = "six_to_twelve_years_number")
    private Integer sixToTwelveYearsNumber;
    @Column(name = "thirteen_to_eighteen_years_number")
    private Integer  thirteenToEighteenYearsNumber;
    @Column(name = "nineghteen_to_thirty_five_years_number")
    private Integer nineghteenToThirtyFiveYearsNumber;
    @Column(name = "thirty_six_to_fifty_two_years_number")
    private Integer thirtySixToFiftyTwoYearsNumber;
    @Column(name = "fifty_three_years_and_more_number")
    private Integer FiftyThreeYearsAndMoreNumber;
    @Column(name = "total_men_in_house_hold")
    private Integer  totalMenInHousehold;
    @Column(name = "total_women_in_house_hold")
    private Integer totalWomenInHousehold;
    @Column(name = "hpv_men")
    private Integer HPVMen;

    @Column(name = "hpv_women")
    private Integer HPVWomen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_exercise", referencedColumnName = "id", insertable = false, updatable = false)
    private ExerciceEntity exercise;

    @Column(name = "head_of_house_hold_name")
    private String headOfHouseholdName;
    @Column(name = "childrenWithDisabilities")
    private Boolean childrenWithDisabilities;
    @Column(name = "numberOfChildrenWithDisabilities")
    private Integer numberOfChildrenWithDisabilities;
	private Integer province;
	private Integer aireDeSante;
	private Integer zoneDeSante;
	private String avenue;
	private Long numeroDeTelephone;
	private Long numeroDeTelephone2;


	private Boolean headOfHouseholdHavDisability;
	private Boolean headOfHouseHoldDisabilityHaveIGA;
	private Integer combienPersonHandicapeEnfant;
	private Integer combienPersonHandicapeJeunesse;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdExercise() {
		return idExercise;
	}
	public void setIdExercise(Integer idExercise) {
		this.idExercise = idExercise;
	}
	public Integer getIdTrimestreConstant() {
		return idTrimestreConstant;
	}
	public void setIdTrimestreConstant(Integer idTrimestreConstant) {
		this.idTrimestreConstant = idTrimestreConstant;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Boolean getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(Boolean questionnaire) {
		this.questionnaire = questionnaire;
	}
	public Integer getHouseholdStatus() {
		return householdStatus;
	}
	public void setHouseholdStatus(Integer householdStatus) {
		this.householdStatus = householdStatus;
	}
	public Integer getAgeOfHeadOfHouseHold() {
		return ageOfHeadOfHouseHold;
	}
	public void setAgeOfHeadOfHouseHold(Integer ageOfHeadOfHouseHold) {
		this.ageOfHeadOfHouseHold = ageOfHeadOfHouseHold;
	}
	public Integer getEducationLevelOfHeadOfHouseHold() {
		return educationLevelOfHeadOfHouseHold;
	}
	public void setEducationLevelOfHeadOfHouseHold(Integer educationLevelOfHeadOfHouseHold) {
		this.educationLevelOfHeadOfHouseHold = educationLevelOfHeadOfHouseHold;
	}
	public Integer getOccupationOfHeadOfHouseHold() {
		return occupationOfHeadOfHouseHold;
	}
	public void setOccupationOfHeadOfHouseHold(Integer occupationOfHeadOfHouseHold) {
		this.occupationOfHeadOfHouseHold = occupationOfHeadOfHouseHold;
	}
	public Integer getZeroToFiveYearsNumber() {
		return zeroToFiveYearsNumber;
	}
	public void setZeroToFiveYearsNumber(Integer zeroToFiveYearsNumber) {
		this.zeroToFiveYearsNumber = zeroToFiveYearsNumber;
	}
	public Integer getSixToTwelveYearsNumber() {
		return sixToTwelveYearsNumber;
	}
	public void setSixToTwelveYearsNumber(Integer sixToTwelveYearsNumber) {
		this.sixToTwelveYearsNumber = sixToTwelveYearsNumber;
	}
	public Integer getThirteenToEighteenYearsNumber() {
		return thirteenToEighteenYearsNumber;
	}
	public void setThirteenToEighteenYearsNumber(Integer thirteenToEighteenYearsNumber) {
		this.thirteenToEighteenYearsNumber = thirteenToEighteenYearsNumber;
	}
	public Integer getNineghteenToThirtyFiveYearsNumber() {
		return nineghteenToThirtyFiveYearsNumber;
	}
	public void setNineghteenToThirtyFiveYearsNumber(Integer nineghteenToThirtyFiveYearsNumber) {
		this.nineghteenToThirtyFiveYearsNumber = nineghteenToThirtyFiveYearsNumber;
	}
	public Integer getThirtySixToFiftyTwoYearsNumber() {
		return thirtySixToFiftyTwoYearsNumber;
	}
	public void setThirtySixToFiftyTwoYearsNumber(Integer thirtySixToFiftyTwoYearsNumber) {
		this.thirtySixToFiftyTwoYearsNumber = thirtySixToFiftyTwoYearsNumber;
	}
	public Integer getFiftyThreeYearsAndMoreNumber() {
		return FiftyThreeYearsAndMoreNumber;
	}
	public void setFiftyThreeYearsAndMoreNumber(Integer fiftyThreeYearsAndMoreNumber) {
		FiftyThreeYearsAndMoreNumber = fiftyThreeYearsAndMoreNumber;
	}
	public Integer getTotalMenInHousehold() {
		return totalMenInHousehold;
	}
	public void setTotalMenInHousehold(Integer totalMenInHousehold) {
		this.totalMenInHousehold = totalMenInHousehold;
	}
	public Integer getTotalWomenInHousehold() {
		return totalWomenInHousehold;
	}
	public void setTotalWomenInHousehold(Integer totalWomenInHousehold) {
		this.totalWomenInHousehold = totalWomenInHousehold;
	}
	public Integer getHPVMen() {
		return HPVMen;
	}
	public void setHPVMen(Integer hPVMen) {
		HPVMen = hPVMen;
	}
	public Integer getHPVWomen() {
		return HPVWomen;
	}
	public void setHPVWomen(Integer hPVWomen) {
		HPVWomen = hPVWomen;
	}
	public ExerciceEntity getExercise() {
		return exercise;
	}
	public void setExercise(ExerciceEntity exercise) {
		this.exercise = exercise;
	}
	public String getHeadOfHouseholdName() {
		return headOfHouseholdName;
	}
	public void setHeadOfHouseholdName(String headOfHouseholdName) {
		this.headOfHouseholdName = headOfHouseholdName;
	}
	public Boolean getChildrenWithDisabilities() {
		return childrenWithDisabilities;
	}
	public void setChildrenWithDisabilities(Boolean childrenWithDisabilities) {
		this.childrenWithDisabilities = childrenWithDisabilities;
	}
	public Integer getNumberOfChildrenWithDisabilities() {
		return numberOfChildrenWithDisabilities;
	}
	public void setNumberOfChildrenWithDisabilities(Integer numberOfChildrenWithDisabilities) {
		this.numberOfChildrenWithDisabilities = numberOfChildrenWithDisabilities;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getAireDeSante() {
		return aireDeSante;
	}

	public void setAireDeSante(Integer aireDeSante) {
		this.aireDeSante = aireDeSante;
	}

	public Integer getZoneDeSante() {
		return zoneDeSante;
	}

	public void setZoneDeSante(Integer zoneDeSante) {
		this.zoneDeSante = zoneDeSante;
	}

	public String getAvenue() {
		return avenue;
	}

	public void setAvenue(String avenue) {
		this.avenue = avenue;
	}

	public Long getNumeroDeTelephone() {
		return numeroDeTelephone;
	}

	public void setNumeroDeTelephone(Long numeroDeTelephone) {
		this.numeroDeTelephone = numeroDeTelephone;
	}

	public Long getNumeroDeTelephone2() {
		return numeroDeTelephone2;
	}

	public void setNumeroDeTelephone2(Long numeroDeTelephone2) {
		this.numeroDeTelephone2 = numeroDeTelephone2;
	}
}
