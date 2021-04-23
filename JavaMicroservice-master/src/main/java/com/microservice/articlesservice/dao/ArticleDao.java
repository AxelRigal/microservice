package com.microservice.articlesservice.dao;

import com.microservice.articlesservice.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article, Integer> {
    public List<Article> findAll();

    public Article findById(int id);

    List<Article> findByPrixGreaterThan(int prixLimit);

    List <Article> findByNomLike(String recherche);

    public Article save(Article article);

    public void deleteById(Integer integer);

    @Query("SELECT p FROM Article p WHERE p.prix > :prixLimit")
    public List<Article> chercherUnArticleCher(@Param("prixLimit") int prix);

    public List<Article> findAllByOrderByNomAsc();
}

