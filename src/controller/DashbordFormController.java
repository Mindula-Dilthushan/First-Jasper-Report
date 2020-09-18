package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.SQLException;

public class DashbordFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXButton btnGenarateReport;
    public JFXButton btnGenarateDB;

    public void btnGenarateReportOnAction(ActionEvent actionEvent) {
    }

    public void btnGenarateDBOnAction(ActionEvent actionEvent) {
        try {
            InputStream is  = this.getClass().getResourceAsStream("/report/CustomerReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(is);
            JasperPrint jasperPrint = null;

            try {
                jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            // JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
