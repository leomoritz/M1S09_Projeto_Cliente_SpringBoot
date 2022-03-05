package com.senai.devinhouse.m1s09_spring_boot.controller.dto;

import com.senai.devinhouse.m1s09_spring_boot.model.account.AccountOperations;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountOperationDTO {

    private Double value;
    private String instant;
    private Integer accountId;
    private String holder;
    private String operationType;

    public AccountOperationDTO(AccountOperations operation){
        this.value = operation.getValue();
        this.instant = String.valueOf(operation.getInstant());
        this.accountId = operation.getAccount().getId();
        this.holder = operation.getAccount().getCustomer().getName();
        this.operationType = operation.getOperationType().getOperatioTypeDescription();
    }

}
