package com.eg1.demo.pojo.DTO;

public class BmiDTO {
    private Double height;
    private Double weight;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String   toString() {
        return "BmiDTO{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }
}
