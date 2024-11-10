package com.arturddmoura.springboot.contracts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    private final ContractRepository contractRepository;

    public ContractController(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @GetMapping("")
    List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Contract> findById(@PathVariable UUID id) {
        try {
            Contract contract = contractRepository.findById(id);
            return new ResponseEntity<>(contract, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody Contract contract) {
        contractRepository.create(contract);
    }

    @PutMapping("/{id}")
    ResponseEntity<Contract> update(@PathVariable UUID id, @RequestBody Contract contract) {
        try {
            contractRepository.update(id, contract);
            return new ResponseEntity<>(contract, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            contractRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
