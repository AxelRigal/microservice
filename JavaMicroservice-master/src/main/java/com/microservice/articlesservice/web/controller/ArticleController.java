package com.microservice.articlesservice.web.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.microservice.articlesservice.dao.ArticleDao;
import com.microservice.articlesservice.model.Article;
import com.microservice.articlesservice.service.ArticleService;
import com.microservice.articlesservice.web.exceptions.ArticleIntrouvableException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
@Api("API pour les opérations CRUD sur les articles")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private HttpServletRequest requestContext;

    @Autowired
    private ArticleDao articleDao;

    @ApiOperation(value = "Récupère les articles en stocks !" )
    @RequestMapping(value="/Articles", method= RequestMethod.GET)
    public MappingJacksonValue listeArticles() {

        logger.info("Début d'appel au service Articles pour la requête : " + requestContext.getHeader("req-id"));

        List <Article> articles = articleDao.findAll();
        SimpleBeanPropertyFilter monFiltre =SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique",monFiltre );
        MappingJacksonValue articlesFiltres = new MappingJacksonValue(articles);
        articlesFiltres.setFilters( listDeNosFiltres );
        return articlesFiltres;
    }

    @ApiOperation(value = "Récupère un article grâce à son ID à condition que celui-ci soit en stock!" )
    @GetMapping(value="/Articles/{id}")
    public Article afficherUnArticle(@PathVariable int id) {
        Article article = articleDao.findById(id);

        if(article == null) throw new ArticleIntrouvableException("L'article avec l'id " + id + " est INTROUVABLE");
        return article;
    }
    @ApiOperation(value = "Retourne la liste des  articles suivant un prix limite !" )
    @GetMapping (value = "/test/articles/{prixLimit}" )
    public List <Article> testeDeRequetes (@PathVariable int prixLimit) {
        return articleDao .findByPrixGreaterThan(prixLimit);
    }
    @GetMapping (value = "/test/articles/like/{recherche}" )
    public List <Article> testeDeRequetes (@PathVariable String recherche) {
        return articleDao.findByNomLike("%"+recherche+"%");
    }

    @ApiOperation(value = "Crée un article !" )
    @PostMapping(value = "/Articles")
    public ResponseEntity<Void> ajouterArticle (@RequestBody Article article) {
        Article articleAdded = articleDao .save(article);
        if ( articleAdded == null )
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(articleAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @ApiOperation(value = "Supprime un article  !" )
    @DeleteMapping (value = "/Articles/{id}" )
    public void supprimerArticle (@PathVariable int id) {
        articleDao.deleteById(id);
    }
    @ApiOperation(value = "Modifie un article suivant son id !" )
    @PutMapping (value = "/Articles" )
    public void updateArticle (@RequestBody Article article) {
        articleDao.save(article);
    }
    @ApiOperation(value = "Retourne la liste des  articles suivant un prix limite !" )
    @GetMapping (value = "/test/articles/prix/{prixLimit}" )
    public List<Article> chercherUnArticleCherRoute(@PathVariable int prixLimit){
        return articleDao.chercherUnArticleCher(prixLimit);
    }

    @ApiOperation(value = " Calculer la Marge de chaque Article")
    @GetMapping(value="/AdminArticle")
    public HashMap<String, Integer> calculerMargeArticle(){
        List<Article> articles = articleDao.findAll();
        return ArticleService.calculMarge(articles);
    }
    @ApiOperation(value = "  Trier les Articles par ordre alphabétique")
    @GetMapping(value="/TrierArticle")
    public List<Article> trierArticlesParOrdreAlphabetique(){
        return articleDao.findAllByOrderByNomAsc();
    }
}
