/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */
package robotarmedge.control.event;

import java.util.EventObject;
import robotarmedge.control.Task;

/**
 * 
 * 
 * @author Joshua Michael Daly
 * @version 1.0
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
