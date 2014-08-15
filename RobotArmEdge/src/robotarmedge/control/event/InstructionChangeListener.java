package robotarmedge.control.event;

import java.util.EventListener;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public interface InstructionChangeListener extends EventListener
{
    public void instructionDeleted(InstructionChangedEvent evt);
    
    public void instructionChanged(InstructionChangedEvent evt);
}
