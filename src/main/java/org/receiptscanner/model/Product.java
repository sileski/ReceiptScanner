package org.receiptscanner.model;

import com.google.gson.annotations.SerializedName;

public class Product implements Comparable<Product> {
    @SerializedName("name")
    private String name;
    @SerializedName("domestic")
    private Boolean domestic;
    @SerializedName("price")
    private Double price;
    @SerializedName("weight")
    private Integer weight;
    @SerializedName("description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDomestic() {
        return domestic;
    }

    public void setDomestic(Boolean domestic) {
        this.domestic = domestic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        String weightStr = weight != null ? weight + "g" : "N/A";
        return String.format(
                """
                        ...%s
                           Price: $%s
                           %s
                           Weight: %s""",
                name, price, truncate(description), weightStr
        );
    }

    private String truncate(String description) {
        if (description.length() > 10) {
            return description.substring(0, 10) + "...";
        }
        return description;
    }
}
