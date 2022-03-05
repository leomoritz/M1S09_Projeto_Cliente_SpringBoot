package com.senai.devinhouse.m1s09_spring_boot.model.account;

import com.senai.devinhouse.m1s09_spring_boot.model.account.Account;
import com.senai.devinhouse.m1s09_spring_boot.model.enums.OperationType;
import lombok.*;
import java.time.Instant;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class AccountOperations {

    @NonNull
    private Double value;

    private final Instant instant = Instant.now();

    @NonNull
    private Account account;

    @NonNull
    private OperationType operationType;

}
