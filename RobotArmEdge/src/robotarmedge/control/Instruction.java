package robotarmedge.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import robotarmedge.utilities.ByteCommand;

/**
 *
 *
 * @author Joshua Michael Daly
 */
public class Instruction implements Comparable<Instruction>, Cloneable
{

    private byte command;
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

    @Override
    public int compareTo(Instruction o)
    {
        if (this.runTime < o.runTime)
        {
            return -1;
        }
        else if (this.runTime > o.runTime)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    public void reverseCommand()
    {
        if (this.byteType == 0)
        {
            switch (this.command)
            {
                case ByteCommand.GRIPPER_CLOSE:
                    this.command = ByteCommand.GRIPPER_OPEN;
                    break;
                case ByteCommand.GRIPPER_OPEN:
                    this.command = ByteCommand.GRIPPER_CLOSE;
                    break;
                case ByteCommand.WRIST_UP:
                    this.command = ByteCommand.WRIST_DOWN;
                    break;
                case ByteCommand.WRIST_DOWN:
                    this.command = ByteCommand.WRIST_UP;
                    break;
                case ByteCommand.ELBOW_UP:
                    this.command = ByteCommand.ELBOW_DOWN;
                    break;
                case ByteCommand.ELBOW_DOWN:
                    this.command = ByteCommand.ELBOW_UP;
                    break;
                case ByteCommand.SHOULDER_UP:
                    this.command = ByteCommand.SHOULDER_DOWN;
                    break;
                case ByteCommand.SHOULDER_DOWN:
                    this.command = ByteCommand.SHOULDER_UP;
            }
        }
        else
        {
            switch (this.command)
            {
                case ByteCommand.BASE_CLOCKWISE:
                    this.command = ByteCommand.BASE_ANTI_CLOCKWISE;
                    break;
                case ByteCommand.BASE_ANTI_CLOCKWISE:
                    this.command = ByteCommand.BASE_CLOCKWISE;
            }
        }
    }
}
