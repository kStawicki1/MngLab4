package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.model.ArticleEntity;
import edu.wat.tim.lab1.model.BasketArticleEntity;
import edu.wat.tim.lab1.model.BasketEntity;
import edu.wat.tim.lab1.model.ClientEntity;
import edu.wat.tim.lab1.repository.BasketArticleEntityRepository;
import edu.wat.tim.lab1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleRestController {

    private final SampleService service;

    @PostMapping("/client")
    public ResponseEntity<ClientEntity> createClientEntity(@RequestBody ClientEntity entity) {
        ClientEntity savedEntity = service.createClient(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PostMapping("/basket/{id}/article/{quantity}")
    public ResponseEntity<ArticleEntity> createArticleEntity(@RequestBody ArticleEntity entity,
                                                             @PathVariable(value = "id") Long basketId,
                                                             @PathVariable(name = "quantity") Integer quantity) {
        ArticleEntity savedEntity = service.createArticle(entity, basketId, quantity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping("/article/{name}")
    public  ResponseEntity<List<ArticleEntity>> getArticlesEntitiesByName(@PathVariable(value = "name") String name) {
        List<ArticleEntity> articleEntities = service.getArticlesByName(name);
        return new ResponseEntity<>(articleEntities, HttpStatus.OK);
    }

    @PostMapping("/client/{id}/basket")
    public ResponseEntity<BasketEntity> addChildEntity(@RequestBody BasketEntity entity, @PathVariable(value = "id") Long id) {
        BasketEntity savedEntity = service.addBasket(entity, id);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable(value = "articleId") Long articleId) {
        service.deleteArticle(articleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/basket/{basketId}/product/{productId}")
    public ResponseEntity<BasketArticleEntity> updateEntity(@PathVariable(value = "basketId") Long basketId,
                                                            @PathVariable(value = "productId") Long articleId,
                                                            @RequestParam Integer newQuantity) {
        return new ResponseEntity<>(service.updateArticleEntityInBasket(basketId, articleId, newQuantity), HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        return new ResponseEntity<>(service.getAllClients(),HttpStatus.OK);
    }
}
