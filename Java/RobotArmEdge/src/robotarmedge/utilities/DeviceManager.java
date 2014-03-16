package robotarmedge.utilities;

import java.util.List;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbHub;

/**
 * 
 * 
 * @author Joshua Michael Daly
 */
public abstract class DeviceManager
{
    /*
     * ************************************************************************* 
     * Public Static Methods
     * *************************************************************************
     */
    
    /**
     * 
     * 
     * @param device 
     */
    public static void dump(UsbDevice device)
    {
        UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
        System.out.format("%04x:%04x%n", desc.idVendor() & 0xffff, 
                desc.idProduct() & 0xffff);
        
        if (device.isUsbHub())
        {
            UsbHub hub = (UsbHub) device;
            
            for (UsbDevice child : (List<UsbDevice>) hub.getAttachedUsbDevices())
            {
                dump(child);
            }
        }
    }
    
    /**
     *
     * @param hub
     * @param vendorId
     * @param productId
     * @return
     */
    public static UsbDevice findDevice(UsbHub hub, int vendorId, int productId)
    {
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices())
        {
            if (device == null)
                continue;
            
            UsbDeviceDescriptor descriptor;
            
            descriptor = device.getUsbDeviceDescriptor();
            
            if (descriptor.idVendor() == vendorId && 
                    descriptor.idProduct() == productId) 
            {
                return device;
            }
            
            if (device.isUsbHub())
            {
                device = findDevice((UsbHub) device, vendorId, productId);
                
                if (device != null)
                {
                    return device;
                }
            }
        }
        
        return null;
    }
}
