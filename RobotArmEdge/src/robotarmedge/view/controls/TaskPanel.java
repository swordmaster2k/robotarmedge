/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */
package robotarmedge.view.controls;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import robotarmedge.control.Instruction;
import robotarmedge.control.Task;
import robotarmedge.control.event.TaskChangeListener;
import robotarmedge.control.event.TaskChangedEvent;
import robotarmedge.utilities.ByteCommand;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public class TaskPanel extends JPanel implements TaskChangeListener
{

    private final Task model;

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public TaskPanel(Task task)
    {
        this.model = task;

        for (Instruction instruction : task.getInstructions())
        {
            this.add(new InstructionView(instruction));
        }

        this.setLayout(new FlowLayout());
    }
    
    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    @Override
    public void taskChanged(TaskChangedEvent e)
    {
        // TODO: Consider adding a taskInstructionAdded and taskInstructionRemoved
        // events to make this process more efficient.
        this.removeAll();
        
        for (Instruction instruction : this.model.getInstructions())
        {
            this.add(new InstructionView(instruction));
        }
        
        this.repaint();
    }

    /*
     * ************************************************************************* 
     * Private Methods
     * *************************************************************************
     */
    public static void main(String[] args)
    {
        Instruction instruction1 = new Instruction(ByteCommand.GRIPPER_CLOSE, 1000, 0);
        Instruction instruction2 = new Instruction(ByteCommand.WRIST_UP, 8000, 0);
        Instruction instruction3 = new Instruction(ByteCommand.ELBOW_DOWN, 4000, 0);
        Instruction instruction4 = new Instruction(ByteCommand.SHOULDER_UP, 10000, 0);
        Instruction instruction5 = new Instruction(ByteCommand.BASE_CLOCKWISE, 6000, 1);

        Task task = new Task();
        task.addInstruction(instruction1);
        task.addInstruction(instruction2);
        task.addInstruction(instruction3);
        task.addInstruction(instruction4);
        task.addInstruction(instruction5);

        TaskPanel panel = new TaskPanel(task);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.pack();
    }

}
