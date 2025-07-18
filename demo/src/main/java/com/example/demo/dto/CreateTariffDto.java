package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTariffDto {
    private String name;
    private String type;
    private String conditions;


    public CreateTariffDto(String name, String type, String conditions) {

        this.name = name;
        this.type = type;
        this.conditions = conditions;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getConditions() {
        return conditions;
    }
}