package com.plotalong.android.model.proposalNotes;

import java.util.ArrayList;

/**
 * Created by kbhakade on 13/12/17.
 */

public class NotesDataModel {
    private String has_conflicts;
    private ArrayList<NotesContentsDataModel> user_notes;
    private String expires_on;
    private String lic_type;

    public NotesDataModel() {
    }

    public NotesDataModel(String has_conflicts, ArrayList<NotesContentsDataModel> user_notes, String expires_on, String lic_type) {
        this.has_conflicts = has_conflicts;
        this.user_notes = user_notes;
        this.expires_on = expires_on;
        this.lic_type = lic_type;
    }

    public String getHas_conflicts() {
        return has_conflicts;
    }

    public void setHas_conflicts(String has_conflicts) {
        this.has_conflicts = has_conflicts;
    }

    public ArrayList<NotesContentsDataModel> getUser_notes() {
        return user_notes;
    }

    public void setUser_notes(ArrayList<NotesContentsDataModel> user_notes) {
        this.user_notes = user_notes;
    }

    public String getExpires_on() {
        return expires_on;
    }

    public void setExpires_on(String expires_on) {
        this.expires_on = expires_on;
    }

    public String getLic_type() {
        return lic_type;
    }

    public void setLic_type(String lic_type) {
        this.lic_type = lic_type;
    }

    @Override
    public String toString() {
        return "NotesDataModel{" +
                "has_conflicts='" + has_conflicts + '\'' +
                ", user_notes=" + user_notes +
                ", expires_on='" + expires_on + '\'' +
                ", lic_type='" + lic_type + '\'' +
                '}';
    }
}
