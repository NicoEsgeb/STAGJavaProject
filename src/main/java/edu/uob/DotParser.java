package edu.uob;

import com.alexmerz.graphviz.ParseException;
import com.alexmerz.graphviz.Parser;
import com.alexmerz.graphviz.objects.Edge;
import com.alexmerz.graphviz.objects.Graph;
import com.alexmerz.graphviz.objects.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.HashMap;
import java.util.HashSet;


public class DotParser {
    //--NOTE: --------------------------------Storage Idea------------------------------------
    //Hashmap1: [LocationMap]
    //    Hashmap<"location", Hashmap<String, Hashset>> ------> (i.e. "cabin", Hashmap2)

    //Hashmap2: [entitieMap]
    //    <"entitie", Hashset> ------> (i.e. "artefacts", [axe, potion])

    //--note----------------------------------------------------------------------------------
    private HashSet<Location> locationsSet;

    public static DigitalGraph parseFile(File fileGiven) {
        if (!fileGiven.exists()) {
            System.err.println("File not found: " + fileGiven.getAbsolutePath());
            return null;
        }
        try{

            Graph wholeTree = getGraphFromFile(fileGiven);
            HashMap<String,Graph> subTreesMap = createSubTreesMap(wholeTree);

            //--note: Parsing Locations -----------------------------------------
            Graph locationTree = subTreesMap.get("locations");
            //--todo: Parsing Paths ---------------------------------------------
            Graph pathTree = subTreesMap.get("paths");

            if (locationTree != null && !locationTree.getNodes(false).isEmpty()){

                HashSet<Location> locationsSet = parseLocations(locationTree);
                locationsSet = parseAndAddPaths(pathTree, locationsSet);

                DigitalGraph digitalGraph = new DigitalGraph(locationsSet);
                return digitalGraph;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static HashMap<String,Graph> createSubTreesMap(Graph wholeTree){
        HashMap<String,Graph> subTreesMap = new HashMap<String,Graph>();
        for (Graph subTree : wholeTree.getSubgraphs()) {
            String subTreeName = subTree.getId().getId();
            subTreesMap.put(subTreeName, subTree);
        }
        return subTreesMap;
    }

    private static Location createLocation(Graph locationGraph){
        Node locationNode = locationGraph.getNodes(false).get(0);
        String locationName = locationNode.getId().getId();
        String locationDescription = locationNode.getAttribute("description");
        Location location = new Location(locationName, locationDescription);

        return location;
    }

    private static EntityCategory createEntityCathegory(Graph entitiesGraph){
        if (entitiesGraph.getNodes(false).isEmpty()) {
            return null;
        }
        String entityCategory = entitiesGraph.getId().getId();
        return new EntityCategory(entityCategory);
    }

    private static Entity createEntity(Node entityNode){
        String entityName = entityNode.getId().getId();
        String entityDescription = entityNode.getAttribute("description");
        Entity entity = new Entity(entityName, entityDescription);

        return entity;
    }

    private static HashSet<Location> parseLocations(Graph locationTree){
        HashSet<Location> locationsSet = new HashSet<>();
        for (Graph location : locationTree.getSubgraphs()) {
            if(!location.getNodes(false).isEmpty()){
                //--note Create [currentLocation]------------------
                Location currentLocation = createLocation(location);

                for (Graph entityCategory : location.getSubgraphs()) {
                    //--note Create [currentEntityCategory]------------------
                    EntityCategory currentEntityCategory =  createEntityCathegory(entityCategory);

                    for (Node entityNode : entityCategory.getNodes(false)) {
                        //--note Create [currentEntity]------------------
                        Entity currentEntity = createEntity(entityNode);
                        //--note: Adding [Entity] Object to inner [EntityCategory] HashSet
                        if(currentEntityCategory != null){
                            currentEntityCategory.addSubEntity(currentEntity);
                        }
                    }
                    //--note: Adding [Entity] Object to inner [Location.entities] HashSet
                    currentLocation.addEntity(currentEntityCategory);
                }
                //--note: Adding [Location] object to [locationsSet]
                locationsSet.add(currentLocation);
            }
        }
        return locationsSet;
    }

    private static HashSet<Location> parseAndAddPaths(Graph pathsTree, HashSet<Location> locationsSet){
        for(Location location : locationsSet){
            String locationName = location.getLocationName();
            for (Edge edge : pathsTree.getEdges()) {
                Node sourceNode = edge.getSource().getNode();
                String source = sourceNode.getId().getId();

                if (locationName.equals(source)) {
                    Node targetNode = edge.getTarget().getNode();
                    String target = targetNode.getId().getId();
                    location.addPath(target);
                }
            }
        }
        return locationsSet;
    }

    private static Graph getGraphFromFile(File fileGiven) throws FileNotFoundException {
        try{
            Parser dotParser = new Parser();
            FileReader dotReader = new FileReader(fileGiven);

            dotParser.parse(dotReader);

            Graph wholeTree = dotParser.getGraphs().get(0);
            return wholeTree;
        } catch (FileNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
