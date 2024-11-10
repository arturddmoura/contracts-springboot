package com.arturddmoura.springboot.contracts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ContractRepository {

    private static final Logger log = LoggerFactory.getLogger(ContractRepository.class);
    private final JdbcClient jdbcClient;

    public ContractRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Contract> findAll() {
        return jdbcClient.sql("SELECT * FROM contracts")
                .query(Contract.class)
                .list();
    }

    public Optional<Contract> findById(UUID id) {
        return jdbcClient.sql("SELECT * FROM contracts WHERE \"id\" = :id")
                .param("id", id)
                .query(Contract.class)
                .optional();
    }

    public void create(Contract contract) {
        var created = jdbcClient.sql("INSERT INTO contracts (\"id\", \"status\", \"key\", \"area\", \"product\", \"contract_date\", \"created_by\") VALUES (:id, :status, :key, :area, :product, :contract_date, :created_by)").param("id", contract.id())
                .param("status", contract.status().name())
                .param("key", contract.key())
                .param("area", contract.area().name())
                .param("product", contract.product().name())
                .param("contract_date", contract.contractDate())
                .param("created_by", contract.createdBy())
                .update();

        Assert.state(created == 1, "Contract not created" + contract.key());
    }

    public void update(UUID id, Contract contract) {
        var updated = jdbcClient.sql("UPDATE contracts SET \"status\" = :status, \"key\" = :key, \"area\" = :area, \"product\" = :product, \"contract_date\" = :contract_date, \"created_by\" = :created_by WHERE \"id\" = :id")
                .param("id", id)
                .param("status", contract.status().name())
                .param("key", contract.key())
                .param("area", contract.area().name())
                .param("product", contract.product().name())
                .param("contract_date", contract.contractDate())
                .param("created_by", contract.createdBy())
                .update();

        Assert.state(updated == 1, "Contract not updated" + contract.key());
    }

    public void delete(UUID id) {
        var deleted = jdbcClient.sql("DELETE FROM contracts WHERE \"id\" = :id")
                .param("id", id)
                .update();

        Assert.state(deleted == 1, "Contract not deleted" + id);
    }
}
