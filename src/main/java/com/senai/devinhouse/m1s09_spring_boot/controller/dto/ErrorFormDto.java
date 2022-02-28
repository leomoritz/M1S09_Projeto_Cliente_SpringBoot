package com.senai.devinhouse.m1s09_spring_boot.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorFormDto {

    private final String field;
    private final String error;

}
