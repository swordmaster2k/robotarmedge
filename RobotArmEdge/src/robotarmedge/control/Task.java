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
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import robotarmedge.control.event.TaskChangeListener;
import robotarmedge.control.event.TaskChangedEvent;

/**
 *
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class Task implements Cloneable
{

    private final LinkedList<TaskChangeListener> taskChangeListeners = new LinkedList<>();
    
    private byte byte0;
    private byte byte1;

    private final TreeSet<Instruction> instructions;

    /*
     * ************************************************************************* 
     * Public Getters
     * *************************************************************************
     */
    public byte getByte0()
    {
        return this.byte0;
    }

    public byte getByte1()
    {
        return this.byte1;
    }

    public TreeSet<Instruction> getInstructions()
    {
        return instructions;
    }

    public byte[] getBytes()
    {
        byte bytes[] =
        {
            byte0, byte1
        };
        return bytes;
    }

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public Task()
    {
        this.byte0 = 0x00;
        this.byte1 = 0x00;
        this.instructions = new TreeSet<>();
    }

    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    public void addTaskChangeListener(TaskChangeListener listener)
    {
        this.taskChangeListeners.add(listener);
    }
    
    public void removeTaskChangeListener(TaskChangeListener listener)
    {
        this.taskChangeListeners.remove(listener);
    }
    
    public void fireTaskDeleted()
    {
        TaskChangedEvent event = null;
        Iterator iterator = this.taskChangeListeners.iterator();

        while (iterator.hasNext())
        {
            if (event == null)
            {
                event = new TaskChangedEvent(this);
            }

            ((TaskChangeListener) iterator.next()).taskDeleted(event);
        }
    }
    
    public void fireTaskChanged()
    {
        TaskChangedEvent event = null;
        Iterator iterator = this.taskChangeListeners.iterator();

        while (iterator.hasNext())
        {
            if (event == null)
            {
                event = new TaskChangedEvent(this);
            }

            ((TaskChangeListener) iterator.next()).taskChanged(event);
        }
    }
    
    public void addInstruction(Instruction instruction)
    {
        boolean didAdd = this.instructions.add(instruction);

        if (didAdd)
        {
            if (instruction.getByteType() == 0)
            {
                this.byte0 = (byte) (this.byte0 + instruction.getCommand());
            }
            else if (instruction.getByteType() == 1)
            {
                this.byte1 = (byte) (this.byte1 + instruction.getCommand());
            }
            
            this.fireTaskChanged();
        }
    }

    public void removeInstruction(Instruction instruction)
    {
        boolean didRemove = this.instructions.remove(instruction);

        if (didRemove)
        {
            if (instruction.getByteType() == 0)
            {
                this.byte0 = (byte) (this.byte0 - instruction.getCommand());
            }
            else if (instruction.getByteType() == 1)
            {
                this.byte1 = (byte) (this.byte1 - instruction.getCommand());
            }
            
            this.fireTaskChanged();
        }
    }

    public void decrementByte1(byte amount)
    {
        this.byte0 -= amount;
    }

    public void decrementByte2(byte amount)
    {
        this.byte1 -= amount;
    }
     
    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException ex)
        {
            Logger.getLogger(Instruction.class.getName()).log(Level.SEVERE, null, ex);
            return this;
        }

    }
}
