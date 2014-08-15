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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class ImageResourceBundle extends PropertyResourceBundle
{

    public static final String RESOURCES_PREFIX = "/robotarmedge/resources/";

    public static final String PROPERTIES_FILE = RESOURCES_PREFIX
            + "Images.properties";

    /*
     * ************************************************************************* 
     * Public Getters
     * *************************************************************************
     */
    public static ImageResourceBundle getInstance(InputStream stream)
    {
        try
        {
            return new ImageResourceBundle(stream);
        }
        catch (IOException ex)
        {
            Logger.getLogger(ImageResourceBundle.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    public URL getImage(String id)
    {
        URL imageUrl = this.getClass().getResource(RESOURCES_PREFIX + this.getString(id));

        return imageUrl;
    }

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    public ImageResourceBundle(InputStream stream) throws IOException
    {
        super(stream);
    }

}
