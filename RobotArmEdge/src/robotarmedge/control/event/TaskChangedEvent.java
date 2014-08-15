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

/**
 * 
 * 
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class TaskChangedEvent extends EventObject
{

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public TaskChangedEvent(Object source)
    {
        super(source);
    }
    
}

