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
import java.awt.Panel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import robotarmedge.control.Instruction;
import robotarmedge.utilities.ByteCommand;

/**
 * 
 *  
 * @author Joshua Michael Daly
 */
public class TaskPanel extends JPanel
{
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public TaskPanel()
    {
        this.setLayout(new FlowLayout());
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        TaskPanel panel = new TaskPanel();

        Instruction instruction1 = new Instruction(ByteCommand.GRIPPER_CLOSE, 1000, 0);
        Instruction instruction2 = new Instruction(ByteCommand.WRIST_UP, 8000, 0);
        Instruction instruction3 = new Instruction(ByteCommand.ELBOW_DOWN, 4000, 0);
        Instruction instruction4 = new Instruction(ByteCommand.SHOULDER_UP, 10000, 0);
        Instruction instruction5 = new Instruction(ByteCommand.BASE_CLOCKWISE, 6000, 1);

        InstructionView instructionView1 = new InstructionView(instruction1);
        InstructionView instructionView2 = new InstructionView(instruction2);
        InstructionView instructionView3 = new InstructionView(instruction3);
        InstructionView instructionView4 = new InstructionView(instruction4);
        InstructionView instructionView5 = new InstructionView(instruction5);

        panel.add(instructionView1);
        panel.add(instructionView2);
        panel.add(instructionView3);
        panel.add(instructionView4);
        panel.add(instructionView5);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.pack();
    }
}
