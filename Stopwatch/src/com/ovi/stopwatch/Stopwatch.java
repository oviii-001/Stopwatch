package com.ovi.stopwatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch {
    private JFrame frame;
    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0;  //in milliseconds
    private boolean isRunning = false;

    public Stopwatch() {
        //frame
        frame = new JFrame("Stopwatch");
        frame.setSize(340, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        //time label
        timeLabel = new JLabel(formatTime(0), SwingConstants.CENTER);
        timeLabel.setBounds(50, 20, 200, 50);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(timeLabel);

        //start button
        startButton = new JButton("Start");
        startButton.setBounds(30, 100, 80, 30);
        frame.add(startButton);

        //stop button
        stopButton = new JButton("Stop");
        stopButton.setBounds(120, 100, 80, 30);
        frame.add(stopButton);

        //reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(210, 100, 80, 30);
        frame.add(resetButton);

        //timer setup (update every 100ms)
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 100;
                timeLabel.setText(formatTime(elapsedTime));
            }
        });
        
        //listener -> start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    timer.start();
                    isRunning = true;
                }
            }
        });

      //listener -> stop button
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    timer.stop();
                    isRunning = false;
                }
            }
        });

      //listener -> reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                isRunning = false;
                elapsedTime = 0;
                timeLabel.setText(formatTime(elapsedTime));
            }
        });

        frame.setVisible(true);
    }

    //time format (mm:ss:ms)
    private String formatTime(int time) {
        int minutes = (time / 60000);
        int seconds = (time / 1000) % 60;
        int milliseconds = (time % 1000) / 100;
        return String.format("%02d:%02d:%01d", minutes, seconds, milliseconds);
    }

    public static void main(String[] args) {
        new Stopwatch();
    }
}
