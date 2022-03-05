package com.senai.devinhouse.m1s09_spring_boot.controller.forms;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewTransferForm {

    @NotNull(message = "Origin Account is required")
    private Integer originAccountId;

    @NotNull(message = "Destiny Account is required")
    private Integer destinyAccountId;

    @NotNull(message = "Value is required")
    private Double value;

    public NewTransferForm() {
    }

    public NewTransferForm(Integer originAccountId, Integer destinyAccountId, Double value){
        this.originAccountId = originAccountId;
        this.destinyAccountId = destinyAccountId;
        this.value = value;
    }

}
