/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carparkmanager;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author Andrew
 */
public class CarParkManager {

    public static void main(String[] args) {
        JFrame myF = new JFrame();
        Overview ov = new Overview();
        myF.setSize(1200, 900);
        myF.setResizable(true);
        myF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myF.setVisible(true);
        myF.add(ov);
          
    }
    
}
