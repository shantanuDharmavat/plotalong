package com.plotalong.android.model.proposalNotes;

/**
 * Created by kbhakade on 13/12/17.
 */

public class NotesContentsDataModel {
    private String when;
    private String who;
    private String what;
    private String type;
    private String proposal;
    private String hasConflict;
    private String expiryOn;

    public NotesContentsDataModel() {
    }

    public NotesContentsDataModel(String when, String who, String what, String type, String proposal, String hasConflict, String expiryOn) {
        this.when = when;
        this.who = who;
        this.what = what;
        this.type = type;
        this.proposal = proposal;
        this.hasConflict = hasConflict;
        this.expiryOn = expiryOn;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public String getHasConflict() {
        return hasConflict;
    }

    public void setHasConflict(String hasConflict) {
        this.hasConflict = hasConflict;
    }

    public String getExpiryOn() {
        return expiryOn;
    }

    public void setExpiryOn(String expiryOn) {
        this.expiryOn = expiryOn;
    }

    @Override
    public String toString() {
        return "NotesContentsDataModel{" +
                "when='" + when + '\'' +
                ", who='" + who + '\'' +
                ", what='" + what + '\'' +
                ", type='" + type + '\'' +
                ", proposal='" + proposal + '\'' +
                ", hasConflict='" + hasConflict + '\'' +
                ", expiryOn='" + expiryOn + '\'' +
                '}';
    }
}
