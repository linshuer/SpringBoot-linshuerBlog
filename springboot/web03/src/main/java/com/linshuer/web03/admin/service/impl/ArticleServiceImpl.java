package com.linshuer.web03.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.linshuer.web03.admin.entity.*;

import com.linshuer.web03.admin.mapper.ArticleMapper;
import com.linshuer.web03.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    //这里除了articleMapper，其他都是Service
    @Autowired
    private ArticleMapper articleMapper;

//    @Autowired
//    private CategoryService categoryService;
//
//    @Autowired
//    private TagsService tagsService;
//
//    @Autowired
//    private ArticleCategoryService articleCategoryService;
//
//    @Autowired
//    private ArticleTagsService articleTagsService;

    @Override
    public List<Article> findAll() {
        return null;
    }

    @Override
    public List<Article> findById(Long id) {
        return null;
    }

    @Override
    public void create(Article article) {
        if(article.getState()=="1"){
            //发布
            article.setPublishTime(new Date());
        }
        article.setEditTime(new Date());
        articleMapper.create(article);
        long articleId = articleMapper.getLastId();
//        updateArticleCategoryAndTags(article, articleId);//关联
    }

    @Override
    public void delete(Long... ids) {

    }

    @Override
    public void update(Article article) {

    }

    //关联Category和Tags表
//    public void updateArticleCategoryAndTags(Article article, long articleId){
//        if(article.getCategory()!=null){
//            categoryService.create(new Category(article.getCategory()));//保存到分类表
//            //从分类提出，再保存到文章和分类的关联表
//            Category category = categoryService.findByName(article.getCategory());
//            articleCategoryService.create(new ArticleCategory(articleId,category.getId()));
//
//        }
//        if(article.getTags()!=null){
//            List<String> list = (List) JSONArray.parse(article.getTags());
//            if (list.size()>0){
//                for (String name : list){
//                    tagsService.create(new Tags(name));
//                    Tags tags = tagsService.findByName(name);
//                    if (tags != null){
//                        articleTagsService.create(new ArticleTags(articleId,tags.getId()));
//                    }
//                }
//
//            }
//        }
//
//    }
}
