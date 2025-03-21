package edu.uob;

import java.util.HashSet;

public class Action {
    private HashSet<String> triggers;
    private HashSet<String> subject;
    private HashSet<String> consumed;
    private HashSet<String> produced;
    private String narration;

    public Action() {
        this.triggers = new HashSet<>();
        this.subject = new HashSet<>();
        this.consumed = new HashSet<>();
        this.produced = new HashSet<>();
    }


    //--note: ------------------------- Adders -------------------------------------
    public void addTrigger(String trigger) {
        this.triggers.add(trigger);
    }

    public void addSubject(String subject) {
        this.subject.add(subject);
    }

    public void addConsumed(String consumed) {
        this.consumed.add(consumed);
    }

    public void addProduced(String produced) {
        this.produced.add(produced);
    }

    public void addNarration(String narration) {
        this.narration = narration;
    }
    //------------------------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------DigitalAction---------------------\n");
        sb.append("-triggers=");
        sb.append(triggers);
        sb.append("\n-subject=");
        sb.append(subject);
        sb.append("\n-consumed=");
        sb.append(consumed);
        sb.append("\n-produced=");
        sb.append(produced);
        sb.append("\n-narration=");
        sb.append(narration);
        sb.append("\n------------------------------------------------------");
        return sb.toString();
    }


    //--note ------------------- Getters --------------------------
    public HashSet<String> getTriggers() {
        return this.triggers;
    }

    public HashSet<String> getSubject() {
        return this.subject;
    }

    public HashSet<String> getConsumed() {
        return this.consumed;
    }

    public HashSet<String> getProduced() {
        return this.produced;
    }

    public String getNarration() {
        return this.narration;
    }
    //-------------------------------------------------------------
}
