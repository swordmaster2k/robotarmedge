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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import robotarmedge.device.UsbRobotArm;
import robotarmedge.utilities.ByteCommand;
import robotarmedge.utilities.ImageResourceBundle;
import robotarmedge.view.ProgramMode;

/**
 * 
 * 
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class RobotArmPanel extends JPanel
{
    private final Image noHighlightImage;
    private final Image gripperHighlightImage;
    private final Image wristHighlightImage;
    private final Image elbowHighlightImage;
    private final Image shoulderHighlightImage;
    private final Image baseHighlightImage;
    
    private final Image arrowCircleAntiImage;
    private final Image arrowCircleImage;
    private final Image arrowCurve90Image;
    private final Image arrowCurve270Image;
    private final Image lightOnImage;
    
    private final Image arrowCircleAntiSmallImage;
    private final Image arrowCircleSmallImage;
    private final Image arrowCurve90SmallImage;
    private final Image arrowCurve270SmallImage;
    
    private boolean gripperHovered;
    private boolean wristHovered;
    private boolean elbowHovered;
    private boolean shoulderHovered;
    private boolean baseHovered;
    
    private final UsbRobotArm usbRobotArm = UsbRobotArm.getInstance();
    
    private ProgramMode programMode;
    
    /*
     * ************************************************************************* 
     * Public Getters and Setters
     * *************************************************************************
     */
    
    public boolean isGripperHovered()
    {
        return this.gripperHovered;
    }

    public void setGripperHovered(boolean gripperHovered)
    {
        this.gripperHovered = gripperHovered;
    }

    public boolean isWristHovered()
    {
        return this.wristHovered;
    }

    public void setWristHovered(boolean wristHovered)
    {
        this.wristHovered = wristHovered;
    }

    public boolean isElbowHovered()
    {
        return this.elbowHovered;
    }

    public void setElbowHovered(boolean elbowHovered)
    {
        this.elbowHovered = elbowHovered;
    }

    public boolean isShoulderHovered()
    {
        return this.shoulderHovered;
    }

    public void setShoulderHovered(boolean shoulderHovered)
    {
        this.shoulderHovered = shoulderHovered;
    }

    public boolean isBaseHovered()
    {
        return this.baseHovered;
    }

    public void setBaseHovered(boolean baseHovered)
    {
        this.baseHovered = baseHovered;
    }
    
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    
    public RobotArmPanel()
    {
        ImageResourceBundle imageResourceBundle = ImageResourceBundle.getInstance(
                ImageResourceBundle.class.getResourceAsStream(
                        ImageResourceBundle.PROPERTIES_FILE));

        this.noHighlightImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("image.highlight_none"));

        this.gripperHighlightImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("image.highlight_gripper"));

        this.wristHighlightImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("image.highlight_wrist"));

        this.elbowHighlightImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("image.highlight_elbow"));

        this.shoulderHighlightImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("image.highlight_shoulder"));

        this.baseHighlightImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("image.highlight_base"));

        this.arrowCircleAntiImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("icon.arrow_circle_anticlockwise_large"));

        this.arrowCircleImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("icon.arrow_circle_large"));

        this.arrowCurve90Image = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("icon.arrow_curve_090_large"));

        this.arrowCurve270Image = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("icon.arrow_curve_270_large"));

        this.lightOnImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("image.highlight_light"));
        
        this.arrowCircleAntiSmallImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("icon.arrow_circle_anticlockwise_small"));

        this.arrowCircleSmallImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("icon.arrow_circle_small"));

        this.arrowCurve90SmallImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("icon.arrow_curve_090_small"));

        this.arrowCurve270SmallImage = Toolkit.getDefaultToolkit().createImage(
                imageResourceBundle.getImage("icon.arrow_curve_270_small"));
    }
    
    public RobotArmPanel(ProgramMode programMode)
    {
        this();
        this.programMode = programMode;
    }
    
    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    
    @Override
    public void paint(Graphics g)
    {
        // Draw the background image.
        // Is the light on?
        if (this.usbRobotArm.getLightState() == ByteCommand.LIGHT_ON)
        {
            g.drawImage(this.lightOnImage, 0, 0, this);
        }
        else if (gripperHovered)
        {
            g.drawImage(this.gripperHighlightImage, 0, 0, this);
        }
        else if (wristHovered)
        {
            g.drawImage(this.wristHighlightImage, 0, 0, this);
        }
        else if (elbowHovered)
        {
            g.drawImage(this.elbowHighlightImage, 0, 0, this);
        }
        else if (shoulderHovered)
        {
            g.drawImage(this.shoulderHighlightImage, 0, 0, this);
        }
        else if (baseHovered)
        {
            g.drawImage(this.baseHighlightImage, 0, 0, this);
        }
        else
        {
            g.drawImage(this.noHighlightImage, 0, 0, this);
        }
        
        // Is the gripper moving?
        if (this.usbRobotArm.getGripperState() == ByteCommand.GRIPPER_OPEN)
        {
            g.drawImage(this.arrowCurve90Image, 10, 35, this);
            g.drawImage(this.arrowCurve270Image, 10, 180, this);
        }
        else if (this.usbRobotArm.getGripperState() == ByteCommand.GRIPPER_CLOSE)
        {
            g.drawImage(this.arrowCurve270Image, 10, 35, this);
            g.drawImage(this.arrowCurve90Image, 10, 180, this);
        }
        
        // Is the wrist moving?
        if (this.usbRobotArm.getWristState() == ByteCommand.WRIST_UP)
        {
            g.drawImage(this.arrowCurve90Image, 280, 60, this);
        }
        else if (this.usbRobotArm.getWristState() == ByteCommand.WRIST_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 280, 60, this);
        }
        
        // Is the elbow moving?
        if (this.usbRobotArm.getElbowState() == ByteCommand.ELBOW_UP)
        {
            g.drawImage(this.arrowCurve90Image, 400, 50, this);
        }
        else if (this.usbRobotArm.getElbowState() == ByteCommand.ELBOW_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 400, 50, this);
        }
        
        // Is the shoulder moving?
        if (this.usbRobotArm.getShoulderState() == ByteCommand.SHOULDER_UP)
        {
            g.drawImage(this.arrowCurve90Image, 290, 190, this);
        }
        else if (this.usbRobotArm.getShoulderState() == ByteCommand.SHOULDER_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 290, 190, this);
        }
        
        // Is the base rotating?
        if (this.usbRobotArm.getBaseState() == ByteCommand.BASE_CLOCKWISE)
        {
            g.drawImage(this.arrowCircleImage, 280, 270, this);
        }
        else if (this.usbRobotArm.getBaseState() == ByteCommand.BASE_ANTI_CLOCKWISE)
        {
            g.drawImage(this.arrowCircleAntiImage, 280, 270, this);
        }
        
        if (this.programMode != null)
        {
            // Draw all the 3D rectangles that contain the motors current
            // run times.
            g.setColor(Color.RED);
            g.fill3DRect(87, 84, 65, 25, true); // Gripper
            g.fill3DRect(270, 35, 65, 25, true); // Wrist
            g.fill3DRect(385, 25, 65, 25, true); // Elbow
            g.fill3DRect(270, 165, 65, 25, true); // Shoulder
            g.fill3DRect(270, 240, 65, 25, true); // Base
            
            // Draw the run times inside of the rectangles and their 
            // corresponding images.
            g.setFont(new Font("Ubuntu", Font.PLAIN, 12));
            g.setColor(Color.WHITE);
            
            if (this.programMode.getGripperCloseTime() > 0)
            {
                g.drawString(Double.toString(
                        this.programMode.getGripperCloseTime() / 1000.0) + "s",
                        87 + ((50 - getStringWidth(g, 
                                this.programMode.getGripperCloseTime())) / 2),
                        100);
                
                g.drawImage(this.arrowCurve90SmallImage, 136, 88, this);
            }
            else
            {
                g.drawString(Double.toString(
                        this.programMode.getGripperOpenTime() / 1000.0) + "s",
                        87 + ((50 - getStringWidth(g, 
                                this.programMode.getGripperOpenTime())) / 2),
                        100);
                
                g.drawImage(this.arrowCurve270SmallImage, 136, 88, this);
            }
            
            if (this.programMode.getWristUpTime() > 0)
            {
                g.drawString(Double.toString(
                        this.programMode.getWristUpTime() / 1000.0) + "s",
                        270 + ((50 - getStringWidth(g, 
                                this.programMode.getWristUpTime())) / 2),
                        51);
                
                g.drawImage(this.arrowCurve90SmallImage, 319, 39, this);
            }
            else
            {
                g.drawString(Double.toString(
                        this.programMode.getWristDownTime() / 1000.0) + "s",
                        270 + ((50 - getStringWidth(g, 
                                this.programMode.getWristDownTime())) / 2),
                        51);
                
                g.drawImage(this.arrowCurve270SmallImage, 319, 39, this);
            }
            
            if (this.programMode.getElbowUpTime() > 0)
            {
                g.drawString(Double.toString(
                        this.programMode.getElbowUpTime() / 1000.0) + "s",
                        385 + ((50 - getStringWidth(g, 
                                this.programMode.getElbowUpTime())) / 2),
                        41);
                
                g.drawImage(this.arrowCurve90SmallImage, 434, 29, this);
            }
            else
            {
                g.drawString(Double.toString(
                        this.programMode.getElbowDownTime() / 1000.0) + "s",
                        385 + ((50 - getStringWidth(g, 
                                this.programMode.getElbowDownTime())) / 2),
                        41);
                
                g.drawImage(this.arrowCurve270SmallImage, 434, 29, this);
            }
            
            if (this.programMode.getShoulderUpTime() > 0)
            {
                g.drawString(Double.toString(
                        this.programMode.getShoulderUpTime() / 1000.0) + "s",
                        270 + ((50 - getStringWidth(g, 
                                this.programMode.getShoulderUpTime())) / 2),
                        181);
                
                g.drawImage(this.arrowCurve90SmallImage, 319, 169, this);
            }
            else
            {
                g.drawString(Double.toString(
                        this.programMode.getShoulderDownTime() / 1000.0) + "s",
                        270 + ((50 - getStringWidth(g, 
                                this.programMode.getShoulderDownTime())) / 2),
                        181);
                
                g.drawImage(this.arrowCurve270SmallImage, 319, 169, this);
            }
            
            if (this.programMode.getBaseClockwiseTime() > 0)
            {
                g.drawString(Double.toString(
                        this.programMode.getBaseClockwiseTime() / 1000.0) + "s",
                        270 + ((50 - getStringWidth(g, 
                                this.programMode.getBaseClockwiseTime())) / 2),
                        256);
                
                g.drawImage(this.arrowCircleSmallImage, 317, 244, this);
            }
            else
            {
                g.drawString(Double.toString(
                        this.programMode.getBaseAnticlockwiseTime() / 1000.0) + "s",
                        270 + ((50 - getStringWidth(g, 
                                this.programMode.getBaseAnticlockwiseTime())) / 2),
                        256);
                
                g.drawImage(this.arrowCircleAntiSmallImage, 317, 244, this);
            }
        }
    }
    
    /*
     * ************************************************************************* 
     * Private Methods
     * *************************************************************************
     */
    
    private static int getStringWidth(Graphics g, long time)
    {
        return g.getFontMetrics().stringWidth(Double.toString(
                    time / 1000.0) + "s");
    }
}
