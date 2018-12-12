package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    public TextField unos;
    public Button trazi;
    public ListView<String> lista;
    public void pretraziFolder(File folder, List<String> listaDatoteka, String uzorak){
        File sadrzaj[] = folder.listFiles();
        if(sadrzaj != null) {
            for (int i = 0; i < sadrzaj.length; i++) {
                ///File nesto = new File(sadrzaj[i]);
                //System.out.println(sadrzaj[i]);
                if (sadrzaj[i].isFile()) {
                    //System.out.println("FILE");
                    if (sadrzaj[i].getName().contains(uzorak)) listaDatoteka.add(sadrzaj[i].toString());
                }
                if (sadrzaj[i].isDirectory() && !sadrzaj[i].isHidden()) {
                    //System.out.println(sadrzaj[i]);
                    pretraziFolder(sadrzaj[i], listaDatoteka, uzorak);
                }
            }
        }
    }
    public void pretraga(ActionEvent event) {
        String uzorak = new String(unos.getText());
        if(uzorak.length() != 0){
            lista.getItems().clear();
            File roots[] = File.listRoots();
            List listaDatoteka = new LinkedList<String>();
            for (int i = 0; i < roots.length ; i++) {
                pretraziFolder(roots[i], listaDatoteka, uzorak);
            }
            lista.getItems().addAll(listaDatoteka);
        }
    }
}
