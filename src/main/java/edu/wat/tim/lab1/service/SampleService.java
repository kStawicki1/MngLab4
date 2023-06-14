package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.ArticleEntity;
import edu.wat.tim.lab1.model.BasketArticleEntity;
import edu.wat.tim.lab1.model.BasketEntity;
import edu.wat.tim.lab1.model.ClientEntity;
import edu.wat.tim.lab1.repository.ArticleEntityRepository;
import edu.wat.tim.lab1.repository.BasketArticleEntityRepository;
import edu.wat.tim.lab1.repository.BasketEntityRepository;
import edu.wat.tim.lab1.repository.ClientEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final ClientEntityRepository clientEntityRepository;
    private final BasketEntityRepository basketEntityRepository;
    private final ArticleEntityRepository articleEntityRepository;
    private final BasketArticleEntityRepository basketArticleEntityRepository;
    public ClientEntity createClient(ClientEntity entity) {
          return clientEntityRepository.save(entity);
    }

    public BasketEntity addBasket(BasketEntity basketEntity, Long parentId) {
        ClientEntity clientEntity = clientEntityRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + parentId));
        basketEntity.setClientEntity(clientEntity);
        return basketEntityRepository.save(basketEntity);
    }

    public ArticleEntity createArticle(ArticleEntity article, Long basketId, int quantity) {
        BasketEntity basketEntity = basketEntityRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + basketId));
        ArticleEntity articleEntity = articleEntityRepository.save(article);
        BasketArticleEntity basketArticleEntity = new BasketArticleEntity();
        basketArticleEntity.setBasketEntity(basketEntity);
        basketArticleEntity.setArticleEntity(articleEntity);
        basketArticleEntity.setQuantity(quantity);
        basketArticleEntityRepository.save(basketArticleEntity);
        return articleEntity;
    }

    public List<ArticleEntity> getArticlesByName(String name) {
        return articleEntityRepository.getByName(name);
    }

    public void deleteArticle(Long articleId) {
        ArticleEntity entity = articleEntityRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + articleId));
        basketArticleEntityRepository.deleteByArticleEntityId(articleId);
        articleEntityRepository.deleteById(articleId);
    }

    public BasketArticleEntity updateArticleEntityInBasket(Long basketId, Long articleId, Integer newQuantity) {
       ArticleEntity articleEntity = articleEntityRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + articleId));
        BasketEntity basketEntity = basketEntityRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + basketId));
       BasketArticleEntity basketArticleEntity = basketArticleEntityRepository.findByArticleEntityIdAndBasketEntityId(articleId, basketId);
       if (newQuantity < 1) {
           throw new RuntimeException(("Podano zla wartosc produktu:") + newQuantity);
       }
       basketArticleEntity.setQuantity(newQuantity);
       return basketArticleEntityRepository.save(basketArticleEntity);
    }

    public List<ClientEntity> getAllClients() {
        return clientEntityRepository.findAll();
    }
}
