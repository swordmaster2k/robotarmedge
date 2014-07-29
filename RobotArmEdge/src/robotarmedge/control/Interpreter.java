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
import robotarmedge.utilities.ByteCommand;

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
        if (this.usbRobotArm != null)
        {
            // O(n^2) algorithm...
            for (Task task : this.runningTasks)
            {
                Logger.getLogger(UsbRobotArm.class.getName()).log(Level.INFO,
                        "Sending Byte0: " + task.getByte0()
                        + " Sending Byte1:" + task.getByte1() + "\n",
                        this);

                this.usbRobotArm.sendBytes(task.getBytes());

                int lastRunTime = 0;

                for (Instruction instruction : task.getInstructions())
                {
                    try
                    {
                        // Find out why this is bad...
                        //
                        // Having two instructions one after the other with
                        // very close run times causes problems here.
                        sleep(instruction.getRunTime() - lastRunTime);
                        lastRunTime = instruction.getRunTime();

                        if (instruction.getByteType() == 0)
                        {
                            task.decrementByte1(instruction.getCommand());
                        }
                        else if (instruction.getByteType() == 1)
                        {
                            task.decrementByte2(instruction.getCommand());
                        }

                        Logger.getLogger(UsbRobotArm.class.getName()).log(Level.INFO,
                                "Sending Byte0: " + task.getByte0()
                                + " Sending Byte1: " + task.getByte1() + "\n",
                                this);

                        this.usbRobotArm.sendBytes(task.getBytes());
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

    public static void main(String[] args)
    {
        Instruction instruction1 = new Instruction(ByteCommand.GRIPPER_CLOSE, 1000, 0);
        Instruction instruction2 = new Instruction(ByteCommand.WRIST_DOWN, 3000, 0);
        Instruction instruction3 = new Instruction(ByteCommand.ELBOW_DOWN, 2000, 0);
        Instruction instruction4 = new Instruction(ByteCommand.SHOULDER_DOWN, 2500, 0);
        Instruction instruction5 = new Instruction(ByteCommand.BASE_CLOCKWISE, 3100, 1);

        Task task = new Task();
        task.addInstruction(instruction1);
        task.addInstruction(instruction2);
        task.addInstruction(instruction3);
        task.addInstruction(instruction4);
        task.addInstruction(instruction5);

        LinkedList<Task> taskList = new LinkedList<>();
        taskList.add(task);

        Interpreter interpreter = new Interpreter(taskList);
        interpreter.start();
    }
}
