/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotarmedge.control.event;

import java.util.EventListener;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public interface TaskChangeListener extends EventListener
{
    public void taskChanged(TaskChangedEvent e);
}
