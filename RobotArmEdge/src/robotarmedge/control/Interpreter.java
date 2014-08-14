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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import robotarmedge.control.event.InterpreterFinishedEvent;
import robotarmedge.control.event.InterpreterFinishedListener;
import robotarmedge.device.UsbRobotArm;
import robotarmedge.utilities.ByteCommand;

/**
 *
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class Interpreter extends Thread
{
    
    private final LinkedList<InterpreterFinishedListener> 
            interpreterFinishedListeners = new LinkedList<>();

    private boolean isShuttingDown;

    private final TaskList runningTasks;
    private final UsbRobotArm usbRobotArm;

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public Interpreter(TaskList taskList)
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
        if (this.usbRobotArm != null)
        {
            // O(n^2) algorithm...
            for (Task task : this.runningTasks)
            {
                if (this.isShuttingDown)
                {
                    break;
                }

                Logger.getLogger(UsbRobotArm.class.getName()).log(Level.INFO,
                        "Sending Byte0: " + task.getByte0()
                        + " Sending Byte1:" + task.getByte1() + "\n",
                        this);

                this.usbRobotArm.sendBytes(task.getBytes());

                int lastRunTime = 0;
                byte[] bytes = task.getBytes();

                for (Instruction instruction : task.getInstructions())
                {
                    if (this.isShuttingDown)
                    {
                        break;
                    }
                    // Find out why this is bad...
                    //
                    // Having two instructions one after the other with
                    // very close run times causes problems here.
                    try
                    {
                        sleep(instruction.getRunTime() - lastRunTime);
                    }
                    catch (InterruptedException ex)
                    {
                        if (this.isShuttingDown)
                        {
                            break;
                        }
                        
                        Logger.getLogger(Interpreter.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }

                    lastRunTime = instruction.getRunTime();

                    if (instruction.getByteType() == 0)
                    {
                        bytes[0] -= instruction.getCommand();
                    }
                    else if (instruction.getByteType() == 1)
                    {
                        bytes[1] -= instruction.getCommand();
                    }

                    Logger.getLogger(UsbRobotArm.class.getName()).log(Level.INFO,
                            "Sending Byte0: " + task.getByte0()
                            + " Sending Byte1: " + task.getByte1() + "\n",
                            this);

                    this.usbRobotArm.sendBytes(new byte[] { bytes[0], 
                        bytes[1],
                        this.usbRobotArm.getLightState()
                    });
                }
            }
            
            this.usbRobotArm.sendBytes(new byte[] { ByteCommand.STOP_ALL, 
                        ByteCommand.STOP_ALL,
                        this.usbRobotArm.getLightState()
                    });
            this.fireInterpreterFinished();
        }
    }

    public void shutdown()
    {
        this.isShuttingDown = true;
    }
    
    public void addInterpreterFinishedListener(InterpreterFinishedListener listener)
    {
        this.interpreterFinishedListeners.add(listener);
    }
    
    public void removeInterpreterFinishedListener(InterpreterFinishedListener listener)
    {
        this.interpreterFinishedListeners.remove(listener);
    }
    
    public void fireInterpreterFinished()
    {
        InterpreterFinishedEvent event = null;
        Iterator iterator = this.interpreterFinishedListeners.iterator();

        while (iterator.hasNext())
        {
            if (event == null)
            {
                event = new InterpreterFinishedEvent(this);
            }

            ((InterpreterFinishedListener) iterator.next()).interpreterFinished(event);
        }
    }

    public static void main(String[] args)
    {
        Instruction instruction1 = new Instruction(ByteCommand.GRIPPER_OPEN, 2000, 0);
        Instruction instruction2 = new Instruction(ByteCommand.WRIST_UP, 3500, 0);
        Instruction instruction3 = new Instruction(ByteCommand.ELBOW_UP, 5000, 0);
        Instruction instruction4 = new Instruction(ByteCommand.SHOULDER_DOWN, 3000, 0);
        //Instruction instruction5 = new Instruction(ByteCommand.BASE_ANTI_CLOCKWISE, 2500, 1);

        //Instruction instruction1 = new Instruction(ByteCommand.GRIPPER_CLOSE, 2000, 0);
        //Instruction instruction2 = new Instruction(ByteCommand.WRIST_DOWN, 3500, 0);
        //Instruction instruction3 = new Instruction(ByteCommand.ELBOW_DOWN, 5000, 0);
        //Instruction instruction4 = new Instruction(ByteCommand.SHOULDER_UP, 3000, 0);
        //Instruction instruction5 = new Instruction(ByteCommand.BASE_CLOCKWISE, 2500, 1);
        Task task = new Task();
        task.addInstruction(instruction1);
        task.addInstruction(instruction2);
        task.addInstruction(instruction3);
        task.addInstruction(instruction4);
        //task.addInstruction(instruction5);

        TaskList taskList = new TaskList();
        taskList.add(task);

        Interpreter interpreter = new Interpreter(taskList);
        interpreter.start();

        UsbRobotArm.getInstance().toggleLight(true);
    }
}
