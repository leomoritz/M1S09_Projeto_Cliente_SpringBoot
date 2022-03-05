package com.senai.devinhouse.m1s09_spring_boot.controller.dto;

import com.senai.devinhouse.m1s09_spring_boot.model.account.Account;
import com.senai.devinhouse.m1s09_spring_boot.model.account.AccountOperations;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTransferDTO {

    private Double value;
    private String instant;
    private Integer originAccountId;
    private Integer destinyAccountId;
    private String holderOriginAccount;
    private String holderDestinyAccount;
    private String operationType;

    public AccountTransferDTO(AccountOperations operation, Account destinyAccount){
        this.value = operation.getValue();
        this.instant = String.valueOf(operation.getInstant());
        this.originAccountId = operation.getAccount().getId();
        this.holderOriginAccount = operation.getAccount().getCustomer().getName();
        this.destinyAccountId = destinyAccount.getId();
        this.holderDestinyAccount = destinyAccount.getCustomer().getName();
        this.operationType = operation.getOperationType().getOperatioTypeDescription();
    }

}
