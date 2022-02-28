package com.senai.devinhouse.m1s09_spring_boot.model;

import com.senai.devinhouse.m1s09_spring_boot.utils.UtilGeradorId;
import lombok.*;

import java.util.Objects;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer implements Comparable<Customer>{

    private Integer id = UtilGeradorId.generateSequenceIdCustomer();

    @NonNull //seta o parâmetro como requerido para o construtor (lombok)
    private String name;

    @NonNull //seta o parâmetro como requerido para o construtor (lombok)
    private String cpf;

    @Override
    public String toString() {
        return "id: " + id + " | " +
               "name: " + name + " | " +
               "cpf: " + cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && cpf.equals(customer.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

    @Override
    public int compareTo(Customer o) {
        return this.id.compareTo(o.getId()) + this.cpf.compareTo(o.getCpf());
    }
}
