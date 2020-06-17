package shopping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money){
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setMoney(double money) {
        if (money < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    private void setName(String name) {
        if (name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public double getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product){
        if (product.getCost() > this.getMoney()){
            throw new IllegalArgumentException(String.format("%s can't afford %s", this.getName(), product.getName()));
        }
        this.products.add(product);
        this.money = this.getMoney() - product.getCost();
    }


}