package robotarmedge.control;

import java.util.LinkedList;

/**
 *
 * @author Joshua Michael Daly
 */
public class Task
{
    private final LinkedList<Instruction> instructions;
    private final byte byte1;
    private final byte byte2;

    /*
     * ************************************************************************* 
     * Public Getters
     * *************************************************************************
     */

    public LinkedList<Instruction> getInstructions()
    {
        return this.instructions;
    }
    
    public byte getByte1()
    {
        return this.byte1;
    }

    public byte getByte2()
    {
        return this.byte2;
    }
    
    public byte[] getBytes()
    {
        byte bytes[] = { byte1, byte2 };
        return bytes;
    }
    
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    
    public Task(LinkedList<Instruction> instructions, byte byte1, byte byte2)
    {
        this.instructions = instructions;
        this.byte1 = byte1;
        this.byte2 = byte2;
    }
}
