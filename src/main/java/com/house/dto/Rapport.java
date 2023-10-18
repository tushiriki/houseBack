package com.house.dto;

import lombok.Data;

@Data
public class Rapport {
    private Integer quarter;
    private String question;
    private Object responses;
}
