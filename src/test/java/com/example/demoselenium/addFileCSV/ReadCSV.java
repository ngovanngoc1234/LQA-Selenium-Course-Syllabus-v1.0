package com.example.demoselenium.addFileCSV;

import java.io.Serializable;

public class ReadCSV implements Serializable {
    private String ID;
    private String example;
    private String food;
    private String size;
    private String quantity;
    private String weight;
    private String temperatureC;
    private String smell;
    private String constitutive;
    private String offer;
    private String promotion;
    private String cartShipper;
    private String other;
    private String status;
    private String workerName;
    private String image;

    public ReadCSV() {
        super();
    }

    public ReadCSV(String ID, String example, String food, String size,
                   String quantity, String weight, String temperatureC, String smell,
                   String constitutive, String offer, String promotion, String cartShipper,
                   String other, String status, String workerName,String image) {
        super();
        this.ID = ID;
        this.example = example;
        this.food = food;
        this.size = size;
        this.quantity = quantity;
        this.weight = weight;
        this.temperatureC = temperatureC;
        this.smell = smell;
        this.constitutive = constitutive;
        this.offer = offer;
        this.promotion = promotion;
        this.cartShipper = cartShipper;
        this.other = other;
        this.status = status;
        this.workerName = workerName;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(String temperatureC) {
        this.temperatureC = temperatureC;
    }

    public String getSmell() {
        return smell;
    }

    public void setSmell(String smell) {
        this.smell = smell;
    }

    public String getConstitutive() {
        return constitutive;
    }

    public void setConstitutive(String constitutive) {
        this.constitutive = constitutive;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getCartShipper() {
        return cartShipper;
    }

    public void setCartShipper(String cartShipper) {
        this.cartShipper = cartShipper;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
