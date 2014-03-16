package robotarmedge.event;

import java.util.EventListener;

/**
 * 
 * 
 * @author Joshua Michael Daly
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
