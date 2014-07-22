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
 * @author Joshua Michael Daly
 */
public class RobotArmPanel extends JPanel
{
    private final String robotArm = "/robotarmedge/resources/robot-arm.png";
    private final String arrowCircleAnti = "/robotarmedge/resources/arrow-circle-anticlockwise.png";
    private final String arrowCircle = "/robotarmedge/resources/arrow-circle.png";
    private final String arrowCurve90 = "/robotarmedge/resources/arrow-curve-090.png";
    private final String arrowCurve270 = "/robotarmedge/resources/arrow-curve-270.png";
    private final String lightOn = "/robotarmedge/resources/light-bulb.png";
    
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
        g.drawImage(this.robotArmImage, 0, 0, this);
        
        if (this.usbRobotArm.getGripperState() == ByteCommand.GRIPPER_OPEN)
        {
            g.drawImage(this.arrowCurve90Image, 15, 65, this);
            g.drawImage(this.arrowCurve270Image, 15, 180, this);
        }
        else if (this.usbRobotArm.getGripperState() == ByteCommand.GRIPPER_CLOSE)
        {
            g.drawImage(this.arrowCurve270Image, 15, 65, this);
            g.drawImage(this.arrowCurve90Image, 15, 180, this);
        }
        
        if (this.usbRobotArm.getWristState() == ByteCommand.WRIST_UP)
        {
            g.drawImage(this.arrowCurve90Image, 235, 70, this);
        }
        else if (this.usbRobotArm.getWristState() == ByteCommand.WRIST_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 235, 70, this);
        }
        
        if (this.usbRobotArm.getElbowState() == ByteCommand.ELBOW_UP)
        {
            g.drawImage(this.arrowCurve90Image, 475, 70, this);
        }
        else if (this.usbRobotArm.getElbowState() == ByteCommand.ELBOW_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 475, 70, this);
        }
        
        if (this.usbRobotArm.getShoulderState() == ByteCommand.SHOULDER_UP)
        {
            g.drawImage(this.arrowCurve90Image, 345, 200, this);
        }
        else if (this.usbRobotArm.getShoulderState() == ByteCommand.SHOULDER_DOWN)
        {
            g.drawImage(this.arrowCurve270Image, 345, 200, this);
        }
        
        if (this.usbRobotArm.getBaseState() == ByteCommand.BASE_CLOCKWISE)
        {
            g.drawImage(this.arrowCircleImage, 320, 270, this);
        }
        else if (this.usbRobotArm.getBaseState() == ByteCommand.BASE_ANTI_CLOCKWISE)
        {
            g.drawImage(this.arrowCircleAntiImage, 320, 270, this);
        }
        
        if (this.usbRobotArm.getLightState() == ByteCommand.LIGHT_ON)
        {
            g.drawImage(this.lightOnImage, 40, 110, this);
        }
    }
}
