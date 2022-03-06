package com.example.poshell.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private Product product;
    private int amount;

    public void setAmount(int amount) {
        if(amount<0) return;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString(){
        return product.toString() +"\t" + amount;
    }
}
