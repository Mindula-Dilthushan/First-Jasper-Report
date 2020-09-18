package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class DashbordFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXButton btnGenarateReport;
    public JFXButton btnGenarateDB;

    public void btnGenarateReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/CustomData.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(is);
            HashMap hashMap = new HashMap();
            hashMap.put("Cname", txtName.getText());
            hashMap.put("CAddress", txtAddress.getText());
            hashMap.put("CSalary", txtSalary.getText());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void btnGenarateDBOnAction(ActionEvent actionEvent) {
        try {
           // InputStream is  = this.getClass().getResourceAsStream("/report/CustomerReport.jrxml");
            InputStream is  = this.getClass().getResourceAsStream("/report/CustomerReport.jasper");
           // JasperReport jasperReport = JasperCompileManager.compileReport(is);
            JasperPrint jasperPrint = null;
            try {
                jasperPrint = JasperFillManager.fillReport(is,null, DBConnection.getInstance().getConnection());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            // JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
