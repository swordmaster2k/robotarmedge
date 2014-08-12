package robotarmedge.control.event;

import java.util.EventListener;

/**
 *
 * @author Joshua Michael Daly
 */
public interface TaskListChangeListener extends EventListener
{
    public void taskAdded(TaskListChangedEvent evt);
    
    public void taskRemoved(TaskListChangedEvent evt);
    
    public void taskChanged(TaskListChangedEvent evt);
}
