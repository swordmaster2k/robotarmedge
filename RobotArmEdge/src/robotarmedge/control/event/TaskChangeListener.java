package robotarmedge.control.event;

import java.util.EventListener;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public interface TaskChangeListener extends EventListener
{
    public void taskDeleted(TaskChangedEvent evt);
    
    public void taskChanged(TaskChangedEvent evt);
}
