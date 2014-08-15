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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
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
public class TaskPanel extends JPanel implements MouseListener, 
        ActionListener, TaskChangeListener, PopupMenuListener
{

    private final Task model;

    private final JPopupMenu popupMenu;
    private final JMenuItem deleteMenuItem;
    
    private boolean isHovered;
    private boolean isPoppedUp;

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
    public TaskPanel(Task task)
    {
        this.model = task;
        this.model.addTaskChangeListener(this);

        for (Instruction instruction : task.getInstructions())
        {
            this.add(new InstructionView(instruction));
        }

        this.setLayout(new FlowLayout());
        
        // Make singleton with this in it...
        java.util.ResourceBundle bundle = 
                java.util.ResourceBundle.getBundle("robotarmedge/resources/RobotArmEdge_en"); // NOI18N
        
        // If more commands are added associated actions with them instead.
        this.deleteMenuItem = new JMenuItem(bundle.getString("menu.popup.delete.task"));
        this.deleteMenuItem.addActionListener(this);
        
        this.popupMenu = new JPopupMenu();
        this.popupMenu.add(this.deleteMenuItem);

        this.add(popupMenu);
        
        this.addMouseListener(this);
        this.popupMenu.addPopupMenuListener(this);
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
    public void taskDeleted(TaskChangedEvent evt)
    {
        
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
            this.isPoppedUp = true;
            this.popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            this.isPoppedUp = true;
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
       if (!this.isPoppedUp)
       {
            this.isHovered = false;
            this.repaint();
       }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Move into an Action.
        if (e.getSource().equals(this.deleteMenuItem))
        {
            this.model.fireTaskDeleted();
        }
    }
    
    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e)
    {
        
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
    {
        
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e)
    {
        this.isPoppedUp = false;
        this.isHovered = false;
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
