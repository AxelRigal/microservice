/*package com.microservice.articlesservice.dao;

import com.microservice.articlesservice.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {
    public static List < Article > articles = new ArrayList<>();
    static {
        articles .add( new Article( 1 , new String( "Ordinateur portable" ), 350, 250 ));
        articles .add( new Article( 2 , new String( "Aspirateur Robot" ), 500, 200 ));
        articles .add( new Article( 3 , new String( "Table de Ping Pong" ), 750, 500 ));
    }
    @Override
    public List<Article> findAll() {
       return articles;
    }

    @Override
    public Article findById(int id) {
        for(Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null; //Error ?
    }

    @Override
    public Article save(Article article) {
        articles.add(article);
        return article;
    }
}
*/