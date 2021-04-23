package com.microservice.commandesservice.dao;

import com.microservice.commandesservice.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeDao extends JpaRepository<Commande, Integer> {
    public List<Commande> findAll();

    public Commande findById(int id);

    public Commande save(Commande commande);

}
