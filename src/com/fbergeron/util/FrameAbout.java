/*
 * Copyright (C) 1999-2011  Frédéric Bergeron (fbergeron@users.sourceforge.net)
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

package com.fbergeron.util;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Simple frame that displays information about the application.
 */
public class FrameAbout extends Frame {

    private static final String CARD_ABOUT = "About";
    private static final String CARD_CREDITS = "Credits";
    private Label labelVersion;
    private Button buttonShowCredits;
    private Label labelCredits;
    private TextArea textAreaCredits;
    private Button buttonHideCredits;
    private Label labelDate;
    private Label labelAuthor;
    private Label labelEmail;
    private Label labelWebSite;

    /**
     * Constructor for FrameAbout.
     */
    public FrameAbout() {
        setLayout(new CardLayout());

        Panel panelAbout = new Panel(new BorderLayout());
        panelAbout.setBackground(Color.white);

        Image imgLogo = Util.getImageResourceFile("logo.jpg", getClass());
        Util.loadImages(new Image[]{imgLogo}, this);
        ImagePanel panelPicture = new ImagePanel(imgLogo);

        Panel panelBottom = new Panel(new BorderLayout());

        Panel panelButtons = new Panel(new BorderLayout());
        buttonShowCredits = new Button();
        buttonShowCredits.addActionListener(
                evt -> showCredits()
        );
        panelButtons.add(buttonShowCredits, BorderLayout.SOUTH);

        Panel panelAuthor = new Panel(new GridLayout(0, 1));
        labelVersion = new Label("", Label.RIGHT);
        labelAuthor = new Label("", Label.RIGHT);
        labelEmail = new Label("", Label.RIGHT);
        labelWebSite = new Label("", Label.RIGHT);
        labelDate = new Label("", Label.RIGHT);
        panelAuthor.add(labelVersion);
        panelAuthor.add(labelAuthor);
        panelAuthor.add(labelEmail);
        panelAuthor.add(labelWebSite);
        panelAuthor.add(labelDate);

        panelBottom.add(panelButtons, BorderLayout.WEST);
        panelBottom.add(panelAuthor, BorderLayout.EAST);

        panelAbout.add(panelPicture, BorderLayout.CENTER);
        panelAbout.add(panelBottom, BorderLayout.SOUTH);

        Panel panelCredits = new Panel(new BorderLayout(5, 5));
        panelCredits.setBackground(Color.white);

        labelCredits = new Label();
        textAreaCredits = new TextArea();

        buttonHideCredits = new Button();
        buttonHideCredits.addActionListener(
                evt -> hideCredits()
        );

        panelCredits.add(labelCredits, BorderLayout.NORTH);
        panelCredits.add(textAreaCredits, BorderLayout.CENTER);
        panelCredits.add(buttonHideCredits, BorderLayout.SOUTH);

        add(panelAbout, CARD_ABOUT);
        add(panelCredits, CARD_CREDITS);

        hideCredits();

        addWindowListener(new WindowManager(this, WindowManager.HIDE_ON_CLOSE));
        pack();
    }

    private void showCredits() {
        ((CardLayout) getLayout()).show(this, CARD_CREDITS);
    }

    private void hideCredits() {
        ((CardLayout) getLayout()).show(this, CARD_ABOUT);
    }

    public Insets getInsets() {
        Insets insets = super.getInsets();
        int top = insets.top + 10;
        int right = insets.right + 10;
        int bottom = insets.bottom + 10;
        int left = insets.left + 10;
        return (new Insets(top, right, bottom, left));
    }

    /**
     * Sets the locale of the Frame.
     *
     * @param locale Locale of the frame.
     */
    public void setLocale(Locale locale) {
        super.setLocale(locale);

        ResourceBundle resBundle = ResourceBundle.getBundle(getClass().getName() + "Ress", locale);

        buttonShowCredits.setLabel(resBundle.getString("ShowCredits"));
        buttonHideCredits.setLabel(resBundle.getString("HideCredits"));

        labelCredits.setText(resBundle.getString("CreditsTitle"));
        textAreaCredits.setText(resBundle.getString("Credits"));

        labelVersion.setText(resBundle.getString("Version") + " " +
                resBundle.getString("VersionNumber"));
        labelAuthor.setText(resBundle.getString("By") + " : " + resBundle.getString("Author"));
        labelDate.setText(resBundle.getString("Copyright"));
        labelEmail.setText(resBundle.getString("Email"));
        labelWebSite.setText(resBundle.getString("WebSite"));

        setTitle(resBundle.getString("About") + " " + resBundle.getString("Solitaire"));
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
        hideCredits();
        super.setVisible(b);
    }

}
