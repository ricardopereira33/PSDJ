/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Presentation;

import calendar.Interfaces.Options;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author dinispeixoto
 */
public class OptionsInterface extends javax.swing.JFrame {

    Options optionsMode;
    
    /**
     * Creates new form Options
     */
    public OptionsInterface(Options optionsMode) {
        this.optionsMode = optionsMode;
        initComponents();
        dateFormatTextField.setText(optionsMode.getDateFormat());
        timeFormatTextField.setText(optionsMode.getTimeFormat());
        updateTimer(timerLabel);
        updateDurationFields();
    }
    
    public void updateDurationFields(){
        List<Integer> fields = optionsMode.getDurationFormat();
        if(fields.get(0) == 1) dayCheckBox.setSelected(true);
        if(fields.get(1) == 1) hourCheckBox.setSelected(true);
        if(fields.get(2) == 1) minuteCheckBox.setSelected(true);
        if(fields.get(3) == 1) secondCheckBox.setSelected(true);
        if(fields.get(4) == 1) miliCheckBox.setSelected(true);
        if(fields.get(5) == 1) nanoCheckBox.setSelected(true);
    }

    public void updateTimer(JLabel label){
        int delay = 1000; //milliseconds
        DateTimeFormatter format = DateTimeFormatter.ofPattern(optionsMode.getDateFormat()+" "+optionsMode.getTimeFormat());

        ActionListener taskPerformer = new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            String datetime = LocalDateTime.now().format(format);
            label.setText(datetime);
          }
        };
        new Timer(delay, taskPerformer).start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dateComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        timeComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dateFormatTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        timeFormatTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dayCheckBox = new javax.swing.JCheckBox();
        hourCheckBox = new javax.swing.JCheckBox();
        minuteCheckBox = new javax.swing.JCheckBox();
        secondCheckBox = new javax.swing.JCheckBox();
        miliCheckBox = new javax.swing.JCheckBox();
        nanoCheckBox = new javax.swing.JCheckBox();
        timerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Phosphate", 1, 45)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OPTions");

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Date format");

        dateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EEE dd-MM-yyyy", "EEE dd-MMM-yyyy", "EEE MM-dd-yyyy", "EEE yyyy-MM-dd", "dd-MM-yyyy", "dd-MMM-yyyy", "MM-dd-yyyy", "MMM-dd-yyyy", "yyyy-MM-dd" }));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Time format");

        timeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HH:mm:ss", "hh:mm:ss a", "HH:mm:ss.SSS", "hh:mm:ss.SSS a" }));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("Custom Date format");

        dateFormatTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFormatTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel5.setText("Custom Time format");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Choose a format that you would like to see on dates, ");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        jLabel7.setText("times and durations. Note that if you change the custom");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("field for a format, it will be the chosen one.");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel9.setText("Duration format");

        dayCheckBox.setText("Days");
        dayCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayCheckBoxActionPerformed(evt);
            }
        });

        hourCheckBox.setText("Hours");

        minuteCheckBox.setText("Minutes");

        secondCheckBox.setText("Seconds");

        miliCheckBox.setText("Milliseconds");

        nanoCheckBox.setText("Nanoseconds");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(timeComboBox, 0, 174, Short.MAX_VALUE)
                                    .addComponent(dateFormatTextField)
                                    .addComponent(timeFormatTextField, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dayCheckBox)
                            .addComponent(secondCheckBox))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(miliCheckBox)
                            .addComponent(hourCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minuteCheckBox)
                            .addComponent(nanoCheckBox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dateFormatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(timeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(timeFormatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayCheckBox)
                    .addComponent(hourCheckBox)
                    .addComponent(minuteCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(secondCheckBox)
                    .addComponent(miliCheckBox)
                    .addComponent(nanoCheckBox))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        timerLabel.setText("DateTime");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(timerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(timerLabel))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String dateFormat, timeFormat;
        if(dateFormatTextField.getText().equals(optionsMode.getDateFormat()))
            dateFormat = dateComboBox.getSelectedItem().toString();
        else
            dateFormat = dateFormatTextField.getText();
        
        if(timeFormatTextField.getText().equals(optionsMode.getTimeFormat()))
            timeFormat = timeComboBox.getSelectedItem().toString();
        else
            timeFormat = timeFormatTextField.getText();
        
        List<Integer> durationFormat = new ArrayList<Integer>(6);
        durationFormat.add(0, (dayCheckBox.isSelected()) ? 1:0);
        durationFormat.add(1, (hourCheckBox.isSelected() ? 1:0));
        durationFormat.add(2, (minuteCheckBox.isSelected()? 1:0));
        durationFormat.add(3, (secondCheckBox.isSelected()? 1:0));
        durationFormat.add(4, (miliCheckBox.isSelected()? 1:0));
        durationFormat.add(5, (nanoCheckBox.isSelected()? 1:0));
        
        optionsMode.setDateFormat(dateFormat);
        optionsMode.setTimeFormat(timeFormat);
        optionsMode.setDurationFormat(durationFormat);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dateFormatTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFormatTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFormatTextFieldActionPerformed

    private void dayCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayCheckBoxActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> dateComboBox;
    private javax.swing.JTextField dateFormatTextField;
    private javax.swing.JCheckBox dayCheckBox;
    private javax.swing.JCheckBox hourCheckBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox miliCheckBox;
    private javax.swing.JCheckBox minuteCheckBox;
    private javax.swing.JCheckBox nanoCheckBox;
    private javax.swing.JCheckBox secondCheckBox;
    private javax.swing.JComboBox<String> timeComboBox;
    private javax.swing.JTextField timeFormatTextField;
    private javax.swing.JLabel timerLabel;
    // End of variables declaration//GEN-END:variables
}
