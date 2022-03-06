package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if(posService.getCart() == null){
            return "There is No Cart";
        }
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Modify Cart", key = "m")
    public String modifyCart(String productId, int amount) {
        if(posService.getCart() == null){
            return "There is No Cart";
        }

        if (posService.modify(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Empty Cart", key = "e")
    public String emptyCart() {
        if(posService.getCart() == null) {
            return "There is No Cart";
        }
        posService.empty(posService.getCart());

        return "The Cart is Now Empty";
    }

    @ShellMethod(value = "Print Cart", key = "print")
    public String printCart() {
        if(posService.getCart() == null) {
            return "There is No Cart";
        }
        return posService.getCart().toString();

    }

    @ShellMethod(value = "Checkout", key = "c")
    public String checkout() {
        if(posService.getCart() == null) {
            return "There is No Cart";
        }
        String info = posService.getCart().toString();

        posService.checkout(posService.getCart());
        return info;

    }
}
