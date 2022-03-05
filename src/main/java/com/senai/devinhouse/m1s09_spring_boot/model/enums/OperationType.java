package com.senai.devinhouse.m1s09_spring_boot.model.enums;

public enum OperationType {

    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw"),
    TRANSFER("Transfer");

    private final String operatioTypeDescription;

    OperationType(String operatioTypeDescription) {
        this.operatioTypeDescription = operatioTypeDescription;
    }

    public String getOperatioTypeDescription() {
        return operatioTypeDescription;
    }
}
