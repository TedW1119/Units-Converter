package com.tedw;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

public class Converter {
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton convertButton;
    private JButton lengthButton;
    private JButton weightButton;
    private JButton temperatureButton;
    private JButton resetButton;
    private JLabel label1;

    private static HashMap<String, Double> lengthMap;
    private static HashMap<String, Double> weightMap;

    String[] lengthUnits = {"Centimeter", "Meter", "Kilometer", "Micrometer", "Nanometer", "Mile", "Foot", "Inch", "Yard"};
    String[] weightUnits = {"Gram", "Kilogram", "Ton", "Milligram", "Pound", "Ounce"};
    String[] tempUnits = {"Celsius", "Fahrenheit", "Kelvin"};

    double result;

    public Converter() {
        // HashMap for length unit conversion where the values represent
        // the conversion ratio in terms of Meter
        lengthMap = new HashMap<String, Double>();
        lengthMap.put("Centimeter", 100.0);
        lengthMap.put("Meter", 1.0);
        lengthMap.put("Kilometer", 0.001);
        lengthMap.put("Micrometer", 1000000.0);
        lengthMap.put("Nanometer", 1000000000.0);
        lengthMap.put("Mile", 0.000621371);
        lengthMap.put("Foot", 3.280839895);
        lengthMap.put("Inch", 39.370079);
        lengthMap.put("Yard", 1.0936132983);

        // HashMap for weight unit conversion where the values represent
        // the conversion ratio in terms of Gram
        weightMap = new HashMap<String, Double>();
        weightMap.put("Gram", 1.0);
        weightMap.put("Kilogram", 0.001);
        weightMap.put("Ton", 0.000001);
        weightMap.put("Milligram", 1000.0);
        weightMap.put("Pound", 0.0022046244);
        weightMap.put("Ounce", 0.0352739907);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String combo1 = (String) comboBox1.getSelectedItem();
                String combo2 = (String) comboBox2.getSelectedItem();
                if (isNumeric(textField1.getText())) {
                    result = Double.parseDouble(textField1.getText());
                    if (Arrays.asList(lengthUnits).contains(combo1))
                        result = meterToAny(combo2, anyToMeter(combo1, result));
                    if (Arrays.asList(weightUnits).contains(combo1))
                        result = gramToAny(combo2, anyToGram(combo1, result));
                    if (Arrays.asList(tempUnits).contains(combo1))
                        result = tempToTemp(combo1, combo2, result);
                    label1.setText(String.valueOf(result));
                }
                else
                    label1.setText("Invalid input");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                label1.setText("");
            }
        });

        lengthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComboItem(comboBox1, comboBox2, lengthUnits);
            }
        });

        weightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComboItem(comboBox1, comboBox2, weightUnits);
            }
        });

        temperatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComboItem(comboBox1, comboBox2, tempUnits);
            }
        });
    }

    public static void addComboItem(JComboBox combo1, JComboBox combo2, String[] units) {
        combo1.removeAllItems();
        for (String x : units)
            combo1.addItem(x);
        combo2.removeAllItems();
        for (String y : units)
            combo2.addItem(y);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static double anyToMeter(String c1, Double result) {
        if (c1.equals("Centimeter"))
            result /= lengthMap.get("Centimeter");
        if (c1.equals("Kilometer"))
            result /= lengthMap.get("Kilometer");
        if (c1.equals("Micrometer"))
            result /= lengthMap.get("Micrometer");
        if (c1.equals("Nanometer"))
            result /= lengthMap.get("Nanometer");
        if (c1.equals("Mile"))
            result /= lengthMap.get("Mile");
        if (c1.equals("Foot"))
            result /= lengthMap.get("Foot");
        if (c1.equals("Inch"))
            result /= lengthMap.get("Inch");
        if (c1.equals("Yard"))
            result /= lengthMap.get("Yard");
        return result;
    }

    public static double meterToAny(String c2, Double result) {
        if (c2.equals("Centimeter"))
            result *= lengthMap.get("Centimeter");
        if (c2.equals("Kilometer"))
            result *= lengthMap.get("Kilometer");
        if (c2.equals("Micrometer"))
            result /= lengthMap.get("Micrometer");
        if (c2.equals("Nanometer"))
            result /= lengthMap.get("Nanometer");
        if (c2.equals("Mile"))
            result *= lengthMap.get("Mile");
        if (c2.equals("Foot"))
            result *= lengthMap.get("Foot");
        if (c2.equals("Inch"))
            result *= lengthMap.get("Inch");
        if (c2.equals("Yard"))
            result *= lengthMap.get("Yard");
        return result;
    }

    public static double anyToGram(String c1, Double result) {
        if (c1.equals("Kilogram"))
            result /= weightMap.get("Kilogram");
        if (c1.equals("Ton"))
            result /= weightMap.get("Ton");
        if (c1.equals("Milligram"))
            result /= weightMap.get("Milligram");
        if (c1.equals("Pound"))
            result /= weightMap.get("Pound");
        if (c1.equals("Ounce"))
            result /= weightMap.get("Ounce");
        return result;
    }

    public static double gramToAny(String c2, Double result) {
        if (c2.equals("Kilogram"))
            result *= weightMap.get("Kilogram");
        if (c2.equals("Ton"))
            result *= weightMap.get("Ton");
        if (c2.equals("Milligram"))
            result *= weightMap.get("Milligram");
        if (c2.equals("Pound"))
            result *= weightMap.get("Pound");
        if (c2.equals("Ounce"))
            result *= weightMap.get("Ounce");
        return result;
    }

    public static double tempToTemp(String c1, String c2, Double result) {
        if (c1.equals("Celsius") && c2.equals("Fahrenheit"))
            result = result * 9/5 + 32;
        if (c1.equals("Celsius") && c2.equals("Kelvin"))
            result += 273.15;
        if (c1.equals("Fahrenheit") && c2.equals("Celsius"))
            result = (result - 32) * 5/9;
        if (c1.equals("Fahrenheit") && c2.equals("Kelvin"))
            result = (result - 32) * 5/9 + 273.15;
        if (c1.equals("Kelvin") && c2.equals("Celsius"))
            result -= 273.15;
        if (c1.equals("Kelvin") && c2.equals("Fahrenheit"))
            result = (result - 273.15) * 9/5 + 32;
        return result;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Converter");
        frame.setContentPane(new Converter().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
