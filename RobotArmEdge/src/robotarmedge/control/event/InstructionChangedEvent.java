package robotarmedge.control.event;

import java.util.EventObject;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public class InstructionChangedEvent extends EventObject
{

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public InstructionChangedEvent(Object source)
    {
        super(source);
    }
    
}
