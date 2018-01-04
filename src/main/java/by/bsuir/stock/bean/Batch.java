package by.bsuir.stock.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Batch implements Serializable {

    private Shell date;
    private ArrayList<? extends Shell> list;
    private String command;
    private Boolean result;

    public Shell getDate() {
        return date;
    }

    public void setDate(Shell date) {
        this.date = date;
    }

    public ArrayList<? extends Shell> getList() { return list; }

    public void setList(ArrayList<? extends Shell> list) { this.list = list; }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
