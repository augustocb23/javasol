/*
 * Copyright (C) 2002-2011  Frédéric Bergeron (fbergeron@users.sourceforge.net)
 *                          and other contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.fbergeron.solitaire;

import com.fbergeron.util.WindowManager;

import java.awt.*;

public class FrameCongratulations extends Frame {
    // Used for addNotify check.
    private boolean fComponentsAdjusted = false;

    FrameCongratulations() {
        setLayout(new BorderLayout(0, 0));
        setSize(200, 100);
        setVisible(false);
        Label labelCongratulations = new Label();
        labelCongratulations.setAlignment(java.awt.Label.CENTER);
        labelCongratulations.setEnabled(false);
        add(BorderLayout.CENTER, labelCongratulations);
        labelCongratulations.setBackground(java.awt.Color.white);
        labelCongratulations.setForeground(java.awt.Color.red);
        labelCongratulations.setFont(new Font("Dialog", Font.PLAIN, 24));
        labelCongratulations.setBounds(0, 0, 200, 100);

        labelCongratulations.setText(Solitaire.resBundle.getString("YouWon"));

        setTitle(Solitaire.resBundle.getString("Congratulations"));
        addWindowListener(new WindowManager(this, WindowManager.HIDE_ON_CLOSE));
    }

    public void addNotify() {
        // Record the size of the window prior to calling parents addNotify.
        Dimension d = getSize();

        super.addNotify();

        if (fComponentsAdjusted)
            return;

        // Adjust components according to the insets
        setSize(getInsets().left + getInsets().right + d.width,
                getInsets().top + getInsets().bottom + d.height);
        Component[] components = getComponents();
        for (Component component : components) {
            Point p = component.getLocation();
            p.translate(getInsets().left, getInsets().top);
            component.setLocation(p);
        }
        fComponentsAdjusted = true;
    }

    /**
     * Shows or hides the component depending on the boolean flag b.
     *
     * @param b if true, show the component; otherwise, hide the component.
     * @see java.awt.Component#isVisible
     */
    public void setVisible(boolean b) {
        Dimension scrSize = getToolkit().getScreenSize();
        Dimension size = getSize();
        if (b) {
            setLocation((scrSize.width - size.width) / 2, (scrSize.height - size.height) / 2);
        }
        super.setVisible(b);
    }
}
