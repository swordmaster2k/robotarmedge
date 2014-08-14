/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */
package robotarmedge.view.controls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import robotarmedge.control.Instruction;
import robotarmedge.utilities.ByteCommand;
import robotarmedge.utilities.ImageResourceBundle;

/**
 *
 *
 * @author Joshua Michael Daly
 */
public class InstructionView extends Component implements MouseListener
{

    private final int BACKGROUND_WIDTH = 38;
    private final int BACKGROUND_HEIGHT = 60;
    private final int FONT_SIZE = 12;

    private final Instruction model;
    private Motor motor;
    private Image directionImage;
    private final Font drawingFont;

    private boolean isHovered;

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public InstructionView(Instruction instruction)
    {
        this.model = instruction;
        this.addMouseListener(this);
        this.setSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
        this.setMinimumSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
        this.setMaximumSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
        this.setPreferredSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));

        this.drawingFont = new Font("Ubuntu", Font.PLAIN, FONT_SIZE);

        ImageResourceBundle imageResourceBundle = ImageResourceBundle.getInstance(
                ImageResourceBundle.class.getResourceAsStream(
                        ImageResourceBundle.PROPERTIES_FILE));

        // Determine which motor this instruction refers too and its direction,
        // only needed for the view.
        if (this.model.getByteType() == 0)
        {
            if (this.model.getCommand() == ByteCommand.GRIPPER_CLOSE
                    || this.model.getCommand() == ByteCommand.GRIPPER_OPEN)
            {
                this.motor = Motor.M1;

                if (this.model.getCommand() == ByteCommand.GRIPPER_CLOSE)
                {
                    this.directionImage = Toolkit.getDefaultToolkit().createImage(
                            imageResourceBundle.getImage("icon.arrow_curve_090"));
                }
                else
                {
                    this.directionImage = Toolkit.getDefaultToolkit().createImage(
                            imageResourceBundle.getImage("icon.arrow_curve_270"));
                }
            }

            if (this.model.getCommand() == ByteCommand.WRIST_UP
                    || this.model.getCommand() == ByteCommand.WRIST_DOWN)
            {
                this.motor = Motor.M2;

                if (this.model.getCommand() == ByteCommand.WRIST_UP)
                {
                    this.directionImage = Toolkit.getDefaultToolkit().createImage(
                            imageResourceBundle.getImage("icon.arrow_curve_090"));
                }
                else
                {
                    this.directionImage = Toolkit.getDefaultToolkit().createImage(
                            imageResourceBundle.getImage("icon.arrow_curve_270"));
                }
            }

            if (this.model.getCommand() == ByteCommand.ELBOW_UP
                    || this.model.getCommand() == ByteCommand.ELBOW_DOWN)
            {
                this.motor = Motor.M3;

                if (this.model.getCommand() == ByteCommand.ELBOW_UP)
                {
                    this.directionImage = Toolkit.getDefaultToolkit().createImage(
                            imageResourceBundle.getImage("icon.arrow_curve_090"));
                }
                else
                {
                    this.directionImage = Toolkit.getDefaultToolkit().createImage(
                            imageResourceBundle.getImage("icon.arrow_curve_270"));
                }
            }

            if (this.model.getCommand() == ByteCommand.SHOULDER_UP
                    || this.model.getCommand() == ByteCommand.SHOULDER_DOWN)
            {
                this.motor = Motor.M4;

                if (this.model.getCommand() == ByteCommand.SHOULDER_UP)
                {
                    this.directionImage = Toolkit.getDefaultToolkit().createImage(
                            imageResourceBundle.getImage("icon.arrow_curve_090"));
                }
                else
                {
                    this.directionImage = Toolkit.getDefaultToolkit().createImage(
                            imageResourceBundle.getImage("icon.arrow_curve_270"));
                }
            }
        }
        else
        {
            this.motor = Motor.M5;

            if (this.model.getCommand() == ByteCommand.BASE_CLOCKWISE)
            {
                this.directionImage = Toolkit.getDefaultToolkit().createImage(
                        imageResourceBundle.getImage("icon.arrow_circle"));
            }
            else
            {
                this.directionImage = Toolkit.getDefaultToolkit().createImage(
                        imageResourceBundle.getImage("icon.arrow_circle_anticlockwise"));
            }
        }
    }

    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    @Override
    public void paint(Graphics g)
    {
        if (this.isHovered)
        {
            g.setColor(Color.GREEN);
        }
        else
        {
            g.setColor(Color.BLUE);
        }

        g.fillRect(0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        if (this.isHovered)
        {
            g.setColor(Color.BLUE);
            g.drawRect(0, 0, BACKGROUND_WIDTH - 1, BACKGROUND_HEIGHT - 1);
        }

        g.setColor(Color.BLACK);
        g.setFont(this.drawingFont);

        int stringWidth = g.getFontMetrics().stringWidth(this.motor.name());

        g.drawString(this.motor.name(),
                (BACKGROUND_WIDTH - stringWidth) / 2,
                12);

        g.drawImage(this.directionImage,
                (BACKGROUND_WIDTH - this.directionImage.getWidth(this)) / 2,
                18,
                this);

        stringWidth = g.getFontMetrics().stringWidth(Double.toString(
                this.model.getRunTime() / 1000.0) + "s");

        g.drawString(Double.toString(this.model.getRunTime() / 1000.0) + "s",
                (BACKGROUND_WIDTH - stringWidth) / 2,
                56);
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        this.isHovered = true;
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        this.isHovered = false;
        this.repaint();
    }
}

enum Motor
{

    M1, M2, M3, M4, M5
}
