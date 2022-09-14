import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 *
 * Simple Java Swing MySQL Database Application
 * Contains Methods for Creating, Filling, Reading and Deleting a Database
 * 
 * Requires MySQL Connector/J from https://dev.mysql.com/downloads/connector/j/
 *
 * @version 1.0 vom 14.09.2022
 * @author NiclzzK
 */

public class main extends JFrame {
  // Anfang Attribute
  private JTextArea jTextArea1 = new JTextArea("");
    private JScrollPane jTextArea1ScrollPane = new JScrollPane(jTextArea1);
  private JButton bCreateDB = new JButton();
  private JButton bFillDB = new JButton();
  private JTextField mySQLIPTextField = new JTextField();
  private JLabel lMySQLIP = new JLabel();
  private JLabel lMySQLPort = new JLabel();
  private JTextField MySQLPortTextField = new JTextField();
  private JLabel lOutput = new JLabel();
  private JButton bReadDB = new JButton();
  private JButton bDeleteDB = new JButton();
  private JButton bClearOutput = new JButton();
  // Ende Attribute
  
  public main() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 600; 
    int frameHeight = 401;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("DB Tool");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    bClearOutput.setBounds(16, 328, 552, 24);
    bClearOutput.setText("Clear Output");
    bClearOutput.setMargin(new Insets(2, 2, 2, 2));
    bClearOutput.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bClearOutput_ActionPerformed(evt);
      }
    });
    cp.add(bClearOutput);
    bDeleteDB.setBounds(440, 40, 128, 24);
    bDeleteDB.setText("Delete DB");
    bDeleteDB.setMargin(new Insets(2, 2, 2, 2));
    bDeleteDB.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDeleteDB_ActionPerformed(evt);
      }
    });
    cp.add(bDeleteDB);
    bReadDB.setBounds(296, 40, 128, 24);
    bReadDB.setText("Read DB");
    bReadDB.setMargin(new Insets(2, 2, 2, 2));
    bReadDB.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bReadDB_ActionPerformed(evt);
      }
    });
    cp.add(bReadDB);
    lOutput.setBounds(16, 80, 552, 24);
    lOutput.setText("Output");
    lOutput.setHorizontalTextPosition(SwingConstants.CENTER);
    lOutput.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lOutput);
    MySQLPortTextField.setBounds(152, 40, 128, 24);
    MySQLPortTextField.setText("3306");
    cp.add(MySQLPortTextField);
    lMySQLPort.setBounds(16, 40, 128, 24);
    lMySQLPort.setText("MySQL Port");
    cp.add(lMySQLPort);
    lMySQLIP.setBounds(16, 8, 128, 24);
    lMySQLIP.setText("MySQL IP");
    cp.add(lMySQLIP);
    mySQLIPTextField.setBounds(152, 8, 128, 24);
    mySQLIPTextField.setText("localhost");
    cp.add(mySQLIPTextField);
    bFillDB.setBounds(440, 8, 128, 24);
    bFillDB.setText("Fill DB");
    bFillDB.setMargin(new Insets(2, 2, 2, 2));
    bFillDB.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bFillDB_ActionPerformed(evt);
      }
    });
    cp.add(bFillDB);
    bCreateDB.setBounds(296, 8, 128, 24);
    bCreateDB.setText("Create DB");
    bCreateDB.setMargin(new Insets(2, 2, 2, 2));
    bCreateDB.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bCreateDB_ActionPerformed(evt);
      }
    });
    cp.add(bCreateDB);
    jTextArea1ScrollPane.setBounds(16, 112, 552, 208);
    jTextArea1.setEditable(false);
    cp.add(jTextArea1ScrollPane);
    
    // Ende Komponenten
    
    setVisible(true);
  } // end of public main
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new main();
  } // end of main
  
  public void bCreateDB_ActionPerformed(ActionEvent evt) {
    // create mysql database
    try {
      jTextArea1.setText(jTextArea1.getText()+"\n"+"Creating Database...");
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://"+mySQLIPTextField.getText()+":"+MySQLPortTextField.getText()+"/", "root", "root");
      Statement stmt = con.createStatement();
      stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `test`;");
      stmt.close();
      con.close();
      jTextArea1.setText(jTextArea1.getText()+"\n"+"Database created or already exists.");
    } catch (Exception e) {
      jTextArea1.setText(jTextArea1.getText()+"\n"+e.toString());
    }
    
  } // end of bCreateDB_ActionPerformed

  public void bFillDB_ActionPerformed(ActionEvent evt) {
    // fill mysql database
    try {
      jTextArea1.setText(jTextArea1.getText()+"\n"+"Filling Database...");
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://"+mySQLIPTextField.getText()+":"+MySQLPortTextField.getText()+"/test", "root", "root");
      Statement stmt = con.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `test`.`test` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,PRIMARY KEY (`id`));");
      stmt.executeUpdate("INSERT INTO `test`.`test` (`name`) VALUES ('Alfred');");
      stmt.executeUpdate("INSERT INTO `test`.`test` (`name`) VALUES ('Peter');");
      stmt.executeUpdate("INSERT INTO `test`.`test` (`name`) VALUES ('Mustafa');");
      stmt.close();
      con.close();
      jTextArea1.setText(jTextArea1.getText()+"\n"+"Database filled.");
    } catch (Exception e) {
      jTextArea1.setText(jTextArea1.getText()+"\n"+e.toString());
    }
    
  } // end of bFillDB_ActionPerformed

  public void bReadDB_ActionPerformed(ActionEvent evt) {
    // read mysql database
    try {
      jTextArea1.setText(jTextArea1.getText()+"\n"+"Reading Database..."+"\n");
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://"+mySQLIPTextField.getText()+":"+MySQLPortTextField.getText()+"/test", "root", "root");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM test");
      while (rs.next()) {
        jTextArea1.setText(jTextArea1.getText()+"\n"+rs.getString("name"));
      }
      stmt.close();
      con.close();
      jTextArea1.setText(jTextArea1.getText()+"\n"+"\n"+"Database read.");
    } catch (Exception e) {
      jTextArea1.setText(jTextArea1.getText()+"\n"+e.toString());
    }
    
  } // end of bReadDB_ActionPerformed

  public void bDeleteDB_ActionPerformed(ActionEvent evt) {
    // delete mysql database
    try {
      jTextArea1.setText(jTextArea1.getText()+"\n"+"Deleting Database...");
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://"+mySQLIPTextField.getText()+":"+MySQLPortTextField.getText()+"/", "root", "root");
      Statement stmt = con.createStatement();
      stmt.executeUpdate("DROP DATABASE IF EXISTS `test`;");
      stmt.close();
      con.close();
      jTextArea1.setText(jTextArea1.getText()+"\n"+"Database deleted.");
    } catch (Exception e) {
      jTextArea1.setText(jTextArea1.getText()+"\n"+e.toString());
    }
    
  } // end of bDeleteDB_ActionPerformed

  public void bClearOutput_ActionPerformed(ActionEvent evt) {
    // clear output
    jTextArea1.setText("");
    
  } // end of bClearOutput_ActionPerformed

  // Ende Methoden
} // end of class main
