package robotarmedge.control;

/**
 *
 * @author Joshua Michael Daly
 */
public class Instruction
{
    private final byte stopByte;
    private final int runTime;
    
    /*
     * ************************************************************************* 
     * Public Getters
     * *************************************************************************
     */
    
    public byte getStopByte()
    {
        return this.stopByte;
    }

    public int getRunTime()
    {
        return this.runTime;
    }
    
    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    
    public Instruction(byte stopByte, int runTime)
    {
        this.stopByte = stopByte;
        this.runTime = runTime;
    }
}
