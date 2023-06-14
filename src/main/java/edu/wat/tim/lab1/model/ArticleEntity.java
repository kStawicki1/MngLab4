package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="article")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "unit")
    private String unit;

    @OneToMany(mappedBy = "articleEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BasketArticleEntity> basketArticleEntities = new ArrayList<>();
}
