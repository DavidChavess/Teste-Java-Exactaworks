package com.exactaworks.testejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exactaworks.testejava.model.Spent;

public interface SpentRepository extends JpaRepository<Spent, Long> {

}
