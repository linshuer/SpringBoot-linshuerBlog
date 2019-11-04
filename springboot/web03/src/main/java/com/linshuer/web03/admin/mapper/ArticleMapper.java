package com.linshuer.web03.admin.mapper;

import com.linshuer.web03.admin.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ArticleMapper {
    List<Article> findAll();

    List<Article> findById(Long id);

    void create(Article article);

    void delete(Long id);

    void update(Article article);
    //最后一行id
    long getLastId();
}
