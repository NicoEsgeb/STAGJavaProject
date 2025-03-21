package edu.uob;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class XmlParser {
    private HashSet<Action> actions;
    private DigitalActions digitalActions;

    public XmlParser() {
        this.actions = new HashSet<>();
        this.digitalActions = new DigitalActions();
    }

    public DigitalActions perform(File xmlFile) {
        try{
            File file = xmlFile;
            Document document = getDocument(file);

            Element tree = document.getDocumentElement();
            NodeList actionNodes = tree.getElementsByTagName("action");

            for (int i = 0; i < actionNodes.getLength(); i++) {
                //--NOTE: -- Retrieving <action> ...... <action> ----------
                Element actionElement = (Element) actionNodes.item(i);
                Action action = new Action();

                action = addTriggersKeyphrases(action, actionElement);
                action = addSubjectsEntities(action, actionElement);
                action = addConsumedEntities(action, actionElement);
                action = addProducedEntities(action, actionElement);
                action = addNarration(action, actionElement);

                this.digitalActions.addDigitalAction(action);
            }

            return this.digitalActions;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private Document getDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dBuilFact= DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuil = dBuilFact.newDocumentBuilder();
        Document document = dBuil.parse(file);
        document.getDocumentElement().normalize();

        return document;
    }

    private Action addTriggersKeyphrases(Action action, Element actionElement) {
        Element triggersElement = (Element) actionElement.getElementsByTagName("triggers").item(0);
        NodeList keyphrases = triggersElement.getElementsByTagName("keyphrase");

        //--note: Adding the keyphrases of triggers to the [action] Triggers inner HashShet
        for (int j = 0; j < keyphrases.getLength(); j++) {
            String strKeyphrase = keyphrases.item(j).getTextContent();
            action.addTrigger(strKeyphrase);
        }
        return action;
    }

    private Action addSubjectsEntities(Action action, Element actionElement) {
        Element subjectsElement = (Element) actionElement.getElementsByTagName("subjects").item(0);
        NodeList keyphrases = subjectsElement.getElementsByTagName("entityCategory");

        //--note: Adding the keyphrases of subjects to the [action] Subjects inner HashShet
        for (int j = 0; j < keyphrases.getLength(); j++) {
            String strKeyphrase = keyphrases.item(j).getTextContent();
            action.addSubject(strKeyphrase);
        }
        return action;
    }

    private Action addConsumedEntities(Action action, Element actionElement) {
        Element consumedElement = (Element) actionElement.getElementsByTagName("consumed").item(0);
        NodeList keyphrases = consumedElement.getElementsByTagName("entityCategory");

        //--note: Adding the keyphrases of <consumed> to the [action] consumed inner HashShet
        for (int j = 0; j < keyphrases.getLength(); j++) {
            String strKeyphrase = keyphrases.item(j).getTextContent();
            action.addConsumed(strKeyphrase);
        }
        return action;
    }

    private Action addProducedEntities(Action action, Element actionElement) {
        Element producedElement = (Element) actionElement.getElementsByTagName("produced").item(0);
        NodeList keyphrases = producedElement.getElementsByTagName("entityCategory");

        //--note: Adding the keyphrases of <consumed> to the [action] consumed inner HashShet
        for (int j = 0; j < keyphrases.getLength(); j++) {
            String strKeyphrase = keyphrases.item(j).getTextContent();
            action.addProduced(strKeyphrase);
        }
        return action;
    }

    private Action addNarration(Action action, Element actionElement) {
        Element narrationElement = (Element) actionElement.getElementsByTagName("narration").item(0);
        //--note: Adding the keyphrases of <consumed> to the [action] consumed inner HashShet
        if (narrationElement != null) {
            String strNarration = narrationElement.getTextContent().trim();
            action.addNarration(strNarration);
        }
        return action;
    }

}
