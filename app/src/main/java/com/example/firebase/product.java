package com.example.firebase;

public class product
{
    public String prod_name;
    public String prod_price;
    public String category;
    public String description;
    public product(){}

    public product(String prod_name, String prod_price, String category, String description) {
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.category = category;
        this.description = description;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_price() {
        return prod_price;
    }

    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
