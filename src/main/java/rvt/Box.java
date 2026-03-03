package rvt;

import java.util.ArrayList;

public class Box implements Packable {

    private ArrayList<Packable> items;
    private double maximumCapacity;

    public Box(double maximumCapacity) {
        this.items = new ArrayList<Packable>();
        this.maximumCapacity = maximumCapacity;
    }

    public double weight(){
        double weight = 0;
        for(Packable item : items){
            weight += item.weight();
        }
        return weight;
    }

    public void add(Packable packable){
        if(this.weight() + packable.weight() <= this.maximumCapacity){
            items.add(packable);
        }
    }

    @Override
    public String toString(){
        return "Box: " + this.items.size() + " items, total weight " + this.weight() + " kg";
    }
    
}
