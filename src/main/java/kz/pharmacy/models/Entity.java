package kz.pharmacy.models;


public abstract class Entity { // Abstract class for entities
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
