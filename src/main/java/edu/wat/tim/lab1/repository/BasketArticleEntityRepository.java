package edu.wat.tim.lab1.repository;


import edu.wat.tim.lab1.model.BasketArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BasketArticleEntityRepository extends JpaRepository<BasketArticleEntity, Long> {

    @Transactional
    public void deleteByArticleEntityId(Long articleId);
    public BasketArticleEntity findByArticleEntityIdAndBasketEntityId(Long articleId, Long basketId);
}
