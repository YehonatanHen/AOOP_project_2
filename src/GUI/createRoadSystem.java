package GUI;

import components.Driving;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class implement "create road system" dialog
 */
public class createRoadSystem extends JFrame implements ActionListener {
    static final int FPS_MIN1 = 3;
    static final int FPS_MIN2 = 0;
    static final int FPS_MAX1 = 20;
    static final int FPS_MAX2 = 50;
    static final int FPS_INIT1 = 5;
    static final int FPS_INIT2 = 4;
    JSlider numOfJuncs,numOfVehicles;
    JPanel frame;
    mainFrame mainFrame;
    panel p;
    JLabel text1,text2;
    JButton okBtn,cancelBtn;
    Driving d;

    /**
     * create road system constructor
     * @param title
     * @param mainPanel
     * @param mainFrame
     */
    public createRoadSystem(String title,panel mainPanel,mainFrame mainFrame) {
        super(title);
        numOfJuncs=new JSlider(JSlider.HORIZONTAL,FPS_MIN1,FPS_MAX1,FPS_INIT1);
        numOfJuncs.setMajorTickSpacing(1);
        numOfJuncs.setPaintTicks(true);
        numOfJuncs.setPaintLabels(true);
        numOfVehicles=new JSlider(JSlider.HORIZONTAL,FPS_MIN2,FPS_MAX2,FPS_INIT2);
        numOfVehicles.setMajorTickSpacing(5);
        numOfVehicles.setMinorTickSpacing(1);
        numOfVehicles.setPaintTicks(true);
        numOfVehicles.setPaintLabels(true);
        frame =new JPanel();
        frame.setLayout(new GridLayout(5,1));
        okBtn=new JButton("OK");
        okBtn.addActionListener(this);
        cancelBtn=new JButton("Cancel");
        cancelBtn.addActionListener(this);
        JPanel bottom=new JPanel();
        bottom.setLayout( new GridLayout(1,2,10,0));
        text1=new JLabel("Number of junctions",SwingConstants.CENTER);
        text2=new JLabel("Number of vehicles",SwingConstants.CENTER);
        frame.add(text1);
        frame.add(numOfJuncs);
        frame.add(text2);
        frame.add(numOfVehicles);
        bottom.add(okBtn);
        bottom.add(cancelBtn);
        frame.add(bottom);
        add(frame);
        p=mainPanel;
        this.mainFrame=mainFrame;
    }

    /**
     * Perform actions.
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==okBtn){
            d=new Driving(numOfJuncs.getValue(),numOfVehicles.getValue(),mainFrame);
            p=new panel(mainFrame);
            p.setDriving(d);
            mainFrame.setMainPanel(p);
            mainFrame.repaint();
            dispose();
        }
        if(e.getSource()==cancelBtn){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();
        }
    }
}