/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modsim;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author exzon
 */
public class FXMLDocumentController implements Initializable {

    private String temp = "";
    private String que = "";
    private static int i = 1;

    ArrayList<Customer> c = new ArrayList();
    private static final Antrian queue = new Antrian();

    @FXML
    private TextField tCS1, tCS2, tSaatIni, tTotalDiAntrean, tNomorAnda;

    @FXML
    private TextArea tQueue;

    @FXML
    private Button bAmbilNomor, bPanggilSistem, bTambahCustomer;

//    @FXML
//    private void callSistemAction(ActionEvent event) throws IOException {
////        ((Node) (event.getSource())).getScene().getWindow().hide();
//        bPanggilSistem.setDisable(true);
//        bTambahCustomer.setDisable(false);
//        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(parent);
//        stage.setScene(scene);
//        stage.setTitle("Login");
//        stage.show();
//    }
//
    @FXML
    private void addCustomer(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Pengunjung.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    private void ambilNomor(ActionEvent event) {
        bAmbilNomor.setDisable(true);
        tNomorAnda.setText(i + "");
        queue.addToQ(i + "");
        i++;
    }

    @FXML
    private void refreshSystem(ActionEvent event) {

        if (!queue.isEmpty()) {
            if (queue.getQ().size() == 2 && tCS1.getText().equals("") && tCS2.getText().equals("")) {
                tCS1.setText(queue.pollFromQ() + "");
                tCS2.setText(queue.pollFromQ() + "");

            } else if (tCS1.getText().equals("")) {
                tCS1.setText(queue.pollFromQ() + "");
            } else if (tCS2.getText().equals("")) {
                tCS2.setText(queue.pollFromQ() + "");
            } else {
            }
            this.getCurrentQueue();
        }
    }

    @FXML
    private void bTambah(ActionEvent event) {
        Customer cus = new Customer(i);
        queue.addToQ((cus.getNumber() + ""));
        if (tCS1.getText().equals("")) {
            tCS1.setText(queue.pollFromQ() + "");
        } else if (tCS2.getText().equals("")) {
            tCS2.setText(queue.pollFromQ() + "");
        } else {

        }
        i++;
        this.getCurrentQueue();
    }

    @FXML
    private void selesaiCS1Action(ActionEvent event) {
        if (!queue.isEmpty()) {
            tCS1.setText(queue.pollFromQ() + "");
            this.getCurrentQueue();
        } else {
            tCS1.setText("");
            this.getCurrentQueue();
        }
    }

    @FXML
    private void selesaiCS2Action(ActionEvent event) {
        if (!queue.isEmpty()) {
            tCS2.setText(queue.pollFromQ() + "");
            this.getCurrentQueue();
        } else {
            tCS2.setText("");
            this.getCurrentQueue();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void getCurrentQueue() {
        for (String s : queue.getQ()) {
            temp = (s.toString());
            que += temp + "\n";
        }
        this.tQueue.setText(que);
        que = "";
        if (this.tCS1.getText().equals("")) {
            if (tSaatIni.getText().equals("")) {
                tSaatIni.setText(tCS2.getText());
            } else {
                if (!tCS2.getText().equals("")) {
                    tSaatIni.setText(Math.max(Integer.parseInt(tCS2.getText()), Integer.parseInt(tSaatIni.getText())) + "");
                }
            }
            tTotalDiAntrean.setText(0 + "");
        } else if (tCS2.getText().equals("")) {
            if (tSaatIni.getText().equals("")) {
                tSaatIni.setText(tCS1.getText());
            } else {
                if (!tCS1.getText().equals("")) {
                    tSaatIni.setText(Math.max(Integer.parseInt(tCS1.getText()), Integer.parseInt(tSaatIni.getText())) + "");
                }
            }
            tTotalDiAntrean.setText(0 + "");
        } else {
            tSaatIni.setText(Math.max(Integer.parseInt(tCS1.getText()), Integer.parseInt(tCS2.getText())) + "");
            tTotalDiAntrean.setText(queue.getQ().size() + "");
        }
    }
}
