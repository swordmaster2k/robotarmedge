package robotarmedge.control.event;

import java.util.EventObject;
import robotarmedge.control.Task;

/**
 *
 * @author Joshua Michael Daly
 */
public class TaskListChangedEvent extends EventObject
{

    private final Task task;

    /*
     * ************************************************************************* 
     * Public Getters
     * *************************************************************************
     */
    public Task getTask()
    {
        return task;
    }
    
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public TaskListChangedEvent(Object source, Task task)
    {
        super(source);
        this.task = task;
    }
    
}
