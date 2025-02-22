package com.product.api.product.entity;

public class Category {
    private Integer category_id = 0;
    private String category = "";
    private String tag ="";
    private Integer status = 0;

    public Category(Integer category_id, String category, String tag, Integer status){
            this.category_id=category_id;
            this.category=category;
            this.tag=tag;
            this.status=status;
    }

    //metodos get
    public int getId(){
            return this.category_id;
    }
    public String getCategoryName(){
            return this.category;
    }
    public String getTag(){
            return this.tag;
    }
    public int getStatus(){
            return this.status;
    }
    //metodos set
    public void setStatus(Integer status){
            this.status=status;
    }
    
    public void setCategoryId(Integer category_id) {
            this.category_id = category_id;
    }

    public void setCategory(String category) {
            this.category = category;
    }

    public void setTag(String tag) {
            this.tag = tag;
    }
    

    @Override
    public String toString(){
            return "{"+category_id.toString()+","+category+","+tag+","+status+"}";
    } 

}
