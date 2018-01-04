package by.bsuir.stock.basket;

import by.bsuir.stock.bean.CargoEntity;

import java.util.ArrayList;
import java.util.Collection;

public class Basket {

    private Collection<CargoEntity> basket = new ArrayList<CargoEntity>();

    private static Basket ourInstance = new Basket();

    public static Basket getInstance() {
        return ourInstance;
    }

    private Basket() {
    }

    public void add(CargoEntity cargo){
        basket.add(cargo);
    }

    public  void delete(CargoEntity cargo){
        basket.remove(cargo);
    }

    public Collection<CargoEntity> getBasket() {
        return basket;
    }

    public void deleteAll(){
        basket.clear();
    }
}
