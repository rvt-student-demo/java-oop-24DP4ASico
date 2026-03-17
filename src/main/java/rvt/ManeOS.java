package rvt;

public class ManeOS {
public static void main(String[] args) {
ShopingCart cart = new ShopingCart();
cart.add("milk", 3);
cart.add("buttermilk", 2);
cart.add("cheese", 5);
System.out.println("cart price: " + cart.price());
cart.add("computer", 899);
System.out.println("cart price: " + cart.price());

cart.print();
}
}