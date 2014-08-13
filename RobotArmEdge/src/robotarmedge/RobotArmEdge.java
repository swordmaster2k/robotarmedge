/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */

package robotarmedge;

import javax.usb.UsbException;
import robotarmedge.view.SelectMode;

/**
 * The central driver class which contains the main method, it starts the 
 * applications GUI.
 * 
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class RobotArmEdge
{
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
            java.util.logging.Logger.getLogger(RobotArmEdge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(RobotArmEdge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(RobotArmEdge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(RobotArmEdge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    
    /**
     * Main entry point, creates the initial GUI which takes over from there on.
     * 
     * @param args the command line arguments
     * @throws javax.usb.UsbException
     */
    public static void main(String[] args) throws UsbException
    {
        setLookAndFeel();
        
        SelectMode view = new SelectMode();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}
