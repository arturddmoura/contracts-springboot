package com.arturddmoura.springboot.contracts;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ContractRepository {
    private final List<Contract> contracts = new ArrayList<>();

    List<Contract> findAll() {
        return contracts;
    }

    Contract findById(UUID id) {
        return contracts.stream().filter(contract -> contract.id().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Contract not found"));
    }

    void create(Contract contract) {
        contracts.add(contract);
    }

    void update(UUID id, Contract contract) {
        Contract existingContract = findById(id);
        if (existingContract != null) {
            contracts.set(contracts.indexOf(existingContract), contract);
        }
    }

    void delete(UUID id) {
        contracts.removeIf(contract -> contract.id().equals(id));
    }

    @PostConstruct
    private void init() {
        contracts.add(new Contract(UUID.randomUUID(), "SP-1", Areas.sales, Products.software_purchase, LocalDateTime.now()));
        contracts.add(new Contract(UUID.randomUUID(), "SP-2", Areas.sales, Products.software_sale, LocalDateTime.now()));
        contracts.add(new Contract(UUID.randomUUID(), "SP-3", Areas.sales, Products.software_purchase, LocalDateTime.now()));
    }
}
