# H1 Robot Arm Edge



## H2 Installation

Not matter what Operating System your using you will need to ensure that you have **Java 1.7 or above**, at the time of writing my version is
*OpenJDK Runtime Environment (IcedTea 2.4.4) (7u51-2.4.4-0ubuntu0.13.10.1)*, it will not work with older versions.

### H3 Linux

So far the software has tested successfully on Ubuntu 12.04 (32bit) with Orcale Java 1.7 and Ubuntu 13.10 (64bit) with OpenJDK Java 1.7.
It has been tested on the Raspberry Pi's Raspbian but there are some issues with *usb4java* that need to be resolved.

To be able to access the Robot Arm on the USB interface you will need to create the file *99-userusbdevices.rules* as **root** in the */lib/udev/rules.d* directory with the following contents:

```
SUBSYSTEM=="usb",ATTR{idVendor}=="1267",ATTR{idProduct}=="0000",MODE="0660",GROUP="plugdev"
```

Ensure that your user account belongs to the *plugdev* group, then restart udev in the terminal with */etc/init.d/udev restart* as **root**.

After which either double click on the RobotArmEdge.jar or run it from the terminal using *java -jar RobotArmEdge.jar*, ensure that the coressponding *lib* folder containing all of the dependencies is in the same path.

### H3 Windows

Not yet tested.

### H3 MAC OSX

Not yet tested. If you have a OSX System please submit your results.

## H2 Implementation

### H3 Modes

* Basic Mode
* Program Mode

### H3 Available Features

* Full communication to the Robot Arm via the USB interface including all motors and lights.
* Button based control of the Robot Arm in both Basic and Program mode.
* Connectivity awareness, it knows whether or not the Robot Arm is connected/disconnected.
* Programmable interface to the Robot Arm via timing functions.
* The ability to run, stop, and rewind programs.
* Creation and deletion of instructions and tasks in ProgramMode.

### H3 What Needs Implementing

* Programs cannot be saved or opened.
* Keyboard control is currently not supported.
* Insertion/Movement of instructions and tasks.

## H2 Development



## H2 Special Thanks


