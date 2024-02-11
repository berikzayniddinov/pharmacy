package kz.pharmacy.models;


public abstract class Entity { // Abstract class for entities

    protected int id; // Unique identifier for the entity.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
