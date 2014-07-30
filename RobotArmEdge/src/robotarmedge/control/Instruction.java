package robotarmedge.control;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public class Instruction implements Comparable<Instruction>
{
    private final byte command;
    private final int runTime;
    private final int byteType;

    /*
     * ************************************************************************* 
     * Public Getters
     * *************************************************************************
     */
    
    public byte getCommand()
    {
        return command;
    }

    public int getRunTime()
    {
        return runTime;
    }

    public int getByteType()
    {
        return byteType;
    }

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    
    public Instruction(byte command, int runTime, int type)
    {
        this.command = command;
        this.runTime = runTime;
        this.byteType = type;
    }

    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    
    @Override
    public int compareTo(Instruction o)
    {
        if (this.runTime < o.runTime)
            return -1;
        else if (this.runTime > o.runTime)
            return 1;
        else 
            return 0;
    }
}
