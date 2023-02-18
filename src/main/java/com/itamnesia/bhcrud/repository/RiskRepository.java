package com.itamnesia.bhcrud.repository;

import com.itamnesia.bhcrud.model.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RiskRepository extends JpaRepository<Risk, UUID> {
}
