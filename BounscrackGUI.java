/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.littlepasscrack;

/**
 *
 * @author Amina Ferra
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BounscrackGUI {
    private JTextField uField;
    private JPasswordField pField;
    private JTextArea oArea;

    public BounscrackGUI() {
        JFrame f = new JFrame("Little Password Cracker");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        f.getContentPane().setBackground(new Color(243, 229, 245));

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 1, 10, 10));
        p.setBackground(new Color(209, 196, 233));

        JLabel uLabel = new JLabel("Enter Username:");
        uField = new JTextField(15);
        JLabel pLabel = new JLabel("Enter Password:");
        pField = new JPasswordField(15);
        JButton sButton = new JButton("Start Attack");
        sButton.setBackground(new Color(123, 31, 162));
        sButton.setForeground(Color.WHITE);
        oArea = new JTextArea(10, 30);
        oArea.setEditable(false);
        oArea.setBackground(new Color(225, 190, 231));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(new Color(209, 196, 233));
        btnPanel.add(sButton);

        p.add(uLabel);
        p.add(uField);
        p.add(pLabel);
        p.add(pField);
        p.add(btnPanel);

        f.add(p, BorderLayout.NORTH);
        f.add(new JScrollPane(oArea), BorderLayout.CENTER);
        
        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAttack();
            }
        });

        f.setVisible(true);
    }

    private void startAttack() {
        String u = uField.getText();
        String p = new String(pField.getPassword());
        
        if (!Attacks.validateUser(u, p)) {
            oArea.setText("Invalid username or password!\n");
            return;
        }

        oArea.setText("Attempting dictionary attack...\n");
        String dResult = Attacks.dAttack();
        if (!dResult.isEmpty()) {
            oArea.append(dResult + "");
        } else {
            oArea.append("Dictionary attack failed. Attempting brute force attack...\n");
            String bfResult = Attacks.bfAttack();
            if (!bfResult.isEmpty()) {
                oArea.append(bfResult + "\n");
            } else {
                oArea.append("Brute force attack failed.\n");
            }
        }
    }
}