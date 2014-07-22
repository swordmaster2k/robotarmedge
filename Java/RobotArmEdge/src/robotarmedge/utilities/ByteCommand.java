/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */

package robotarmedge.utilities;

/**
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public final class ByteCommand
{
    /**
     *
     */
    public static final byte STOP_ALL = 0x00;
    
    // Byte 0

    /**
     *
     */
    public static final byte GRIPPER_STOP = 0x00;

    /**
     *
     */
    public static final byte GRIPPER_CLOSE = 0x01;

    /**
     *
     */
    public static final byte GRIPPER_OPEN = 0x02;

    /**
     *
     */
    public static final byte WRIST_STOP = 0x00;

    /**
     *
     */
    public static final byte WRIST_UP = 0x04;

    /**
     *
     */
    public static final byte WRIST_DOWN = 0x08;
    
    /**
     *
     */
    public static final byte ELBOW_STOP = 0x00;

    /**
     *
     */
    public static final byte ELBOW_UP = 0x10;

    /**
     *
     */
    public static final byte ELBOW_DOWN = 0x20;
    
    /**
     *
     */
    public static final byte SHOULDER_STOP = 0x00;

    /**
     *
     */
    public static final byte SHOULDER_UP = 0x40;

    /**
     *
     */
    public static final byte SHOULDER_DOWN = (byte)0x80;
    
    // Byte 1

    /**
     *
     */
        public static final byte BASE_STOP = 0x00;

    /**
     *
     */
    public static final byte BASE_CLOCKWISE = 0x01;

    /**
     *
     */
    public static final byte BASE_ANTI_CLOCKWISE = 0x02;
    
    // Byte 2

    /**
     *
     */
        public static final byte LIGHT_ON = 0x01;

    /**
     *
     */
    public static final byte LIGHT_OFF = 0x00;
}
