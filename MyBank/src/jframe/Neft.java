/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author RAVI PANCHAL
 */
public class Neft extends javax.swing.JFrame {

    /**
     * Creates new form Deposit
     */
    public Neft() {
        initComponents();
        showAcc();
    }
    public void showAcc()
    {
        String acc = SignupPage.acc;
        txt_account.setText(acc);
    }
    //deposit or credit money here
    public void Neft()
    {
        String acc = txt_account.getText();
        int t_amt = Integer.parseInt(txt_amount.getText());
        String r_id = txt_receiverid.getText();
        int pwd = Integer.parseInt(txt_password.getText());
        
        String[] type = {"Dr.","Cr."};
        String t_type = "NEFT";
        int new_bal;
        try
        {
            Connection con = Dbconnection.getConnection();
            String sql = "select * from bank2 where no=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,acc);
            
            ResultSet rs = pst.executeQuery();
            rs.next();
            int oldbal = rs.getInt(3);
            int pw = rs.getInt(4);
            if(pwd==pw)
            {
                 //set new bal
                new_bal = oldbal - t_amt;
                //update new bal
                String sql1 = "update bank2 set bal=? where no = ?";
                PreparedStatement pst1 = con.prepareStatement(sql1);
                pst1.setInt(1,new_bal);
                pst1.setString(2,acc);
                int updateRowCount = pst1.executeUpdate();
                
                if(updateRowCount>0)
                {
                    //for add entry into transaction history for acc holder
                    String t_sql = "insert into bank_tran values(?,?,?,?,?)";
                    PreparedStatement t_pst = con.prepareStatement(t_sql);
                    t_pst.setString(1,acc);
                    t_pst.setString(2,r_id);
                    t_pst.setInt(3,t_amt);
                    t_pst.setString(4,type[0]);
                    t_pst.setString(5,t_type);
                    
                    t_pst.executeUpdate();
                    
                    /*for receiver transaction // account update*/
                    String sql2 = "select * from bank2 where no=?";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    pst2.setString(1,r_id);

                    ResultSet rs2 = pst.executeQuery();
                    rs2.next();
                    int r_oldbal = rs2.getInt(3);
                    //set new bal
                    int r_new_bal = r_oldbal + t_amt;
                    //update new bal
                    String sql3 = "update bank2 set bal=? where no = ?";
                    PreparedStatement pst3 = con.prepareStatement(sql3);
                    pst3.setInt(1,r_new_bal);
                    pst3.setString(2,r_id);
                    int updateRowCount2 = pst3.executeUpdate();
                    if(updateRowCount2>0)
                    {
                        JOptionPane.showMessageDialog(this,"Transfer Succesfully !");
                        //for add entry into transaction history for acc holder
                        String t_sql1 = "insert into bank_tran values(?,?,?,?,?)";
                        PreparedStatement t_pst1 = con.prepareStatement(t_sql1);
                        t_pst1.setString(1,r_id);
                        t_pst1.setString(2,acc);
                        t_pst1.setInt(3,t_amt);
                        t_pst1.setString(4,type[1]);
                        t_pst1.setString(5,t_type);
                        
                        t_pst1.executeUpdate();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Neft Fail !!");
                } 
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Password is incorrect !");
            }
            //JOptionPane.showMessageDialog(this,new_bal);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public Boolean checkField()
    {
        String t_amt = txt_amount.getText();
        String r_id = txt_receiverid.getText();
        String pwd = txt_password.getText();
        if(r_id.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please Enter Receiver Acc No");
            return false;
        }
        else if(t_amt.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please Enter Amount");
            return false;
        }
        else if(pwd.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please Enter Password");
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelImage1 = new rojerusan.RSPanelImage();
        jLabel3 = new javax.swing.JLabel();
        txt_account = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_receiverid = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_amount = new app.bolivia.swing.JCTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_password = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/MyBankicon/Neft.png"))); // NOI18N
        rSPanelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Account No");
        rSPanelImage1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 100, 30));

        txt_account.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 0)));
        txt_account.setEnabled(false);
        rSPanelImage1.add(txt_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Receiver No");
        rSPanelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 100, 30));

        txt_receiverid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 0)));
        txt_receiverid.setPlaceholder("Enter a Receiver Acc no");
        rSPanelImage1.add(txt_receiverid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Amount");
        rSPanelImage1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 100, 30));

        txt_amount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 0)));
        txt_amount.setPlaceholder("Enter Amount To Transfer");
        rSPanelImage1.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Password");
        rSPanelImage1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 100, 30));

        txt_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 0)));
        txt_password.setPlaceholder("Enter Four Digit Pasword");
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        rSPanelImage1.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Transfer");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        rSPanelImage1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, 180, 36));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        rSPanelImage1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 100, 30));

        getContentPane().add(rSPanelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 650));

        setSize(new java.awt.Dimension(600, 650));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        HomePage h = new HomePage();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        if(checkField())
        {
            Neft();
        }
    }//GEN-LAST:event_jLabel1MouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Neft.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Neft.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Neft.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Neft.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Neft().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private rojerusan.RSPanelImage rSPanelImage1;
    private app.bolivia.swing.JCTextField txt_account;
    private app.bolivia.swing.JCTextField txt_amount;
    private app.bolivia.swing.JCTextField txt_password;
    private app.bolivia.swing.JCTextField txt_receiverid;
    // End of variables declaration//GEN-END:variables
}
