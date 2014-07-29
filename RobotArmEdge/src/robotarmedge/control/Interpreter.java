/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */

package robotarmedge.control;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import robotarmedge.device.UsbRobotArm;

/**
 * 
 * 
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class Interpreter extends Thread
{
    private final LinkedList<Task> runningTasks;
    private final UsbRobotArm usbRobotArm;
    
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    
    public Interpreter(LinkedList<Task> taskList)
    {
        this.runningTasks = taskList;
        this.usbRobotArm = UsbRobotArm.getInstance();
    }
    
    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    
    @Override
    public void run()
    {
        for (Task task : this.runningTasks)
        {
            byte startByte1 = task.getByte1();
            byte startByte2 = task.getByte2();
            this.usbRobotArm.sendBytes(task.getBytes());
            
            for (Instruction instruction : task.getInstructions())
            {
                try
                {
                    sleep(instruction.getRunTime());
                   
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(Interpreter.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
