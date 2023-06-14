package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="basket_article")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketArticleEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name="basket_id")
    @JsonIgnore
    private BasketEntity basketEntity;

    @ManyToOne
    @JoinColumn(name="article_id", nullable=false)
    @JsonIgnore
    private ArticleEntity articleEntity;
}
