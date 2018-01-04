package by.bsuir.stock.bean;


import java.io.Serializable;

public abstract class Shell implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
