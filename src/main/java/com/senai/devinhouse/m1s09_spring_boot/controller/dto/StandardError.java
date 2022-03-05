package com.senai.devinhouse.m1s09_spring_boot.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError {

    private String classMethod;
    private String error;
    private String message;
    private String path;

}
