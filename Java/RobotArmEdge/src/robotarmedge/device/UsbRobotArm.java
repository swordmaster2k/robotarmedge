package robotarmedge.device;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbServices;
import javax.usb.event.UsbDeviceDataEvent;
import javax.usb.event.UsbDeviceErrorEvent;
import javax.usb.event.UsbDeviceEvent;
import javax.usb.event.UsbDeviceListener;
import javax.usb.event.UsbServicesEvent;
import javax.usb.event.UsbServicesListener;
import robotarmedge.event.RobotArmChangedEvent;
import robotarmedge.event.RobotArmChangeListener;
import robotarmedge.utilities.ByteCommand;
import robotarmedge.utilities.DeviceManager;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public class UsbRobotArm implements UsbDeviceListener, UsbServicesListener
{
    // Singleton.
    private static final UsbRobotArm usbRobotArm = new UsbRobotArm();
    
    /**
     *
     */
    public static final int VENDOR_ID = 0x1267;

    /**
     *
     */
    public static final int PRODUCT_ID = 0x0;
    
    private final LinkedList changeListeners = new LinkedList<>();
    
    private UsbServices usbServices;
    private UsbDevice robotArmDevice;
    private UsbControlIrp irp;
    
    private byte lightState;
    private byte gripperState;
    private byte wristState;
    private byte elbowState;
    private byte shoulderState;
    private byte baseState;
    
    /*
     * ************************************************************************* 
     * Public Getters and Setters
     * *************************************************************************
     */
   
    /**
     * Get an instance of the UsbRobotArm singleton.
     * 
     * @return the robot arm device
     */
    public static UsbRobotArm getInstance()
    {
        return usbRobotArm;
    }
    
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
    
    /*
     * ************************************************************************* 
     * Private Constructors
     * *************************************************************************
     */

    /**
     * Creates a new UsbRobotArm, if no device is attached an exception is 
     * thrown. This is a singleton constructor.
     */
    private UsbRobotArm()
    {
        try
        {
            // Get a list of the attached USB devices.
            this.usbServices = UsbHostManager.getUsbServices();
            this.usbServices.addUsbServicesListener(this);
            
            UsbHub rootHub = this.usbServices.getRootUsbHub();
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
                init();
            }
        }
        catch (UsbException | SecurityException ex)
        {
            Logger.getLogger(UsbRobotArm.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    
    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    
    /**
     * 
     * 
     * @param listener 
     */
    public void addRobotArmChangeListener(RobotArmChangeListener listener)
    {
       this.changeListeners.add(listener); 
    }
    
    /**
     * 
     * 
     * @param listener 
     */
    public void removeRobotArmChangeListener(RobotArmChangeListener listener)
    {
       this.changeListeners.remove(listener); 
    } 
    
    /**
     * 
     */
    public void stop()
    {
        this.resetStates();
        this.sendCommands();
    }
    
    /**
     * 
     * 
     * @param on 
     */
    public void toggleLight(boolean on)
    {          
        if (on)
        {
            this.lightState = ByteCommand.LIGHT_ON;
        }
        else
        {
            this.lightState = ByteCommand.LIGHT_OFF;
        }
        
        this.sendCommands();
    }
    
    /**
     *
     */
    public void stopGripper()
    {
        this.gripperState = ByteCommand.GRIPPER_STOP;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void closeGripper()
    {
        this.gripperState = ByteCommand.GRIPPER_CLOSE;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void openGripper()
    {
        this.gripperState = ByteCommand.GRIPPER_OPEN;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void stopWrist()
    {
        this.wristState = ByteCommand.WRIST_STOP;
        this.sendCommands();
    }
            
    /**
     *
     */
    public void moveWristUp()
    {
        this.wristState = ByteCommand.WRIST_UP;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void moveWristDown()
    {
        this.wristState = ByteCommand.WRIST_DOWN;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void stopElbow()
    {
        this.elbowState = ByteCommand.ELBOW_STOP;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void moveElbowUp()
    {
        this.elbowState = ByteCommand.ELBOW_UP;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void moveElbowDown()
    {
        this.elbowState = ByteCommand.ELBOW_DOWN;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void stopShoulder()
    {
        this.shoulderState = ByteCommand.SHOULDER_STOP;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void moveShoulderUp()
    {
        this.shoulderState = ByteCommand.SHOULDER_UP;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void moveShoulderDown()
    {
        this.shoulderState = ByteCommand.SHOULDER_DOWN;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void stopBase()
    {
        this.baseState = ByteCommand.BASE_STOP;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void rotateBaseClockwise()
    {
        this.baseState = ByteCommand.BASE_CLOCKWISE;
        this.sendCommands();
    }
    
    /**
     *
     */
    public void rotateBaseAntiClockwise()
    {
        this.baseState = ByteCommand.BASE_ANTI_CLOCKWISE;
        this.sendCommands();
    }
    
    public void sendBytes(byte[] commands)
    {
        try
        {
            byte[] data = new byte[3];
            data[0] = commands[0];
            data[1] = commands[1];
            data[2] = this.lightState;
            
            this.irp.setData(data);
            this.robotArmDevice.syncSubmit(this.irp);
        }
        catch (UsbException | IllegalArgumentException | 
                UsbDisconnectedException ex)
        {
            Logger.getLogger(UsbRobotArm.class.getName()).log
                (Level.SEVERE, null, ex);
        }
    }
    
    /*
     * ************************************************************************* 
     * Public Implemented Methods
     * *************************************************************************
     */
    
    /**
     * 
     * 
     * @param use 
     */
    @Override
    public void usbDeviceAttached(UsbServicesEvent use)
    {
        // If we are not already attached see if the device was the robot arm.
        if (this.robotArmDevice == null)
        {
            UsbDeviceDescriptor descriptor = use.getUsbDevice().
                                                    getUsbDeviceDescriptor();
            
            if (descriptor.idVendor() == VENDOR_ID)
            {
                if (descriptor.idProduct() == PRODUCT_ID)
                {
                    // It is the robot arm, connect it.
                    this.robotArmDevice = use.getUsbDevice();
                    this.init();
                    
                    this.fireRobotArmAttached();
                    
                    Logger.getLogger(UsbRobotArm.class.getName()).log(Level.INFO, 
                    "Robotic Arm attached.", use);
                }
            }
        }
    }

    /**
     *
     * @param use
     */
    @Override
    public void usbDeviceDetached(UsbServicesEvent use)
    {
        // Ignore this, don't care about other devices being detached.
    }
    
    /**
     * 
     * @param ude 
     */
    @Override
    public void usbDeviceDetached(UsbDeviceEvent ude)
    {
        Logger.getLogger(UsbRobotArm.class.getName()).log(Level.WARNING, 
                    ude.getUsbDevice().toString() + "\n", ude);
        
        this.resetStates();
        this.robotArmDevice = null;
        this.fireRobotArmDetached();
    }

    /**
     *
     * @param udee
     */
    @Override
    public void errorEventOccurred(UsbDeviceErrorEvent udee)
    {
        Logger.getLogger(UsbRobotArm.class.getName()).log(Level.SEVERE, 
                    udee.getUsbException().toString(), udee); 
    }

    /**
     *
     * @param udde
     */
    @Override
    public void dataEventOccurred(UsbDeviceDataEvent udde)
    {
        String data = "Byte 0: " + udde.getData()[0] + " " +
                      "Byte 1: " + udde.getData()[1] + " " +
                      "Byte 2: " + udde.getData()[2] + "\n";
        
        Logger.getLogger(UsbRobotArm.class.getName()).log(Level.INFO, 
                    data, udde);
    }
    
    /*
     * ************************************************************************* 
     * Private Methods
     * *************************************************************************
     */
    
    private void init()
    {
        this.robotArmDevice.addUsbDeviceListener(this);
        
        this.resetStates();
        
        // Set up the control pipe.
        this.irp = robotArmDevice.createUsbControlIrp(
                (byte) UsbConst.CONFIGURATION_SELF_POWERED,
                (byte) UsbConst.REQUEST_GET_DESCRIPTOR,
                (short) 0x100,
                (short) 0
        );
    }
    
    private void resetStates()
    {
        this.lightState = 0x00;
        this.gripperState = 0x00;
        this.wristState = 0x00;
        this.elbowState = 0x00;
        this.shoulderState = 0x00;
        this.baseState = 0x00;
    }
    
    private void sendCommands()
    {
        try
        {
            byte[] data = new byte[3];
            data[0] = (byte)(this.gripperState + this.wristState + 
                        this.elbowState + this.shoulderState);
            data[1] = this.baseState;
            data[2] = this.lightState;
            
            this.irp.setData(data);
            this.robotArmDevice.syncSubmit(this.irp);
        }
        catch (UsbException | IllegalArgumentException | 
                UsbDisconnectedException ex)
        {
            Logger.getLogger(UsbRobotArm.class.getName()).log
                (Level.SEVERE, null, ex);
        }
    }
    
    private void fireRobotArmAttached()
    {
        RobotArmChangedEvent event = null;
        Iterator iterator = this.changeListeners.iterator();

        while (iterator.hasNext()) 
        {
            if (event == null) 
            {
                event = new RobotArmChangedEvent(this);
            }
            
            ((RobotArmChangeListener)iterator.next()).robotArmAttached(event);
        }
    }
    
    private void fireRobotArmDetached()
    {
        RobotArmChangedEvent event = null;
        Iterator iterator = this.changeListeners.iterator();

        while (iterator.hasNext()) 
        {
            if (event == null) 
            {
                event = new RobotArmChangedEvent(this);
            }
            
            ((RobotArmChangeListener)iterator.next()).robotArmDetached(event);
        }
    }
}
