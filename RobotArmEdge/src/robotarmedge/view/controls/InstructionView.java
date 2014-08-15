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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import robotarmedge.control.Instruction;
import robotarmedge.utilities.ByteCommand;
import robotarmedge.utilities.ImageResourceBundle;

/**
 *
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class InstructionView extends JComponent implements MouseListener,
        ActionListener
{

    private final int BACKGROUND_WIDTH = 42;
    private final int BACKGROUND_HEIGHT = 60;
    private final int FONT_SIZE = 10;

    private final Instruction model;
    private Motor motor;
    private Image directionImage;
    private final Font drawingFont;

    private boolean isHovered;

    private final JPopupMenu popupMenu;
    private final JMenuItem deleteMenuItem;

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

        // Make singleton with this in it...
        java.util.ResourceBundle bundle
                = java.util.ResourceBundle.getBundle("robotarmedge/resources/RobotArmEdge_en"); // NOI18N

        // If more commands are added associated actions with them instead.
        this.deleteMenuItem = new JMenuItem(bundle.getString("menu.popup.delete.instruction"));
        this.deleteMenuItem.addActionListener(this);

        this.popupMenu = new JPopupMenu();
        this.popupMenu.add(this.deleteMenuItem);

        this.add(this.popupMenu);

        //this.popupMenu.addPopupMenuListener(this);
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
                this.motor = Motor.Gripper;

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
                this.motor = Motor.Wrist;

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
                this.motor = Motor.Elbow;

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
                this.motor = Motor.Shoulder;

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
            this.motor = Motor.Base;

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
        if (e.isPopupTrigger())
        {
            //this.isPoppedUp = true;
            this.popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            //this.isPoppedUp = true;
            this.popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.deleteMenuItem))
        {
            this.model.fireInstructionDeleted();
        }
    }
    
}

enum Motor
{

    Gripper, Wrist, Elbow, Shoulder, Base
    
}
