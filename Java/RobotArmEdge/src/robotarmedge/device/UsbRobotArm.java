package robotarmedge.device;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbServices;
import javax.usb.event.UsbDeviceDataEvent;
import javax.usb.event.UsbDeviceErrorEvent;
import javax.usb.event.UsbDeviceEvent;
import javax.usb.event.UsbDeviceListener;
import robotarmedge.utilities.ByteCommand;
import robotarmedge.utilities.DeviceManager;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public class UsbRobotArm implements UsbDeviceListener
{
    public static final int VENDOR_ID = 0x1267;
    public static final int PRODUCT_ID = 0x0;
    
    private UsbDevice robotArmDevice;
    private UsbControlIrp irp;
    
    private byte[] commands;
    
    /************************************************************
     * Public Getters and Setters
     ***********************************************************/
   
    /**
     * 
     * 
     * @return 
     */
    public UsbDevice getUsbDevice()
    {
        return this.robotArmDevice;
    }
    
    /**
     * 
     * 
     * @return 
     */
    public boolean isAttached()
    {
        return this.robotArmDevice != null;
    }
    
    /************************************************************
     * Public Constructors
     ***********************************************************/
    
    public UsbRobotArm()
    {
        try
        {
            // Get a list of the attached USB devices.
            UsbServices services = UsbHostManager.getUsbServices();
            UsbHub rootHub = services.getRootUsbHub();
            DeviceManager.dump(rootHub);
            
            // Look for the robot arm in that list.
            this.robotArmDevice = DeviceManager.findDevice(rootHub, 
                                                            VENDOR_ID,
                                                            PRODUCT_ID);
            
            // If we haven't found it then throw an exception.
            if (this.robotArmDevice == null)
            {
                throw new UsbException("Robot Arm Edge not found.");
            }
            else 
            {
                this.robotArmDevice.addUsbDeviceListener(this);
                this.commands = new byte[3];
                
                // Set up the control pipe.
                this.irp = robotArmDevice.createUsbControlIrp(
                    (byte) UsbConst.CONFIGURATION_SELF_POWERED,
                    (byte) UsbConst.REQUEST_GET_DESCRIPTOR,
                    (short) 0x100,
                    (short) 0
                    );
            }
        }
        catch (UsbException | SecurityException ex)
        {
            Logger.getLogger(UsbRobotArm.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    
    /************************************************************
     * Public Methods
     ***********************************************************/
    
    /**
     * 
     * 
     * @param on 
     */
    public void toggleLight(boolean on)
    {          
        if (on)
        {
            this.commands[2] = ByteCommand.LIGHT_ON;
        }
        else
        {
            this.commands[2] = ByteCommand.LIGHT_OFF;
        }
        
        this.sendCommands();
    }
    
    public void stopGripper()
    {
        this.commands[0] = ByteCommand.GRIPPER_STOP;
        this.sendCommands();
    }
    
    public void closeGripper()
    {
        this.commands[0] = ByteCommand.GRIPPER_CLOSE;
        this.sendCommands();
    }
    
    public void openGripper()
    {
        this.commands[0] = ByteCommand.GRIPPER_OPEN;
        this.sendCommands();
    }
    
    public void stopWrist()
    {
        this.commands[0] = ByteCommand.WRIST_STOP;
        this.sendCommands();
    }
            
    public void moveWristUp()
    {
        this.commands[0] = ByteCommand.WRIST_UP;
        this.sendCommands();
    }
    
    public void moveWristDown()
    {
        this.commands[0] = ByteCommand.WRIST_DOWN;
        this.sendCommands();
    }
    
    public void stopElbow()
    {
        this.commands[0] = ByteCommand.ELBOW_STOP;
        this.sendCommands();
    }
    
    public void moveElbowUp()
    {
        this.commands[0] = ByteCommand.ELBOW_UP;
        this.sendCommands();
    }
    
    public void moveElbowDown()
    {
        this.commands[0] = ByteCommand.ELBOW_DOWN;
        this.sendCommands();
    }
    
    public void stopShoulder()
    {
        this.commands[0] = ByteCommand.SHOULDER_STOP;
        this.sendCommands();
    }
    
    public void moveShoulderUp()
    {
        this.commands[0] = ByteCommand.SHOULDER_UP;
        this.sendCommands();
    }
    
    public void moveShoulderDown()
    {
        this.commands[0] = ByteCommand.SHOULDER_DOWN;
        this.sendCommands();
    }
    
    public void stopBase()
    {
        this.commands[1] = ByteCommand.BASE_STOP;
        this.sendCommands();
    }
    
    public void rotateBaseClockwise()
    {
        this.commands[1] = ByteCommand.BASE_CLOCKWISE;
        this.sendCommands();
    }
    
    public void rotateBaseAntiClockwise()
    {
        this.commands[1] = ByteCommand.BASE_ANTI_CLOCKWISE;
        this.sendCommands();
    }
    
    /************************************************************
     * Public Implemented Methods
     ***********************************************************/
    
    /**
     * 
     * @param ude 
     */
    @Override
    public void usbDeviceDetached(UsbDeviceEvent ude)
    {
        Logger.getLogger(UsbRobotArm.class.getName()).log(Level.WARNING, 
                    ude.getUsbDevice().toString(), ude);
        
        this.robotArmDevice = null;
    }

    @Override
    public void errorEventOccurred(UsbDeviceErrorEvent udee)
    {
        Logger.getLogger(UsbRobotArm.class.getName()).log(Level.SEVERE, 
                    udee.getUsbException().toString(), udee); 
    }

    @Override
    public void dataEventOccurred(UsbDeviceDataEvent udde)
    {
        String data = "Byte 0: " + udde.getData()[0] + " " +
                      "Byte 1: " + udde.getData()[1] + " " +
                      "Byte 2: " + udde.getData()[2];
        
        Logger.getLogger(UsbRobotArm.class.getName()).log(Level.INFO, 
                    data, udde);
    }
    
    /************************************************************
     * Private Methods
     ***********************************************************/
    
    private void sendCommands()
    {
        try
        {
            this.irp.setData(this.commands);
            this.robotArmDevice.syncSubmit(this.irp);
        }
        catch (UsbException | IllegalArgumentException | 
                UsbDisconnectedException ex)
        {
            Logger.getLogger(UsbRobotArm.class.getName()).log
                (Level.SEVERE, null, ex);
        }
    }
}
