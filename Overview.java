package carparkmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Andrew
 */
public final class Overview extends JPanel implements ActionListener, MouseListener {

    //car parking space label array
    JLabel[] shortParkingSpace;
    //lorry and coach parking space
    JLabel[] longParkingSpace;
    //create new JPanel for parking spaces
    JPanel shortParkingSpacePanel;
    JPanel menuPanel;
    JPanel ctrlPanel;
    JPanel btnPanel;

    //create size for parking spaces
    Dimension shortLabelSize;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editVehiclesMenu;
    JMenuItem newItem;
    JMenuItem saveItem;
    JMenuItem addCarItem;
    JMenuItem removeCarItem;
    JLabel displayTotal;

    JButton addCarBtn;
    JButton removeCarBtn;
    JButton resetBtn;
    JButton updateChargeBtn;

    JTextField inputParkingCharge;

    Vehicle v;
    Car c;
    Revenue r;

    ImageIcon carImg;

    Overview() {
        r = new Revenue();
        //initialise parking panel
        shortParkingSpacePanel = new JPanel();
        menuPanel = new JPanel();
        ctrlPanel = new JPanel();
        btnPanel = new JPanel();
        carImg = new ImageIcon("images\\car.png");
        v = new Vehicle();
        c = new Car();

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editVehiclesMenu = new JMenu("Edit vehicles");
        newItem = new JMenuItem("New");
        saveItem = new JMenuItem("Save");
        addCarItem = new JMenuItem("Add car");
        removeCarItem = new JMenuItem("Remove car");
        r.setCost(3.3);
        displayTotal = new JLabel("Total: " + revenueToString());

        inputParkingCharge = new JTextField();

        //initialise buttons
        addCarBtn = new JButton("add car");
        removeCarBtn = new JButton("remove car");
        resetBtn = new JButton("reset");
        updateChargeBtn = new JButton("update cost");
        //set layout and add buttons
        btnPanel.setLayout(new GridLayout(2, 8));
        btnPanel.add(addCarBtn);
        btnPanel.add(removeCarBtn);
        btnPanel.add(resetBtn);
        btnPanel.add(inputParkingCharge);
        btnPanel.add(updateChargeBtn);
        //add file menu
        menuBar.add(fileMenu);
        menuBar.add(editVehiclesMenu);
        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        editVehiclesMenu.add(addCarItem);
        editVehiclesMenu.add(removeCarItem);

        menuPanel.add(menuBar);
        //declare arrays
        shortParkingSpace = new JLabel[48];
        longParkingSpace = new JLabel[6];
        //set size for parking spaces
        shortLabelSize = new Dimension(120, 90);
        //add parking space panel to main panel
        this.add(menuPanel);
        this.add(shortParkingSpacePanel);
        this.add(btnPanel);
        this.add(ctrlPanel);

        //create labels by populating array and add properties
        for (int i = 0; i < shortParkingSpace.length; i++) {
            //populate array
            shortParkingSpace[i] = new JLabel();

            //set colour to black
            shortParkingSpace[i].setBackground(Color.BLACK);
            //set opaque
            shortParkingSpace[i].setOpaque(true);
            //make visible
            shortParkingSpace[i].setVisible(true);

            //set size
            shortParkingSpace[i].setPreferredSize(shortLabelSize);
            //this.add(shortParkingSpace[i]);

            String s = "Parking Space";

            shortParkingSpace[i].setText(s);

        }
        //add parking spaces
        for (int i = 0; i < shortParkingSpace.length; i++) {
            shortParkingSpacePanel.add(shortParkingSpace[i]);
            shortParkingSpace[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
            shortParkingSpace[i].addMouseListener(this);
        }
//        for(int j=0;j<longParkingSpace.length;j++){
//            
//            
//        }
        //set layout with a 90 pixel horizontal gap
        shortParkingSpacePanel.setLayout(new GridLayout(8, 12, 90, 0));

        ctrlPanel.add(displayTotal);

        addCarItem.addActionListener(this);
        addCarBtn.addActionListener(this);
        removeCarItem.addActionListener(this);
        removeCarBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        inputParkingCharge.addActionListener(this);
        updateChargeBtn.addActionListener(this);
    }

    public void mouseClicked(MouseEvent me) {

        for (int i = 0; i < shortParkingSpace.length; i++) {
            if (me.getSource().equals(shortParkingSpace[i])) {
                shortParkingSpace[i].setIcon(carImg);
                countNumberOfCars();
                displayTotal.setText(revenueToString());
            }
        }
    }

    public void mousePressed(MouseEvent me) {

    }

    public void mouseReleased(MouseEvent me) {

    }

    public void mouseEntered(MouseEvent me) {

    }

    public void mouseExited(MouseEvent me) {
        //revenueToString();
    }

    public void actionPerformed(ActionEvent ae) {

        try {
            if (ae.getSource().equals(addCarItem) || ae.getSource().equals(addCarBtn)) {
                checkParkFull();
                setCarIcon();
                countNumberOfCars();
                r.getNumCars();
                displayTotal.setText(revenueToString());
            }
            if (ae.getSource().equals(removeCarItem) || ae.getSource().equals(removeCarBtn)) {

                if (emptySpaceNo() > 0) {
                    int getLastFullSpace = emptySpaceNo() - 1;
                    shortParkingSpace[getLastFullSpace].setIcon(null);
                    countNumberOfCars();
                    r.getNumCars();
                    displayTotal.setText(revenueToString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ae.getSource().equals(resetBtn)) {
            for (int i = 0; i < shortParkingSpace.length; i++) {
                r.setAddTotal(0);
                r.setNumCars(0);
                displayTotal.setText("£0.00");
                shortParkingSpace[i].setIcon(null);
            }
        }
        if (ae.getSource().equals(updateChargeBtn)) {
            String s = inputParkingCharge.getText();
            double inputCharge = Double.parseDouble(s);
            r.setCost(inputCharge);
        }
    }

    //find an car empty parking space and return its number
    public int emptySpaceNo() {

        for (int i = 0; i < shortParkingSpace.length; i++) {

            if (shortParkingSpace[i].getIcon() == null) {
                return i;

            }
        }
        return -1;
    }

    //return JLabel that is empty
    JLabel findEmptySpace() {
        return shortParkingSpace[emptySpaceNo()];
    }

    //set car icon in empty parking space
    public void setCarIcon() {
        //add public car icon
        findEmptySpace().setIcon(carImg);
    }

    //check to see if car park is full
    public void checkParkFull() {
        for (int i = 0; i < shortParkingSpace.length; i++) {
            if (shortParkingSpace[i].getIcon() == null) {
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Sorry the car park is full!");
    }

    public int countNumberOfCars() {
        int countCars = 0;
        for (int i = 0; i < shortParkingSpace.length; i++) {
            if (shortParkingSpace[i].getIcon() == carImg) {

                countCars++;

            }
        }
        r.setNumCars(countCars);
        r.getCost();
        System.out.println(revenueToString());
        return countCars;
    }

    public double addRevenue() {
        r.getCost();
        double revenue = r.getAddTotal();
        return revenue;

    }

    public String revenueToString() {

        DecimalFormat df = new DecimalFormat("£0.00");
        String s = df.format(addRevenue());
        System.out.println(s);
        return s;
    }

}
