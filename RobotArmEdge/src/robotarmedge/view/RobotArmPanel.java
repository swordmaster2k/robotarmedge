/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */

package robotarmedge.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JPanel;
import robotarmedge.device.UsbRobotArm;
import robotarmedge.utilities.ByteCommand;

/**
 * 
 * 
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class RobotArmPanel extends JPanel
{
    private final String noHighlight = "/robotarmedge/resources/highlight_none.png";
    private final String gripperHighlight = "/robotarmedge/resources/highlight_gripper.png";
    private final String wristHighlight = "/robotarmedge/resources/highlight_wrist.png";
    private final String elbowHighlight = "/robotarmedge/resources/highlight_elbow.png";
    private final String shoulderHighlight = "/robotarmedge/resources/highlight_shoulder.png";
    private final String baseHighlight = "/robotarmedge/resources/highlight_base.png";
    
    private final String arrowCircleAnti = "/robotarmedge/resources/arrow-circle"
            + "-anticlockwise-large.png";
    private final String arrowCircle = "/robotarmedge/resources/arrow-circle-"
            + "large.png";
    private final String arrowCurve90 = "/robotarmedge/resources/arrow-curve-090"
            + "-large.png";
    private final String arrowCurve270 = "/robotarmedge/resources/arrow-curve-270"
            + "-large.png";
    private final String lightOn = "/robotarmedge/resources/highlight_light.png";
    
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
    
    private boolean gripperHovered;
    private boolean wristHovered;
    private boolean elbowHovered;
    private boolean shoulderHovered;
    private boolean baseHovered;
    
    private final UsbRobotArm usbRobotArm = UsbRobotArm.getInstance();
    
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
        URL imageUrl = this.getClass().getResource(this.noHighlight);
        this.noHighlightImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.gripperHighlight);
        this.gripperHighlightImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.wristHighlight);
        this.wristHighlightImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.elbowHighlight);
        this.elbowHighlightImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.shoulderHighlight);
        this.shoulderHighlightImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.baseHighlight);
        this.baseHighlightImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.arrowCircleAnti);
        this.arrowCircleAntiImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.arrowCircle);
        this.arrowCircleImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.arrowCurve90);
        this.arrowCurve90Image = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.arrowCurve270);
        this.arrowCurve270Image = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.lightOn);
        this.lightOnImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
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
    }
}
