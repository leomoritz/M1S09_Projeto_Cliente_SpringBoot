package com.senai.devinhouse.m1s09_spring_boot.controller.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
public class NewOperationForm {

    @NotNull(message = "Value is required")
    private Double value;

    public NewOperationForm() {
    }

    public NewOperationForm(Double value){
        this.value = value;
    }

}
