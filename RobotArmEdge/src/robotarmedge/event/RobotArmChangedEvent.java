/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */
package robotarmedge.event;

import java.util.EventObject;
import robotarmedge.device.UsbRobotArm;

/**
 *
 *
 * @author Joshua Michael Daly
 * @version 1.0
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
