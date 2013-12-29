package robotarmedge.event;

import java.util.EventListener;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public interface RobotArmChangeListener extends EventListener
{
    public void robotArmAttached(RobotArmChangedEvent race);
    
    public void robotArmDetached(RobotArmChangedEvent race);
}
