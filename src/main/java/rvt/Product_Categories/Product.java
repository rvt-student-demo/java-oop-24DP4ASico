package rvt.Product_Categories;

public class Product {
    private int id;
    private String name;
    private double price;
    private int categoryId;

    public Product(String name, double price, int categoryId) {
        this.id = 0;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
