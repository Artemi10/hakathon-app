package com.itamnesia.bhcrud.repository;

import com.itamnesia.bhcrud.model.user.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, UUID> {

    Optional<Expert> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}
