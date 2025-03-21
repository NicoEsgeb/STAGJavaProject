package edu.uob;

import java.util.HashSet;

//--note: ----------------------------------------------------------------------------------------------
/*
This will be the digital representation of the whole tree.
1- It will store the graphs read
2- It will be updated with the current state of the game if needed
 */
//--note: ----------------------------------------------------------------------------------------------

public class DigitalGraph {
    private HashSet<Location> digitalGraph;
    public DigitalGraph(HashSet<Location> locationHashSet) {
        this.digitalGraph = locationHashSet;
    }


    public void printLocations(){
        for (Location location : this.digitalGraph){
            System.out.println(location.toString());
        }
    }

    public void printGraph(){
        int counter = 1;
        for (Location location : this.digitalGraph){
            if(location != null){
                System.out.println("\n[" + counter + "]" + location.toString());
                for (EntityCategory entityCategory : location.getEntities()){
                    if(entityCategory != null){
                        System.out.print("    |\n    |->");
                        System.out.println(entityCategory.toString());
                        for (Entity entity : entityCategory.getSubEntities()){
                            System.out.print("           |\n           |->");
                            System.out.println(entity.toString());
                        }
                    }
                }
                counter++;
            }
        }
    }
}
