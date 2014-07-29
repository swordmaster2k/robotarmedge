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

/**
 *
 * @author Joshua Michael Daly
 * @version 1.0
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
