/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modsim;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author exzon
 */
public class FXMLDocumentController implements Initializable {

    private Queue<String> q = new LinkedList<>();
    private String temp = "";
    private String que = "";

    private int tempSaatIni = 0;

    @FXML
    private TextField tAntrean, tCS1, tCS2, tSaatIni, tTotalDiAntrean;

    @FXML
    private TextArea tQueue;

    @FXML
    private void tambahButtonAction(ActionEvent event) {
        if (!q.contains(tAntrean.getText()) && Integer.parseInt(tAntrean.getText()) > tempSaatIni) {
            q.add((tAntrean.getText()));
            if (tCS1.getText().equals("")) {
                tCS1.setText(q.poll() + "");
            } else if (tCS2.getText().equals("")) {
                tCS2.setText(q.poll() + "");
            } else {

            }
            this.getCurrentQueue();
        }

    }

    @FXML
    private void selesaiCS1Action(ActionEvent event) {
        if (q.size() != 0) {
            tCS1.setText(q.poll() + "");
            this.getCurrentQueue();
        }
    }

    @FXML
    private void selesaiCS2Action(ActionEvent event) {
        if (q.size() != 0) {
            tCS2.setText(q.poll() + "");
            this.getCurrentQueue();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void getCurrentQueue() {
        int x = q.size();

        for (String s : q) {
            temp = (s.toString());
            que += temp + "\n";
        }
        System.out.println(que);
        tQueue.setText(que);
        que = "";
        if (tCS1.getText().equals("")) {
            tSaatIni.setText(tCS2.getText());
            tTotalDiAntrean.setText(0 + "");
            tempSaatIni = Integer.parseInt(tCS2.getText());
        } else if (tCS2.getText().equals("")) {
            tSaatIni.setText(tCS1.getText());
            tTotalDiAntrean.setText(0 + "");
            tempSaatIni = Integer.parseInt(tCS1.getText());
        } else {
            tSaatIni.setText(Math.max(Integer.parseInt(tCS1.getText()), Integer.parseInt(tCS2.getText())) + "");
            tempSaatIni = (Math.max(Integer.parseInt(tCS1.getText()), Integer.parseInt(tCS2.getText())));
            tTotalDiAntrean.setText(q.size() + "");
        }
    }
}
