/*
 * Copyright (C) 2001-2011  Frédéric Bergeron (fbergeron@users.sourceforge.net)
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

/**
 * Panel drawn with an image.
 */
public class ImagePanel extends Panel {

    private Image img;
    private Image offscreen;
    private Graphics offscreenGr;

    /**
     * Create an <code>ImagePanel</code> with an image.
     *
     * @param image Image to be drawn on the canvas.
     */
    ImagePanel(Image image) {
        setImage(image);
    }

    public void setBounds(int x, int y, int width, int height) {
        if (offscreen != null) {
            offscreenGr.dispose();
            offscreen = null;
            offscreenGr = null;
        }
        super.setBounds(x, y, width, height);
    }

    /**
     * Redraw the panel.
     * This method is more efficient than the default behavior.
     *
     * @param g Graphics on which we draw the panel.
     */
    public void update(Graphics g) {
        //Create offscreen
        Dimension dim = this.getSize();
        if (offscreen == null) {
            offscreen = this.createImage(dim.width, dim.height);
            offscreenGr = offscreen.getGraphics();
        }

        paint(offscreenGr);

        g.drawImage(offscreen, 0, 0, this);
    }

    /**
     * Draw the panel.
     * <p>
     * Subclasses of <code>ImagePanel</code> can draw over the image
     * once it is loaded.
     *
     * @param g Graphics on which we draw the panel.
     */
    public void paint(Graphics g) {
        super.paint(g);

        // If no img is specified, nothing more to do.
        if (img == null)
            return;

        // Print a message while the image is loading.
        if (!prepareImage(img, this)) {
            String str = "Loading image...";
            FontMetrics fm = getFontMetrics(getFont());
            Dimension dim = getSize();
            int x = (dim.width - fm.stringWidth(str)) / 2;
            int y = (dim.height - fm.getHeight()) / 2;
            g.drawString(str, x, y);
            return;
        }

        // Draw the image when loaded.
        Dimension dim = getSize();
        int imgWidth = img.getWidth(this);
        int imgHeight = img.getHeight(this);
        int x = (dim.width - imgWidth) / 2;
        int y = (dim.height - imgHeight) / 2;
        // Rounding correction
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
        g.drawImage(img, x, y, this);
    }

    /**
     * Gets the preferred size of the component.
     *
     * @return The size of the image if specified.
     * Otherwise, returns default behavior.
     */
    public Dimension getPreferredSize() {
        if (img == null)
            return (super.getPreferredSize());
        int width = img.getWidth(this);
        int height = img.getHeight(this);
        return (new Dimension(width, height));
    }

    /**
     * Affect an image to the panel and redraw it.
     *
     * @param image Image to be drawn on the panel.
     */
    private void setImage(Image image) {
        img = image;
        repaint();
    }

}

