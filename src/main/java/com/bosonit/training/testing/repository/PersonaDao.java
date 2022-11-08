package com.bosonit.training.testing.repository;

import com.bosonit.training.testing.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonaDao extends JpaRepository<Persona, Integer> {
    List<Persona> findByname(String name);
}
