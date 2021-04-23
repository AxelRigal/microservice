package com.microservice.commandesservice.web.controller;


import com.microservice.commandesservice.dao.CommandeDao;
import com.microservice.commandesservice.model.Commande;
import com.microservice.commandesservice.web.exceptions.CommandeIntrouvableException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@Api("API pour les opérations CRUD sur les commandes")
public class CommandeController {
    private static final Logger logger = LoggerFactory.getLogger(CommandeController.class);

    @Autowired
    private HttpServletRequest requestContext;

    @Autowired
    private CommandeDao commandeDao;

    @ApiOperation(value = "Récupère toutes les commandes")
    @RequestMapping(value="/Commandes", method= RequestMethod.GET)
    public List<Commande> listeCommandes(){
        logger.info("Début d'appel à la route get all commandes "+ requestContext.getHeader("req-id"));
        List<Commande> commandes = commandeDao.findAll();
        return commandes;
    }

    @ApiOperation(value = "Passer une commande")
    @PostMapping(value="/Commandes")
    public ResponseEntity<Void> ajouterCommande (@RequestBody Commande commande){
        // -----  Créer un suivi de commande ------
        Commande commandeAdded = commandeDao.save(commande);
        if(commandeAdded == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commandeAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @ApiOperation(value = "Récupère un commande grâce à son ID !" )
    @GetMapping(value="/Commandes/{id}")
    public Commande afficherUneCommande(@PathVariable int id) {
        Commande commande = commandeDao.findById(id);

        if(commande == null) throw new CommandeIntrouvableException("La commande avec l'id " + id + " est INTROUVABLE");
        return commande;
    }
}
