package robotarmedge;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbServices;
import robotarmedge.view.BasicMode;

/**
 *
 * 
 * @author Joshua Michael Daly
 */
public class RobotArmEdge
{

    private static void dump(UsbDevice device)
    {
        UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
        System.out.format("%04x:%04x%n", desc.idVendor() & 0xffff, desc.idProduct() & 0xffff);
        
        if (device.isUsbHub())
        {
            UsbHub hub = (UsbHub) device;
            for (UsbDevice child : (List<UsbDevice>) hub.getAttachedUsbDevices())
            {
                dump(child);
            }
        }
    }
    
    public static UsbDevice findDevice(UsbHub hub, int vendorId, int productId)
    {
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices())
        {
            UsbDeviceDescriptor desc;
            
            desc = device.getUsbDeviceDescriptor();
            
            if (desc.idVendor() == vendorId && desc.idProduct() == productId) 
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
    
    public static void setLookAndFeel()
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(BasicMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(BasicMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(BasicMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(BasicMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    
    /**
     * @param args the command line arguments
     * @throws javax.usb.UsbException
     */
    public static void main(String[] args) throws UsbException
    {
        setLookAndFeel();
        
        BasicMode view = new BasicMode();
        view.setVisible(true);
        
//        try
//        {
//            UsbServices services = UsbHostManager.getUsbServices();
//            UsbHub rootHub = services.getRootUsbHub();
//            dump(rootHub);
//            
//            UsbDevice device = findDevice(rootHub, 0x1267, 0x0);
//            
//            UsbControlIrp irp = device.createUsbControlIrp(
//                    (byte) UsbConst.CONFIGURATION_SELF_POWERED,
//                    (byte) UsbConst.REQUEST_GET_DESCRIPTOR,
//                    (short) 0x100,
//                    (short) 0
//            );
//            
//            byte[] commands = new byte[3];
//            commands[0] = 0x00;
//            commands[1] = 0x00;
//            commands[2] = 0x01;
//            
//            irp.setData(commands);
//            device.syncSubmit(irp);
//            
//            Thread.sleep(3000);
//            
//            commands[0] = 0x00;
//            commands[1] = 0x00;
//            commands[2] = 0x00;
//            
//            irp.setData(commands);
//            device.syncSubmit(irp);
//        }
//        catch (InterruptedException ex)
//        {
//            Logger.getLogger(RobotArmEdge.class.getName()).log(Level.SEVERE, 
//                              null, ex);
//        }
    }
}
