package com.senai.devinhouse.m1s09_spring_boot.controller;

import com.senai.devinhouse.m1s09_spring_boot.controller.dto.AccountDTO;
import com.senai.devinhouse.m1s09_spring_boot.controller.dto.AccountOperationDTO;
import com.senai.devinhouse.m1s09_spring_boot.controller.dto.AccountTransferDTO;
import com.senai.devinhouse.m1s09_spring_boot.controller.forms.NewOperationForm;
import com.senai.devinhouse.m1s09_spring_boot.controller.forms.NewTransferForm;
import com.senai.devinhouse.m1s09_spring_boot.model.account.Account;
import com.senai.devinhouse.m1s09_spring_boot.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    /*CRUD*/
    @GetMapping("/getAccountList")
    public ResponseEntity<List<AccountDTO>> getAccountList() {
        List<AccountDTO> accountDTOS = service.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(accountDTOS);
    }

    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Integer id) {
        AccountDTO accountDTO = new AccountDTO(service.findById(id));
        return ResponseEntity.ok().body(accountDTO);
    }

    @GetMapping("/getAccountOperations/{id}")
    public ResponseEntity<Set<AccountOperationDTO>> getAccountOperations(@PathVariable Integer id) {
        Set<AccountOperationDTO> accountOperationDTOS = service.getAccountOperations(id).stream().map(AccountOperationDTO::new).collect(Collectors.toSet());
        return ResponseEntity.ok().body(accountOperationDTOS);
    }

    @PostMapping("/registerAccount")
    public ResponseEntity<String> registerAccount(@RequestBody @Valid Account account, UriComponentsBuilder uriBuilder) {
        AccountDTO newAccountDTO = new AccountDTO(service.create(account));
        URI uri = uriBuilder.path("/registerAccount").buildAndExpand(newAccountDTO).toUri();
        return ResponseEntity.created(uri).body("\"id\":" + "\"" + newAccountDTO.getId() + "\"");
    }

    @PutMapping("/updateAccountById/{id}")
    public ResponseEntity<Boolean> updateAccountById(@PathVariable Integer id, @RequestBody Account account) {
        return ResponseEntity.ok().body(service.update(id, account));
    }

    @DeleteMapping("/deleteAccountById/{id}")
    public ResponseEntity<Boolean> deleteAccountById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.delete(id));
    }

    /*OTHER METHODS*/
    @PostMapping("/withdraw/{id}")
    public ResponseEntity<AccountOperationDTO> withdraw(@PathVariable Integer id, @Valid @RequestBody NewOperationForm newWithdraw) {
        AccountOperationDTO accountOperationDTO = service.withdraw(id, newWithdraw.getValue());
        return ResponseEntity.ok().body(accountOperationDTO);
    }

    @PutMapping(value = "/deposit/{id}")
    public ResponseEntity<AccountOperationDTO> deposit(@PathVariable Integer id, @Valid @RequestBody NewOperationForm newDeposit) {
        AccountOperationDTO accountOperationDTO = service.deposit(id, newDeposit.getValue());
        return ResponseEntity.ok().body(accountOperationDTO);
    }

    @PutMapping("/transferValueToOtherAccount")
    public ResponseEntity<AccountTransferDTO> transferValueToOtherAccount(@Valid @RequestBody NewTransferForm newTransfer) {
        AccountTransferDTO accountTransferDTO = service.transferValueToOtherAccount(newTransfer);
        return ResponseEntity.ok().body(accountTransferDTO);
    }

}
