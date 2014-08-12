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

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import robotarmedge.control.Instruction;
import robotarmedge.control.Task;
import robotarmedge.control.event.TaskChangeListener;
import robotarmedge.control.event.TaskChangedEvent;
import robotarmedge.utilities.ByteCommand;
import robotarmedge.view.ProgramMode;

/**
 *
 *
 * @author Joshua Michael Daly
 */
public class TaskPanel extends JPanel implements MouseListener, 
        ActionListener, TaskChangeListener
{

    private final Task model;
    private final ProgramMode parent;

    private final JPopupMenu popupMenu;
    private final JMenuItem deleteMenuItem;
    
    private boolean isHovered;

    /*
     * ************************************************************************* 
     * Public Getter
     * *************************************************************************
     */
    public Task getModel()
    {
        return model;
    }
    
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public TaskPanel(Task task, ProgramMode parent)
    {
        this.model = task;
        this.parent = parent;

        for (Instruction instruction : task.getInstructions())
        {
            this.add(new InstructionView(instruction));
        }

        this.setLayout(new FlowLayout());
        
        // Make singleton with this in it...
        java.util.ResourceBundle bundle = 
                java.util.ResourceBundle.getBundle("robotarmedge/resources/RobotArmEdge_en"); // NOI18N
        
        // If more commands are added associated actions with them instead.
        this.deleteMenuItem = new JMenuItem(bundle.getString("menu.popup.delete"));
        this.deleteMenuItem.addActionListener(this);
        
        this.popupMenu = new JPopupMenu();
        this.popupMenu.add(this.deleteMenuItem);

        this.add(popupMenu);
        
        this.addMouseListener(this);
    }

    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        if (this.isHovered)
        {
            g.setColor(Color.red);
            g.fillRect(0, 0, this.getParent().getWidth(), this.getHeight());

            g.setColor(Color.blue);
            g.drawRect(0, 0, this.getParent().getWidth() - 1, 
                this.getHeight() - 1);
            
            this.paintChildren(g);
        } 
    }
    
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

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            this.popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            this.popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        this.isHovered = true;
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        this.isHovered = false;
        this.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.deleteMenuItem))
        {
            this.parent.deleteTask(this);
        }
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

        TaskPanel panel = new TaskPanel(task, null);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.pack();
    }

}
