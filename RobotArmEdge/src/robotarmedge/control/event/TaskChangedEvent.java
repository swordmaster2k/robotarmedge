/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotarmedge.control.event;

import java.util.EventObject;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public class TaskChangedEvent extends EventObject
{

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public TaskChangedEvent(Object source)
    {
        super(source);
    }
    
}

