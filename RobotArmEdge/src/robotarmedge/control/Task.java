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

import java.util.TreeSet;

/**
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class Task
{
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
        byte bytes[] = { byte0, byte1 };
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
    
    public void addInstruction(Instruction instruction)
    {
        this.instructions.add(instruction);
        
        if (instruction.getByteType() == 0)
            this.byte0 = (byte)(this.byte0 + instruction.getCommand());
        else if (instruction.getByteType() == 1)
            this.byte1 = (byte)(this.byte1 + instruction.getCommand());
    }
    
    public void decrementByte1(byte amount)
    {
        this.byte0 -= amount;
    }
    
    public void decrementByte2(byte amount)
    {
        this.byte1 -= amount;
    }
}
