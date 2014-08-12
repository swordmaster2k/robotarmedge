/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotarmedge.utilities;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Unimplemented.
 * 
 * @author Joshua Michael Daly
 */
public class StringResourceBundle extends ResourceBundle
{
    public static final String RESOURCES_PREFIX = "/robotarmedge/resources/";
    
    public static final String PROPERTIES_FILE = RESOURCES_PREFIX + 
            "RobotArmEdge_en.properties";
    
    private static final StringResourceBundle instance = 
            (StringResourceBundle)java.util.ResourceBundle.getBundle("robotarmedge/resources/RobotArmEdge_en"); // NOI18N
    
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
