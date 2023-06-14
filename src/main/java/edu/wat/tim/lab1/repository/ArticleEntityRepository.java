package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Long> {

    public List<ArticleEntity> getByName(String name);
}
