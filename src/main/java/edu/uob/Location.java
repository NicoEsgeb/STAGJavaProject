package edu.uob;

import java.util.HashSet;

public class Location extends GameEntity{
    private String locationDescription;
    private String locationName;
    private HashSet<EntityCategory> entities;
    private HashSet<String> paths;
    //--todo: [2] {items} Storage for Items or "things" in this location
    private HashSet<String> items;

    public Location(String locationName, String locationDescription){
        super(locationDescription,locationName);
        this.locationDescription = locationDescription;
        this.locationName = locationName;
        this.entities = new HashSet<>();
    }

    public void addEntity(EntityCategory entityCategory){
        this.entities.add(entityCategory);
        this.paths = new HashSet<>();
    }

    @Override
    public String toString() {
        String pathsString = pathsToString();
        return "Location[name='" + this.locationName + "', description='" + this.locationDescription + "'], " + pathsString;
    }

    public String pathsToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paths: [");
        if (paths != null && !paths.isEmpty()) {
            int i = 0;
            for (String path : paths) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(path);
                i++;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void addPath(String path){
        this.paths.add(path);
    }

    //--NOTE: -------------- G E T T E R S -----------------------------------------------------------
    public HashSet<EntityCategory> getEntities(){
        return this.entities;
    }

    public String getLocationName(){
        return this.locationName;
    }
}
