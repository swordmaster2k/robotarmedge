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

import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import robotarmedge.control.Instruction;
import robotarmedge.control.Interpreter;
import robotarmedge.control.Task;
import robotarmedge.control.event.InterpreterFinishedEvent;
import robotarmedge.control.event.InterpreterFinishedListener;
import robotarmedge.device.UsbRobotArm;
import robotarmedge.event.RobotArmChangedEvent;
import robotarmedge.event.RobotArmChangeListener;
import robotarmedge.utilities.ByteCommand;
import robotarmedge.utilities.ImageResourceBundle;
import robotarmedge.view.controls.TaskPanel;

/**
 *
 *
 * @author Joshua Michael Daly
 * @version 1.0
 */
public class ProgramMode extends javax.swing.JFrame implements
        RobotArmChangeListener, InterpreterFinishedListener
{

    private UsbRobotArm usbRobotArm;

    private Mode currentMode;

    private final ImageResourceBundle imageResourceBundle;

    private final LinkedList<Task> tasksList = new LinkedList<>();

    private Interpreter interpreter;

    private Instruction gripperInstruction;
    private Instruction wristInstruction;
    private Instruction elbowInstruction;
    private Instruction shoulderInstruction;
    private Instruction baseInstruction;
    
    private long pressedTime;
    private long gripperOpenTime;
    private long gripperCloseTime;
    private long wristUpTime;
    private long wristDownTime;
    private long elbowUpTime;
    private long elbowDownTime;
    private long shoulderUpTime;
    private long shoulderDownTime;
    private long baseClockwiseTime;
    private long baseAnticlockwiseTime;

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
    public ProgramMode()
    {
        initComponents();

        this.imageResourceBundle = ImageResourceBundle.getInstance(
                ImageResourceBundle.class.getResourceAsStream(
                        ImageResourceBundle.PROPERTIES_FILE));

        ImageIcon icon = new ImageIcon(this.imageResourceBundle.
                getImage("icon.application"));
        this.setIconImage(icon.getImage());

        this.tasksPanel.setLayout(new GridLayout(0, 1, 0, 0));
    }

    /**
     * Creates new form BasicMode
     *
     * @param robotArm
     */
    public ProgramMode(UsbRobotArm robotArm)
    {
        this();
        this.usbRobotArm = robotArm;
        this.usbRobotArm.addRobotArmChangeListener(this);

        this.currentMode = Mode.Mouse;

        if (this.usbRobotArm.isAttached())
        {
            this.toggleButttons(true);
            this.connectionLabel.setText("Connected");
            this.connectionLabel.setIcon(new javax.swing.ImageIcon(
                    this.imageResourceBundle.getImage("icon.plug")));
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
        this.connectionLabel.setIcon(new javax.swing.ImageIcon(
                this.imageResourceBundle.getImage("icon.plug")));
    }

    @Override
    public void robotArmDetached(RobotArmChangedEvent race)
    {
        this.toggleButttons(false);
        this.connectionLabel.setText("No Connection");
        this.connectionLabel.setIcon(new javax.swing.ImageIcon(
                this.imageResourceBundle.getImage("icon.plug_exclamation")));
    }
    
    @Override
    public void interpreterFinished(InterpreterFinishedEvent e)
    {
        this.stopButton.setEnabled(false);
        this.runButton.setEnabled(true);
        this.rewindButton.setEnabled(true);
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
     *
     */
    private void addTasks()
    {
        Task task = new Task();

        if (this.gripperInstruction != null)
        {
            task.addInstruction((Instruction) this.gripperInstruction.clone());
            this.gripperInstruction = null;
        }

        if (this.wristInstruction != null)
        {
            task.addInstruction((Instruction) this.wristInstruction.clone());
            this.wristInstruction = null;
        }

        if (this.elbowInstruction != null)
        {
            task.addInstruction((Instruction) this.elbowInstruction.clone());
            this.elbowInstruction = null;
        }

        if (this.shoulderInstruction != null)
        {
            task.addInstruction((Instruction) this.shoulderInstruction.clone());
            this.shoulderInstruction = null;
        }

        if (this.baseInstruction != null)
        {
            task.addInstruction((Instruction) this.baseInstruction.clone());
            this.baseInstruction = null;
        }

        if (task.getInstructions().isEmpty())
        {
            return;
        }

        TaskPanel panel = new TaskPanel(task);
        this.tasksPanel.add(panel);
        this.tasksPanel.validate();
        this.tasksPanel.repaint();

        this.taskScrollPane.getViewport().revalidate();
        this.tasksList.add(task);
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
        robotArmPanel = new robotarmedge.view.controls.RobotArmPanel();
        connectionLabel = new javax.swing.JLabel();
        enterButton = new javax.swing.JButton();
        programPanel = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        rewindButton = new javax.swing.JButton();
        taskScrollPane = new javax.swing.JScrollPane();
        tasksPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("robotarmedge/resources/RobotArmEdge_en"); // NOI18N
        setTitle(bundle.getString("mode.program")); // NOI18N
        setLocationByPlatform(true);
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
        closeGripperButton.setToolTipText(bundle.getString("tooltip.gripper.close")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                closeGripperButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                closeGripperButtonMouseEntered(evt);
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
        openGripperButton.setToolTipText(bundle.getString("tooltip.gripper.open")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                closeGripperButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                closeGripperButtonMouseEntered(evt);
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
        wristDownButton.setToolTipText(bundle.getString("tooltip.wrist.down")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                wristUpButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                wristUpButtonMouseEntered(evt);
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
        wristUpButton.setToolTipText(bundle.getString("tooltip.wrist.up")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                wristUpButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                wristUpButtonMouseEntered(evt);
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
        elbowUpButton.setToolTipText(bundle.getString("tooltip.elbow.up")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                elbowUpButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                elbowUpButtonMouseEntered(evt);
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
        elbowDownButton.setToolTipText(bundle.getString("tooltip.elbow.down")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                elbowUpButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                elbowUpButtonMouseEntered(evt);
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
        shoulderDownButton.setToolTipText(bundle.getString("tooltip.shoulder.down")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                shoulderUpButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                shoulderUpButtonMouseEntered(evt);
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
        shoulderUpButton.setToolTipText(bundle.getString("tooltip.shoulder.up")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                shoulderUpButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                shoulderUpButtonMouseEntered(evt);
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
        baseAntiClockwiseButton.setToolTipText(bundle.getString("tooltip.base.anticlockwise")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                baseClockwiseButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                baseClockwiseButtonMouseEntered(evt);
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
        baseClockwiseButton.setToolTipText(bundle.getString("tooltip.base.clockwise")); // NOI18N
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
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                baseClockwiseButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                baseClockwiseButtonMouseEntered(evt);
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
        basicModeLabel.setText(bundle.getString("mode.program")); // NOI18N
        basicModeLabel.setToolTipText("");
        basicModeLabel.setFocusable(false);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lightToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/light-bulb-off.png"))); // NOI18N
        lightToggleButton.setText(bundle.getString("tooltip.light.toggle")); // NOI18N
        lightToggleButton.setToolTipText(bundle.getString("tooltip.light.toggle")); // NOI18N
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
            .addGap(0, 594, Short.MAX_VALUE)
        );
        robotArmPanelLayout.setVerticalGroup(
            robotArmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        connectionLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/plug--exclamation.png"))); // NOI18N
        connectionLabel.setText(bundle.getString("connection.none")); // NOI18N
        connectionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        enterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/tick.png"))); // NOI18N
        enterButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                enterButtonActionPerformed(evt);
            }
        });

        programPanel.setBackground(java.awt.Color.lightGray);

        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/blue-document.png"))); // NOI18N
        newButton.setText(bundle.getString("control.new")); // NOI18N
        newButton.setToolTipText(bundle.getString("tooltip.control.new")); // NOI18N
        newButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newButtonActionPerformed(evt);
            }
        });

        openButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/blue-folder-open.png"))); // NOI18N
        openButton.setText(bundle.getString("control.open")); // NOI18N
        openButton.setToolTipText(bundle.getString("tooltip.control.open")); // NOI18N
        openButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                openButtonActionPerformed(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/disk.png"))); // NOI18N
        saveButton.setText(bundle.getString("control.save")); // NOI18N
        saveButton.setToolTipText(bundle.getString("tooltip.control.save")); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveButtonActionPerformed(evt);
            }
        });

        runButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/control.png"))); // NOI18N
        runButton.setText(bundle.getString("control.run")); // NOI18N
        runButton.setToolTipText(bundle.getString("tooltip.control.run")); // NOI18N
        runButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                runButtonActionPerformed(evt);
            }
        });

        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/control-stop-square.png"))); // NOI18N
        stopButton.setText(bundle.getString("control.stop")); // NOI18N
        stopButton.setToolTipText(bundle.getString("tooltip.control.stop")); // NOI18N
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                stopButtonActionPerformed(evt);
            }
        });

        rewindButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robotarmedge/resources/control-double-180.png"))); // NOI18N
        rewindButton.setText(bundle.getString("control.rewind")); // NOI18N
        rewindButton.setToolTipText(bundle.getString("tooltip.control.rewind")); // NOI18N
        rewindButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rewindButtonActionPerformed(evt);
            }
        });

        taskScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        taskScrollPane.setMaximumSize(new java.awt.Dimension(241, 464));
        taskScrollPane.setMinimumSize(new java.awt.Dimension(241, 464));
        taskScrollPane.setPreferredSize(new java.awt.Dimension(241, 464));

        tasksPanel.setMaximumSize(new java.awt.Dimension(241, 464));
        tasksPanel.setMinimumSize(new java.awt.Dimension(241, 464));
        tasksPanel.setName(""); // NOI18N

        javax.swing.GroupLayout tasksPanelLayout = new javax.swing.GroupLayout(tasksPanel);
        tasksPanel.setLayout(tasksPanelLayout);
        tasksPanelLayout.setHorizontalGroup(
            tasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );
        tasksPanelLayout.setVerticalGroup(
            tasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        taskScrollPane.setViewportView(tasksPanel);

        javax.swing.GroupLayout programPanelLayout = new javax.swing.GroupLayout(programPanel);
        programPanel.setLayout(programPanelLayout);
        programPanelLayout.setHorizontalGroup(
            programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(programPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taskScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addGroup(programPanelLayout.createSequentialGroup()
                        .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(programPanelLayout.createSequentialGroup()
                                .addComponent(newButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(openButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton))
                            .addGroup(programPanelLayout.createSequentialGroup()
                                .addComponent(runButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rewindButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        programPanelLayout.setVerticalGroup(
            programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(programPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newButton)
                    .addComponent(openButton)
                    .addComponent(saveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runButton)
                    .addComponent(stopButton)
                    .addComponent(rewindButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(basicModeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
                        .addComponent(connectionLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
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
                            .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addComponent(robotArmPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(programPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(programPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(basicModeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(connectionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(robotArmPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
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
                                .addComponent(enterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shoulderDownButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderDownButtonMouseReleased
    {//GEN-HEADEREND:event_shoulderDownButtonMouseReleased
        if (this.shoulderDownButton.isEnabled())
        {
            this.shoulderDownTime += System.currentTimeMillis() - this.pressedTime;
            
            this.shoulderInstruction = new Instruction(ByteCommand.SHOULDER_DOWN,
                    (int) (this.shoulderDownTime),
                    0);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopShoulder();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_shoulderDownButtonMouseReleased

    private void baseClockwiseButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseClockwiseButtonMousePressed
    {//GEN-HEADEREND:event_baseClockwiseButtonMousePressed
        if (this.baseClockwiseButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.baseAnticlockwiseTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.rotateBaseClockwise();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_baseClockwiseButtonMousePressed

    private void baseClockwiseButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseClockwiseButtonMouseReleased
    {//GEN-HEADEREND:event_baseClockwiseButtonMouseReleased
        if (this.baseClockwiseButton.isEnabled())
        {
            this.baseClockwiseTime += System.currentTimeMillis() - this.pressedTime;
            
            this.baseInstruction = new Instruction(ByteCommand.BASE_CLOCKWISE,
                    (int) (this.baseClockwiseTime),
                    1);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopBase();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_baseClockwiseButtonMouseReleased

    private void baseAntiClockwiseButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseAntiClockwiseButtonMousePressed
    {//GEN-HEADEREND:event_baseAntiClockwiseButtonMousePressed
        if (this.baseAntiClockwiseButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.baseClockwiseTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.rotateBaseAntiClockwise();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_baseAntiClockwiseButtonMousePressed

    private void baseAntiClockwiseButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseAntiClockwiseButtonMouseReleased
    {//GEN-HEADEREND:event_baseAntiClockwiseButtonMouseReleased
        if (this.baseAntiClockwiseButton.isEnabled())
        {
            this.baseAnticlockwiseTime += System.currentTimeMillis() - this.pressedTime;
            
            this.baseInstruction = new Instruction(ByteCommand.BASE_ANTI_CLOCKWISE,
                    (int) (this.baseAnticlockwiseTime),
                    1);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopBase();
            }

            this.robotArmPanel.repaint();
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
            this.pressedTime = System.currentTimeMillis();
            this.shoulderUpTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveShoulderDown();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_shoulderDownButtonMousePressed

    private void shoulderUpButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderUpButtonMouseReleased
    {//GEN-HEADEREND:event_shoulderUpButtonMouseReleased
        if (this.shoulderUpButton.isEnabled())
        {
            this.shoulderUpTime += System.currentTimeMillis() - this.pressedTime;
            
            this.shoulderInstruction = new Instruction(ByteCommand.SHOULDER_UP,
                    (int) (this.shoulderUpTime),
                    0);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopShoulder();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_shoulderUpButtonMouseReleased

    private void shoulderUpButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderUpButtonMousePressed
    {//GEN-HEADEREND:event_shoulderUpButtonMousePressed
        if (this.shoulderUpButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.shoulderDownTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveShoulderUp();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_shoulderUpButtonMousePressed

    private void elbowDownButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowDownButtonMouseReleased
    {//GEN-HEADEREND:event_elbowDownButtonMouseReleased
        if (this.elbowDownButton.isEnabled())
        {
            this.elbowDownTime += System.currentTimeMillis() - this.pressedTime;
            
            this.elbowInstruction = new Instruction(ByteCommand.ELBOW_DOWN,
                    (int) (this.elbowDownTime),
                    0);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopElbow();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_elbowDownButtonMouseReleased

    private void elbowDownButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowDownButtonMousePressed
    {//GEN-HEADEREND:event_elbowDownButtonMousePressed
        if (this.elbowDownButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.elbowUpTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveElbowDown();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_elbowDownButtonMousePressed

    private void elbowUpButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowUpButtonMouseReleased
    {//GEN-HEADEREND:event_elbowUpButtonMouseReleased
        if (this.elbowUpButton.isEnabled())
        {
            this.elbowUpTime += System.currentTimeMillis() - this.pressedTime;
            
            this.elbowInstruction = new Instruction(ByteCommand.ELBOW_UP,
                    (int) (this.elbowUpTime),
                    0);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopElbow();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_elbowUpButtonMouseReleased

    private void elbowUpButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowUpButtonMousePressed
    {//GEN-HEADEREND:event_elbowUpButtonMousePressed
        if (this.elbowUpButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.elbowDownTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveElbowUp();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_elbowUpButtonMousePressed

    private void wristDownButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristDownButtonMouseReleased
    {//GEN-HEADEREND:event_wristDownButtonMouseReleased
        if (this.wristDownButton.isEnabled())
        {
            this.wristDownTime += System.currentTimeMillis() - this.pressedTime;
            
            this.wristInstruction = new Instruction(ByteCommand.WRIST_DOWN,
                    (int) (this.wristDownTime),
                    0);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopWrist();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_wristDownButtonMouseReleased

    private void wristDownButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristDownButtonMousePressed
    {//GEN-HEADEREND:event_wristDownButtonMousePressed
        if (this.wristDownButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.wristUpTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveWristDown();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_wristDownButtonMousePressed

    private void wristUpButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristUpButtonMouseReleased
    {//GEN-HEADEREND:event_wristUpButtonMouseReleased
        if (this.wristUpButton.isEnabled())
        {
            this.wristUpTime += System.currentTimeMillis() - this.pressedTime;
            
            this.wristInstruction = new Instruction(ByteCommand.WRIST_UP,
                    (int) (this.wristUpTime),
                    0);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopWrist();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_wristUpButtonMouseReleased

    private void wristUpButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristUpButtonMousePressed
    {//GEN-HEADEREND:event_wristUpButtonMousePressed
        if (this.wristUpButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.wristDownTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.moveWristUp();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_wristUpButtonMousePressed

    private void openGripperButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_openGripperButtonMouseReleased
    {//GEN-HEADEREND:event_openGripperButtonMouseReleased
        if (this.openGripperButton.isEnabled())
        {
            this.gripperOpenTime += System.currentTimeMillis() - this.pressedTime;
            
            this.gripperInstruction = new Instruction(ByteCommand.GRIPPER_OPEN,
                    (int) (this.gripperOpenTime),
                    0);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopGripper();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_openGripperButtonMouseReleased

    private void openGripperButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_openGripperButtonMousePressed
    {//GEN-HEADEREND:event_openGripperButtonMousePressed
        if (this.openGripperButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.gripperCloseTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.openGripper();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_openGripperButtonMousePressed

    private void closeGripperButtonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_closeGripperButtonMouseReleased
    {//GEN-HEADEREND:event_closeGripperButtonMouseReleased
        if (this.closeGripperButton.isEnabled())
        {
            this.gripperCloseTime += System.currentTimeMillis() - this.pressedTime;
            
            this.gripperInstruction = new Instruction(ByteCommand.GRIPPER_CLOSE,
                    (int) (this.gripperCloseTime),
                    0);

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.stopGripper();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_closeGripperButtonMouseReleased

    private void closeGripperButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_closeGripperButtonMousePressed
    {//GEN-HEADEREND:event_closeGripperButtonMousePressed
        if (this.closeGripperButton.isEnabled())
        {
            this.pressedTime = System.currentTimeMillis();
            this.gripperOpenTime = 0;

            if (this.usbRobotArm.isAttached())
            {
                this.usbRobotArm.closeGripper();
            }

            this.robotArmPanel.repaint();
        }
    }//GEN-LAST:event_closeGripperButtonMousePressed

    private void componentKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_componentKeyPressed
    {//GEN-HEADEREND:event_componentKeyPressed
        if (tt != null)
        {
            return;
        }

        tt = new TimerTask()
        {
            @Override
            public void run()
            {
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

    private void closeGripperButtonMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_closeGripperButtonMouseEntered
    {//GEN-HEADEREND:event_closeGripperButtonMouseEntered
        this.robotArmPanel.setGripperHovered(true);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_closeGripperButtonMouseEntered

    private void closeGripperButtonMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_closeGripperButtonMouseExited
    {//GEN-HEADEREND:event_closeGripperButtonMouseExited
        this.robotArmPanel.setGripperHovered(false);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_closeGripperButtonMouseExited

    private void wristUpButtonMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristUpButtonMouseEntered
    {//GEN-HEADEREND:event_wristUpButtonMouseEntered
        this.robotArmPanel.setWristHovered(true);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_wristUpButtonMouseEntered

    private void wristUpButtonMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wristUpButtonMouseExited
    {//GEN-HEADEREND:event_wristUpButtonMouseExited
        this.robotArmPanel.setWristHovered(false);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_wristUpButtonMouseExited

    private void elbowUpButtonMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowUpButtonMouseEntered
    {//GEN-HEADEREND:event_elbowUpButtonMouseEntered
        this.robotArmPanel.setElbowHovered(true);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_elbowUpButtonMouseEntered

    private void elbowUpButtonMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_elbowUpButtonMouseExited
    {//GEN-HEADEREND:event_elbowUpButtonMouseExited
        this.robotArmPanel.setElbowHovered(false);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_elbowUpButtonMouseExited

    private void shoulderUpButtonMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderUpButtonMouseEntered
    {//GEN-HEADEREND:event_shoulderUpButtonMouseEntered
        this.robotArmPanel.setShoulderHovered(true);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_shoulderUpButtonMouseEntered

    private void shoulderUpButtonMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_shoulderUpButtonMouseExited
    {//GEN-HEADEREND:event_shoulderUpButtonMouseExited
        this.robotArmPanel.setShoulderHovered(false);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_shoulderUpButtonMouseExited

    private void baseClockwiseButtonMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseClockwiseButtonMouseEntered
    {//GEN-HEADEREND:event_baseClockwiseButtonMouseEntered
        this.robotArmPanel.setBaseHovered(true);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_baseClockwiseButtonMouseEntered

    private void baseClockwiseButtonMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_baseClockwiseButtonMouseExited
    {//GEN-HEADEREND:event_baseClockwiseButtonMouseExited
        this.robotArmPanel.setBaseHovered(false);
        this.robotArmPanel.repaint();
    }//GEN-LAST:event_baseClockwiseButtonMouseExited

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_enterButtonActionPerformed
    {//GEN-HEADEREND:event_enterButtonActionPerformed
        this.gripperOpenTime = 0;
        this.gripperCloseTime = 0;
        this.wristUpTime = 0;
        this.wristDownTime = 0;
        this.elbowUpTime = 0;
        this.elbowDownTime = 0;
        this.shoulderUpTime = 0;
        this.shoulderDownTime = 0;
        this.baseClockwiseTime = 0;
        this.baseAnticlockwiseTime = 0;
        this.addTasks();
    }//GEN-LAST:event_enterButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newButtonActionPerformed
    {//GEN-HEADEREND:event_newButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newButtonActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_openButtonActionPerformed
    {//GEN-HEADEREND:event_openButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveButtonActionPerformed
    {//GEN-HEADEREND:event_saveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runButtonActionPerformed
    {//GEN-HEADEREND:event_runButtonActionPerformed
        if (!this.tasksList.isEmpty())
        {
            this.runButton.setEnabled(false);
            this.rewindButton.setEnabled(false);
            this.stopButton.setEnabled(true);
            
            this.interpreter = new Interpreter(this.tasksList);
            this.interpreter.addInterpreterFinishedListener(this);
            this.interpreter.start();
        }
    }//GEN-LAST:event_runButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_stopButtonActionPerformed
    {//GEN-HEADEREND:event_stopButtonActionPerformed
        if (this.interpreter != null)
        {
            this.interpreter.shutdown();

            if (this.interpreter.getState() == Thread.State.TIMED_WAITING)
            {
                this.interpreter.interrupt();
            }
            
            this.stopButton.setEnabled(false);
            this.runButton.setEnabled(true);
            this.rewindButton.setEnabled(true);
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void rewindButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rewindButtonActionPerformed
    {//GEN-HEADEREND:event_rewindButtonActionPerformed
        if (!this.tasksList.isEmpty())
        {
            this.rewindButton.setEnabled(false);
            this.runButton.setEnabled(false);
            this.stopButton.setEnabled(true);
            
             LinkedList<Task> reversedList = new LinkedList<>();
            
             for (Task task : this.tasksList)
             {
                 Task clonedTask = new Task();
                 
                 for (Instruction instruction : task.getInstructions())
                 {
                     Instruction clonedInstruction = (Instruction)instruction.clone();
                     clonedInstruction.reverseCommand();
                     
                     clonedTask.addInstruction(clonedInstruction);
                 }
                 
                 reversedList.addFirst(clonedTask);
             }
             
            this.interpreter = new Interpreter(reversedList);
            this.interpreter.addInterpreterFinishedListener(this);
            this.interpreter.start();
        }
    }//GEN-LAST:event_rewindButtonActionPerformed

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
                new ProgramMode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baseAntiClockwiseButton;
    private javax.swing.JButton baseClockwiseButton;
    private javax.swing.JLabel basicModeLabel;
    private javax.swing.JButton closeGripperButton;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JButton elbowDownButton;
    private javax.swing.JButton elbowUpButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JToggleButton lightToggleButton;
    private javax.swing.JButton newButton;
    private javax.swing.JButton openButton;
    private javax.swing.JButton openGripperButton;
    private javax.swing.JPanel programPanel;
    private javax.swing.JButton rewindButton;
    private robotarmedge.view.controls.RobotArmPanel robotArmPanel;
    private javax.swing.JButton runButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton shoulderDownButton;
    private javax.swing.JButton shoulderUpButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JScrollPane taskScrollPane;
    private javax.swing.JPanel tasksPanel;
    private javax.swing.JButton wristDownButton;
    private javax.swing.JButton wristUpButton;
    // End of variables declaration//GEN-END:variables

}
