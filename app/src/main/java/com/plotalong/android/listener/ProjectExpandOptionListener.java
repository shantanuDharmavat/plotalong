package com.plotalong.android.listener;


import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

/**
 * Created by kbhakade on 4/9/17.
 */

public interface ProjectExpandOptionListener {
    void onProjectOptionClick(int optionPosition, int projectPosition);

    void onProjectClick(ExpandableLayout expandableLayout, TextView textViewProjectName);
}
