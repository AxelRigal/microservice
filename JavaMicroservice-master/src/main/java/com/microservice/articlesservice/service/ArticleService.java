package com.microservice.articlesservice.service;

import com.microservice.articlesservice.model.Article;

import java.util.HashMap;
import java.util.List;

public abstract class ArticleService {
    public static HashMap<String, Integer> calculMarge(List<Article> articles) {
        HashMap<String, Integer> myHash = new HashMap<String, Integer>();
        for (Article article : articles) {
            myHash.put(article.getNom(), article.getPrix() - article.getPrixAchat());
        }
        return myHash;
    }
}
