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
    private final String robotArm = "/robotarmedge/resources/robot-arm.png";
    private final String arrowCircleAnti = "/robotarmedge/resources/arrow-circle-anticlockwise-large.png";
    private final String arrowCircle = "/robotarmedge/resources/arrow-circle-large.png";
    private final String arrowCurve90 = "/robotarmedge/resources/arrow-curve-090-large.png";
    private final String arrowCurve270 = "/robotarmedge/resources/arrow-curve-270-large.png";
    private final String lightOn = "/robotarmedge/resources/light-bulb-large.png";
    
    private final Image robotArmImage;
    private final Image arrowCircleAntiImage;
    private final Image arrowCircleImage;
    private final Image arrowCurve90Image;
    private final Image arrowCurve270Image;
    private final Image lightOnImage;
    
    private final UsbRobotArm usbRobotArm = UsbRobotArm.getInstance();
    
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public RobotArmPanel()
    {
        URL imageUrl = this.getClass().getResource(this.robotArm);
        this.robotArmImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
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
        g.drawImage(this.robotArmImage, 0, 0, this);
        
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
            g.drawImage(this.arrowCurve90Image, 210, 70, this);
        }
        else if (this.usbRobotArm.getWristState() == ByteCommand.WRIST_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 210, 70, this);
        }
        
        // Is the elbow moving?
        if (this.usbRobotArm.getElbowState() == ByteCommand.ELBOW_UP)
        {
            g.drawImage(this.arrowCurve90Image, 410, 60, this);
        }
        else if (this.usbRobotArm.getElbowState() == ByteCommand.ELBOW_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 410, 60, this);
        }
        
        // Is the shoulder moving?
        if (this.usbRobotArm.getShoulderState() == ByteCommand.SHOULDER_UP)
        {
            g.drawImage(this.arrowCurve90Image, 310, 200, this);
        }
        else if (this.usbRobotArm.getShoulderState() == ByteCommand.SHOULDER_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 310, 200, this);
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
        
        // Is the light on?
        if (this.usbRobotArm.getLightState() == ByteCommand.LIGHT_ON)
        {
            g.drawImage(this.lightOnImage, 20, 100, this);
        }
    }
}
