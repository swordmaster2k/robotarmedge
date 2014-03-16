package robotarmedge.event;

import java.util.EventObject;
import robotarmedge.device.UsbRobotArm;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public class RobotArmChangedEvent extends EventObject
{
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */

    /**
     *
     * @param robotArm
     */
    
    
    public RobotArmChangedEvent(UsbRobotArm robotArm)
    {
        super(robotArm);
    }
}
