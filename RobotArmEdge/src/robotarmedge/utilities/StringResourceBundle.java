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

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Unimplemented.
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class StringResourceBundle extends ResourceBundle
{

    public static final String RESOURCES_PREFIX = "/robotarmedge/resources/";

    public static final String PROPERTIES_FILE = RESOURCES_PREFIX
            + "RobotArmEdge_en.properties";

    private static final StringResourceBundle instance
            = (StringResourceBundle) java.util.ResourceBundle.getBundle("robotarmedge/resources/RobotArmEdge_en"); // NOI18N

    /*
     * ************************************************************************* 
     * Public Getters
     * *************************************************************************
     */
    public static StringResourceBundle getInstance()
    {
        return instance;
    }

    @Override
    protected Object handleGetObject(String key)
    {
        return null;
    }

    @Override
    public Enumeration<String> getKeys()
    {
        return null;
    }
    
}
