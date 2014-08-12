package robotarmedge.control;

import java.util.Iterator;
import java.util.LinkedList;
import robotarmedge.control.event.TaskChangeListener;
import robotarmedge.control.event.TaskChangedEvent;
import robotarmedge.control.event.TaskListChangeListener;
import robotarmedge.control.event.TaskListChangedEvent;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public class TaskList extends LinkedList<Task> implements TaskChangeListener
{
    
    private final LinkedList<TaskListChangeListener> 
            taskListChangeListeners = new LinkedList<>();
    
    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    public void addTaskListChangeListener(TaskListChangeListener listener)
    {
        this.taskListChangeListeners.add(listener);
    }
    
    public void removeTaskListChangeListener(TaskListChangeListener listener)
    {
        this.taskListChangeListeners.remove(listener);
    }
    
    public void fireTaskAdded(Task task)
    {
        TaskListChangedEvent event = null;
        Iterator iterator = this.taskListChangeListeners.iterator();

        while (iterator.hasNext())
        {
            if (event == null)
            {
                event = new TaskListChangedEvent(this, task);
            }

            ((TaskListChangeListener) iterator.next()).taskAdded(event);
        }
    }
    
    public void fireTaskRemoved(Task task)
    {
        TaskListChangedEvent event = null;
        Iterator iterator = this.taskListChangeListeners.iterator();

        while (iterator.hasNext())
        {
            if (event == null)
            {
                event = new TaskListChangedEvent(this, task);
            }

            ((TaskListChangeListener) iterator.next()).taskRemoved(event);
        }
    }
    
    public void fireTaskChanged(Task task)
    {
        TaskListChangedEvent event = null;
        Iterator iterator = this.taskListChangeListeners.iterator();

        while (iterator.hasNext())
        {
            if (event == null)
            {
                event = new TaskListChangedEvent(this, task);
            }

            ((TaskListChangeListener) iterator.next()).taskChanged(event);
        }
    }
    
    @Override
    public boolean add(Task e)
    {
       boolean result = super.add(e);
       e.addTaskChangeListener(this);
       this.fireTaskAdded(e);
       
       return result;
    }
    
    public boolean remove(Task e)
    {
       boolean result = super.remove(e);
       e.removeTaskChangeListener(this);
       this.fireTaskRemoved(e);
       
       return result;
    }

    @Override
    public void taskDeleted(TaskChangedEvent evt)
    {
        this.remove((Task)evt.getSource());
    }
    
    @Override
    public void taskChanged(TaskChangedEvent evt)
    {
        this.fireTaskChanged((Task)evt.getSource());
    }
    
}
