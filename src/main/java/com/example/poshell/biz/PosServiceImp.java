package com.example.poshell.biz;

import com.example.poshell.db.PosDB;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {
        return posDB.getCart();
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public void checkout(Cart cart) {
        empty(cart);
    }

    @Override
    public void total(Cart cart) {

    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {

        if (amount<0) return false;
        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        this.getCart().addItem(new Item(product, amount));
        return true;
    }


    @Override
    public boolean empty(Cart cart) {
        this.getCart().emptyList();
        return true;
    }

    @Override
    public boolean modify(String productId, int amount) {
        if(amount < 0) return false;
        for(Item i : getCart().getItems()) {
            if(i.getProduct().getId().equals(productId)) {
                i.setAmount(amount);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
