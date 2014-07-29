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

import java.util.EventListener;

/**
 * 
 * 
 * @author Joshua Michael Daly
 * @version 1.0
 */
public interface RobotArmChangeListener extends EventListener
{

    /**
     *
     * @param race
     */
    public void robotArmAttached(RobotArmChangedEvent race);
    
    /**
     *
     * @param race
     */
    public void robotArmDetached(RobotArmChangedEvent race);
}
