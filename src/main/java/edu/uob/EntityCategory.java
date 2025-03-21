package edu.uob;

import java.util.HashSet;

public class EntityCategory{
    private String entityName;
    private HashSet<Entity> entities;

    public EntityCategory(String entityName){
        this.entityName = entityName;
        this.entities = new HashSet<>();
    }

    public void addSubEntity(Entity entity){
        this.entities.add(entity);
    }

    @Override
    public String toString() {
        return "Entity Category: ['" + this.entityName + "']";
    }

    public HashSet<Entity> getSubEntities() {
        return this.entities;
    }

}
