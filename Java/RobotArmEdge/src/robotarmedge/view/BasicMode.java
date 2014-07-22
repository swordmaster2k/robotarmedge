/*
 ********************************************************************
 * Robot Arm Edge Version 1.0
 * This file copyright (C) 2014 Joshua Michael Daly
 * 
 * Robot Arm Edge is licensed under the GNU General Public License
 * version 3. See <http://www.gnu.org/licenses/> for more details.
 ********************************************************************
 */

package robotarmedge.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import robotarmedge.device.UsbRobotArm;
import robotarmedge.event.RobotArmChangedEvent;
import robotarmedge.event.RobotArmChangeListener;

/**
 *
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class BasicMode extends javax.swing.JFrame implements
        RobotArmChangeListener
{
    private UsbRobotArm usbRobotArm;

    private Mode currentMode;
    
    private final String applicationIconString = "/robotarmedge/resources/application.png";
    
    private final String usbConnectedString = "/robotarmedge/resources/plug.png";
    private final String usbDisconnectedString = "/robotarmedge/resources/plug--exclamation.png";
    
    private final Image usbConnectedImage;
    private final Image usbDisconnectedImage;
    
    Timer t = new Timer();
    TimerTask tt;

    /*
     * ************************************************************************* 
     * Public Constructors
     * *************************************************************************
     */
    
    /**
     * Creates new form BasicMode
     */
    public BasicMode()
    {
        initComponents();
        
        ImageIcon icon = new ImageIcon(getClass().getResource(this.applicationIconString));
        this.setIconImage(icon.getImage());
        
        URL imageUrl = this.getClass().getResource(this.usbConnectedString);
        this.usbConnectedImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        
        imageUrl = this.getClass().getResource(this.usbDisconnectedString);
        this.usbDisconnectedImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
    }

    /**
     * Creates new form BasicMode
     *
     * @param robotArm
     */
    public BasicMode(UsbRobotArm robotArm)
    {
        this();
        this.usbRobotArm = robotArm;
        this.usbRobotArm.addRobotArmChangeListener(this);

        this.currentMode = Mode.Mouse;
        
        if (this.usbRobotArm.isAttached())
        {
            this.toggleButttons(true);
            this.connectionLabel.setText("Connected");
            this.connectionLabel.setIcon(new javax.swing.ImageIcon(this.getClass().getResource
                ("/robotarmedge/resources/plug.png")));
        }
    }

    /*
     * ************************************************************************* 
     * Public Methods
     * *************************************************************************
     */
    
    @Override
    public void robotArmAttached(RobotArmChangedEvent race)
    {
        this.toggleButttons(true);
        this.connectionLabel.setText("Connected");
        this.connectionLabel.setIcon(new javax.swing.ImageIcon(this.getClass().getResource
                ("/robotarmedge/resources/plug.png")));
    }

    @Override
    public void robotArmDetached(RobotArmChangedEvent race)
    {
        this.toggleButttons(false);
        this.connectionLabel.setText("No Connection");
        this.connectionLabel.setIcon(new javax.swing.ImageIcon(this.getClass().getResource
                ("/robotarmedge/resources/plug--exclamation.png")));
    }

    /*
     * ************************************************************************* 
     * Private Methods
     * *************************************************************************
     */
    
    /**
     * 
     */
    private void changeMode()
    {
        boolean buttonState = true;

        if (Mode.Keyboard == this.currentMode)
        {
            buttonState = false;
        }

        this.toggleButttons(buttonState);
    }
    
    /**
     * 
     * 
     * @param buttonState 
     */
    private void toggleButttons(boolean buttonState)
    {
        this.closeGripperButton.setEnabled(buttonState);
        this.openGripperButton.setEnabled(buttonState);
        this.wristUpButton.setEnabled(buttonState);
        this.wristDownButton.setEnabled(buttonState);
        this.elbowUpButton.setEnabled(buttonState);
        this.elbowDownButton.setEnabled(buttonState);
        this.shoulderUpButton.setEnabled(buttonState);
        this.shoulderDownButton.setEnabled(buttonState);
        this.baseClockwiseButton.setEnabled(buttonState);
        this.baseAntiClockwiseButton.setEnabled(buttonState);
        this.lightToggleButton.setEnabled(buttonState);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        closeGripperButton = new javax.swing.JButton();
        openGripperButton = new javax.swing.JButton();
        wristDownButton = new javax.swing.JButton();
        wristUpButton = new javax.swing.JButton();
        elbowUpButton = new javax.swing.JButton();
        elbowDownButton = new javax.swing.JButton();
        shoulderDownButton = new javax.swing.JButton();
        shoulderUpButton = new javax.swing.JButton();
        baseAntiClockwiseButton = new javax.swing.JButton();
        baseClockwiseButton = new javax.swing.JButton();
        basicModeLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        lightToggleButton = new javax.swing.JToggleButton();
        robotArmPanel = new robotarmedge.view.RobotArmPanel();
        connectionLabel = new javax.swing.JLabel();
        aboutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Basic Mode");
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(0, 0));
        setName("BasicModeFrame"); // NOI18N
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        closeGripperButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-curve-090.png"))); // NOI18N
        closeGripperButton.setText("W");
        closeGripperButton.setToolTipText("Close Gripper");
        closeGripperButton.setEnabled(false);
        closeGripperButton.setMaximumSize(new java.awt.Dimension(70, 50));
        closeGripperButton.setMinimumSize(new java.awt.Dimension(70, 50));
        closeGripperButton.setPreferredSize(new java.awt.Dimension(70, 50));
        closeGripperButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                closeGripperButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                closeGripperButtonMouseReleased(evt);
            }
        });
        closeGripperButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        openGripperButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-curve-270.png"))); // NOI18N
        openGripperButton.setText("S");
        openGripperButton.setToolTipText("Open Gripper");
        openGripperButton.setEnabled(false);
        openGripperButton.setMaximumSize(new java.awt.Dimension(70, 50));
        openGripperButton.setMinimumSize(new java.awt.Dimension(70, 50));
        openGripperButton.setPreferredSize(new java.awt.Dimension(70, 50));
        openGripperButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                openGripperButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                openGripperButtonMouseReleased(evt);
            }
        });
        openGripperButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        wristDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-curve-270.png"))); // NOI18N
        wristDownButton.setText("D");
        wristDownButton.setToolTipText("Wrist Down");
        wristDownButton.setEnabled(false);
        wristDownButton.setMaximumSize(new java.awt.Dimension(70, 50));
        wristDownButton.setMinimumSize(new java.awt.Dimension(70, 50));
        wristDownButton.setPreferredSize(new java.awt.Dimension(70, 50));
        wristDownButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                wristDownButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                wristDownButtonMouseReleased(evt);
            }
        });
        wristDownButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        wristUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-curve-090.png"))); // NOI18N
        wristUpButton.setText("E");
        wristUpButton.setToolTipText("Wrist Up");
        wristUpButton.setEnabled(false);
        wristUpButton.setMaximumSize(new java.awt.Dimension(70, 50));
        wristUpButton.setMinimumSize(new java.awt.Dimension(70, 50));
        wristUpButton.setPreferredSize(new java.awt.Dimension(70, 50));
        wristUpButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                wristUpButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                wristUpButtonMouseReleased(evt);
            }
        });
        wristUpButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        elbowUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-curve-090.png"))); // NOI18N
        elbowUpButton.setText("R");
        elbowUpButton.setToolTipText("Elbow Up");
        elbowUpButton.setEnabled(false);
        elbowUpButton.setMaximumSize(new java.awt.Dimension(70, 50));
        elbowUpButton.setMinimumSize(new java.awt.Dimension(70, 50));
        elbowUpButton.setPreferredSize(new java.awt.Dimension(70, 50));
        elbowUpButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                elbowUpButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                elbowUpButtonMouseReleased(evt);
            }
        });
        elbowUpButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        elbowDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-curve-270.png"))); // NOI18N
        elbowDownButton.setText("F");
        elbowDownButton.setToolTipText("Elbow Down");
        elbowDownButton.setEnabled(false);
        elbowDownButton.setMaximumSize(new java.awt.Dimension(70, 50));
        elbowDownButton.setMinimumSize(new java.awt.Dimension(70, 50));
        elbowDownButton.setPreferredSize(new java.awt.Dimension(70, 50));
        elbowDownButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                elbowDownButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                elbowDownButtonMouseReleased(evt);
            }
        });
        elbowDownButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        shoulderDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-curve-270.png"))); // NOI18N
        shoulderDownButton.setText("J");
        shoulderDownButton.setToolTipText("Shoulder Down");
        shoulderDownButton.setEnabled(false);
        shoulderDownButton.setMaximumSize(new java.awt.Dimension(70, 50));
        shoulderDownButton.setMinimumSize(new java.awt.Dimension(70, 50));
        shoulderDownButton.setPreferredSize(new java.awt.Dimension(70, 50));
        shoulderDownButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                shoulderDownButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                shoulderDownButtonMouseReleased(evt);
            }
        });
        shoulderDownButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        shoulderUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-curve-090.png"))); // NOI18N
        shoulderUpButton.setText("U");
        shoulderUpButton.setToolTipText("Shoulder Up");
        shoulderUpButton.setEnabled(false);
        shoulderUpButton.setMaximumSize(new java.awt.Dimension(70, 50));
        shoulderUpButton.setMinimumSize(new java.awt.Dimension(70, 50));
        shoulderUpButton.setPreferredSize(new java.awt.Dimension(70, 50));
        shoulderUpButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                shoulderUpButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                shoulderUpButtonMouseReleased(evt);
            }
        });
        shoulderUpButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        baseAntiClockwiseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-circle-anticlockwise.png"))); // NOI18N
        baseAntiClockwiseButton.setText("K");
        baseAntiClockwiseButton.setToolTipText("Base Anti Clockwise");
        baseAntiClockwiseButton.setEnabled(false);
        baseAntiClockwiseButton.setMaximumSize(new java.awt.Dimension(70, 50));
        baseAntiClockwiseButton.setMinimumSize(new java.awt.Dimension(70, 50));
        baseAntiClockwiseButton.setPreferredSize(new java.awt.Dimension(70, 50));
        baseAntiClockwiseButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                baseAntiClockwiseButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                baseAntiClockwiseButtonMouseReleased(evt);
            }
        });
        baseAntiClockwiseButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        baseClockwiseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/arrow-circle.png"))); // NOI18N
        baseClockwiseButton.setText("I");
        baseClockwiseButton.setToolTipText("Base Clockwise");
        baseClockwiseButton.setEnabled(false);
        baseClockwiseButton.setMaximumSize(new java.awt.Dimension(70, 50));
        baseClockwiseButton.setMinimumSize(new java.awt.Dimension(70, 50));
        baseClockwiseButton.setPreferredSize(new java.awt.Dimension(70, 50));
        baseClockwiseButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                baseClockwiseButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                baseClockwiseButtonMouseReleased(evt);
            }
        });
        baseClockwiseButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        basicModeLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        basicModeLabel.setText("BASIC MODE");
        basicModeLabel.setToolTipText("Basic Mode");
        basicModeLabel.setFocusable(false);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lightToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/light-bulb-off.png"))); // NOI18N
        lightToggleButton.setText("L");
        lightToggleButton.setToolTipText("Light On");
        lightToggleButton.setEnabled(false);
        lightToggleButton.setMaximumSize(new java.awt.Dimension(70, 50));
        lightToggleButton.setMinimumSize(new java.awt.Dimension(70, 50));
        lightToggleButton.setPreferredSize(new java.awt.Dimension(70, 50));
        lightToggleButton.setRolloverEnabled(false);
        lightToggleButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/light-bulb.png"))); // NOI18N
        lightToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                lightToggleButtonActionPerformed(evt);
            }
        });
        lightToggleButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                componentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                componentKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout robotArmPanelLayout = new javax.swing.GroupLayout(robotArmPanel);
        robotArmPanel.setLayout(robotArmPanelLayout);
        robotArmPanelLayout.setHorizontalGroup(
            robotArmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );
        robotArmPanelLayout.setVerticalGroup(
            robotArmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
        );

        connectionLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/plug--exclamation.png"))); // NOI18N
        connectionLabel.setText("No Connection");
        connectionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        aboutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/information.png"))); // NOI18N
        aboutButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                aboutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(basicModeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(connectionLabel))
                    .addComponent(robotArmPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeGripperButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openGripperButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wristDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wristUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(elbowDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elbowUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(shoulderDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shoulderUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseAntiClockwiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baseClockwiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lightToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aboutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(basicModeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(connectionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(robotArmPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(baseAntiClockwiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(elbowUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(elbowDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(wristUpButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(closeGripperButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(wristDownButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(openGripperButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(shoulderUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(baseClockwiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(shoulderDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lightToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(aboutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shoulderDownButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderDownButtonMouseReleased
    {//GEN-HEADEREND:event_shoulderDownButtonMouseReleased
        if (this.shoulderDownButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopShoulder();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_shoulderDownButtonMouseReleased

    private void baseClockwiseButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseClockwiseButtonMousePressed
    {//GEN-HEADEREND:event_baseClockwiseButtonMousePressed
        if (this.baseClockwiseButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.rotateBaseClockwise();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_baseClockwiseButtonMousePressed

    private void baseClockwiseButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseClockwiseButtonMouseReleased
    {//GEN-HEADEREND:event_baseClockwiseButtonMouseReleased
        if (this.baseClockwiseButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopBase();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_baseClockwiseButtonMouseReleased

    private void baseAntiClockwiseButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseAntiClockwiseButtonMousePressed
    {//GEN-HEADEREND:event_baseAntiClockwiseButtonMousePressed
        if (this.baseAntiClockwiseButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.rotateBaseAntiClockwise();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_baseAntiClockwiseButtonMousePressed

    private void baseAntiClockwiseButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseAntiClockwiseButtonMouseReleased
    {//GEN-HEADEREND:event_baseAntiClockwiseButtonMouseReleased
        if (this.baseAntiClockwiseButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopBase();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_baseAntiClockwiseButtonMouseReleased

    private void lightToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_lightToggleButtonActionPerformed
    {//GEN-HEADEREND:event_lightToggleButtonActionPerformed
        JToggleButton toggleButton = (JToggleButton) evt.getSource();
        
        if (this.usbRobotArm.isAttached())
        {
            this.usbRobotArm.toggleLight(toggleButton.isSelected());
            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_lightToggleButtonActionPerformed

    private void shoulderDownButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderDownButtonMousePressed
    {//GEN-HEADEREND:event_shoulderDownButtonMousePressed
        if (this.shoulderDownButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveShoulderDown();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_shoulderDownButtonMousePressed

    private void shoulderUpButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderUpButtonMouseReleased
    {//GEN-HEADEREND:event_shoulderUpButtonMouseReleased
        if (this.shoulderUpButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopShoulder();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_shoulderUpButtonMouseReleased

    private void shoulderUpButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderUpButtonMousePressed
    {//GEN-HEADEREND:event_shoulderUpButtonMousePressed
        if (this.shoulderUpButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveShoulderUp();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_shoulderUpButtonMousePressed

    private void elbowDownButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowDownButtonMouseReleased
    {//GEN-HEADEREND:event_elbowDownButtonMouseReleased
        if (this.elbowDownButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopElbow();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_elbowDownButtonMouseReleased

    private void elbowDownButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowDownButtonMousePressed
    {//GEN-HEADEREND:event_elbowDownButtonMousePressed
        if (this.elbowDownButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveElbowDown();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_elbowDownButtonMousePressed

    private void elbowUpButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowUpButtonMouseReleased
    {//GEN-HEADEREND:event_elbowUpButtonMouseReleased
        if (this.elbowUpButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopElbow();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_elbowUpButtonMouseReleased

    private void elbowUpButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowUpButtonMousePressed
    {//GEN-HEADEREND:event_elbowUpButtonMousePressed
        if (this.elbowUpButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveElbowUp();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_elbowUpButtonMousePressed

    private void wristDownButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristDownButtonMouseReleased
    {//GEN-HEADEREND:event_wristDownButtonMouseReleased
        if (this.wristDownButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopWrist();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_wristDownButtonMouseReleased

    private void wristDownButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristDownButtonMousePressed
    {//GEN-HEADEREND:event_wristDownButtonMousePressed
        if (this.wristDownButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveWristDown();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_wristDownButtonMousePressed

    private void wristUpButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristUpButtonMouseReleased
    {//GEN-HEADEREND:event_wristUpButtonMouseReleased
        if (this.wristUpButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopWrist();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_wristUpButtonMouseReleased

    private void wristUpButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristUpButtonMousePressed
    {//GEN-HEADEREND:event_wristUpButtonMousePressed
        if (this.wristUpButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveWristUp();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_wristUpButtonMousePressed

    private void openGripperButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_openGripperButtonMouseReleased
    {//GEN-HEADEREND:event_openGripperButtonMouseReleased
        if (this.openGripperButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopGripper();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_openGripperButtonMouseReleased

    private void openGripperButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_openGripperButtonMousePressed
    {//GEN-HEADEREND:event_openGripperButtonMousePressed
        if (this.openGripperButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.openGripper();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_openGripperButtonMousePressed

    private void closeGripperButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_closeGripperButtonMouseReleased
    {//GEN-HEADEREND:event_closeGripperButtonMouseReleased
        if (this.closeGripperButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopGripper();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_closeGripperButtonMouseReleased

    private void closeGripperButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_closeGripperButtonMousePressed
    {//GEN-HEADEREND:event_closeGripperButtonMousePressed
        if (this.closeGripperButton.isEnabled())
        {
            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.closeGripper();
                this.robotArmPanel.repaint();
            }
        }
    }//GEN-LAST:event_closeGripperButtonMousePressed

    private void componentKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_componentKeyPressed
    {//GEN-HEADEREND:event_componentKeyPressed
         if (tt != null)
            return;

        tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() % 1000);
            }
        };

        t.scheduleAtFixedRate(tt, 0, 500);
    }//GEN-LAST:event_componentKeyPressed

    private void componentKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_componentKeyReleased
    {//GEN-HEADEREND:event_componentKeyReleased
        tt.cancel();
        tt = null;
        System.out.println("released");
    }//GEN-LAST:event_componentKeyReleased

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutButtonActionPerformed
    {//GEN-HEADEREND:event_aboutButtonActionPerformed
        AboutDialog aboutDialog = new AboutDialog(this, true);
        aboutDialog.setLocationRelativeTo(this);
        aboutDialog.setVisible(true);
    }//GEN-LAST:event_aboutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new BasicMode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton baseAntiClockwiseButton;
    private javax.swing.JButton baseClockwiseButton;
    private javax.swing.JLabel basicModeLabel;
    private javax.swing.JButton closeGripperButton;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JButton elbowDownButton;
    private javax.swing.JButton elbowUpButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JToggleButton lightToggleButton;
    private javax.swing.JButton openGripperButton;
    private robotarmedge.view.RobotArmPanel robotArmPanel;
    private javax.swing.JButton shoulderDownButton;
    private javax.swing.JButton shoulderUpButton;
    private javax.swing.JButton wristDownButton;
    private javax.swing.JButton wristUpButton;
    // End of variables declaration//GEN-END:variables

}
