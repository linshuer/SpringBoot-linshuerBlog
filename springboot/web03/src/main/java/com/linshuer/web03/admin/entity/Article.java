package com.linshuer.web03.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private long id; //编号
    @NotNull
    private String title; //标题
    private String titlePic; //封面图片
    private String category; //分类
    private String tags; //标签
    private String author; //作者
    private String content; //内容
    private String state; //状态
    private long eyeCount; //浏览量

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editTime;//最后编辑时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;//发布时间

    public Article() {
    }

    public Article(String category) {
        this.category = category;
    }

    public Article(long id, String category) {
        this.id = id;
        this.category = category;
    }

    public Article(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlePic() {
        return titlePic;
    }

    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getEyeCount() {
        return eyeCount;
    }

    public void setEyeCount(long eyeCount) {
        this.eyeCount = eyeCount;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titlePic='" + titlePic + '\'' +
                ", category='" + category + '\'' +
                ", tags='" + tags + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", eyeCount=" + eyeCount +
                ", editTime=" + editTime +
                ", publishTime=" + publishTime +
                '}';
    }
}
