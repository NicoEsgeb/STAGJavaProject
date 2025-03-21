package edu.uob;

import java.util.HashSet;

//--note: ----------------------------------------------------------------------------------------------
/*
This will be the digital representation of the ACTIONS.
1- It will store the actions read from the .xml file
2- It will be updated with the current state of the game if needed
 */
//--note: ----------------------------------------------------------------------------------------------



public class DigitalActions {
    private HashSet<Action> digitalActions;

    public DigitalActions() {
        this.digitalActions = new HashSet<>();
    }

    public void addDigitalAction(Action action) {
        this.digitalActions.add(action);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Action action : this.digitalActions) {
            sb.append("\n--------------------DigitalAction---------------------\n");
            sb.append("-triggers=");
            sb.append(action.getTriggers());
            sb.append("\n-subject=");
            sb.append(action.getSubject());
            sb.append("\n-consumed=");
            sb.append(action.getConsumed());
            sb.append("\n-produced=");
            sb.append(action.getProduced());
            sb.append("\n-narration=");
            sb.append(action.getNarration());
            sb.append("\n------------------------------------------------------");
        }
        return sb.toString();
    }
}
