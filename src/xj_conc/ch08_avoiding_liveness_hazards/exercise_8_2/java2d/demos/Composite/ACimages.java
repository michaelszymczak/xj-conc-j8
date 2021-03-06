/*
 * @(#)ACimages.java	1.28 06/08/29
 *
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

/*
 * @(#)ACimages.java	1.28 06/08/29
 */

package xj_conc.ch08_avoiding_liveness_hazards.exercise_8_2.java2d.demos.Composite;

import xj_conc.ch08_avoiding_liveness_hazards.exercise_8_2.java2d.*;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import static java.awt.Color.*;

/**
 * Compositing shapes on images.
 */
public class ACimages extends Surface {

    private static String s[] = {"box", "fight", "magnify",
        "boxwave", "globe", "snooze",
        "tip", "thumbsup", "dukeplug"};
    private static Image imgs[] = new Image[s.length];
    private static Color colors[] = {BLUE, CYAN, GREEN,
        MAGENTA, ORANGE, PINK, RED, YELLOW, LIGHT_GRAY};


    public ACimages() {
        setBackground(WHITE);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = getImage(s[i] + ".gif");
        }
    }


    public void render(int w, int h, Graphics2D g2) {

        float alpha = 0.0f;
        int iw = w / 3;
        int ih = (h - 45) / 3;
        float xx = 0, yy = 15;

        for (int i = 0; i < imgs.length; i++) {

            xx = (i % 3 == 0) ? 0 : xx + w / 3;
            switch (i) {
                case 3:
                    yy = h / 3 + 15;
                    break;
                case 6:
                    yy = h / 3 * 2 + 15;
            }

            g2.setComposite(AlphaComposite.SrcOver);
            g2.setColor(BLACK);
            AlphaComposite ac = AlphaComposite.SrcOver.derive(alpha += .1f);
            String s = "a=" + Float.toString(alpha).substring(0, 3);
            new TextLayout(s, g2.getFont(), g2.getFontRenderContext()).draw(g2, xx + 3, yy - 2);

            Shape shape = null;

            switch (i % 3) {
                case 0:
                    shape = new Ellipse2D.Float(xx, yy, iw, ih);
                    break;
                case 1:
                    shape = new RoundRectangle2D.Float(xx, yy, iw, ih, 25, 25);
                    break;
                case 2:
                    shape = new Rectangle2D.Float(xx, yy, iw, ih);
                    break;
            }
            g2.setColor(colors[i]);
            g2.setComposite(ac);
            g2.fill(shape);
            g2.drawImage(imgs[i], (int) xx, (int) yy, iw, ih, null);
        }
    }


    public static void main(String s[]) {
        createDemoFrame(new ACimages());
    }
}
