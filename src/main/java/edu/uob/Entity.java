package edu.uob;

public class Entity extends GameEntity{
    private String entityDescription;
    private String entityName;

    public Entity(String entityName, String entityDescription){
        super(entityDescription,entityName);
        this.entityDescription = entityDescription;
        this.entityName = entityName;
    }

    @Override
    public String toString() {
        return "Entity[name='" + this.entityName + "', description='" + this.entityDescription + "']";
    }
}