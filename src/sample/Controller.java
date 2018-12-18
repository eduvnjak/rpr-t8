package sample;

import javafx.application.Platform;
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
    public Button prekini;
    private boolean zaustavi;
    private List listaDatoteka = new LinkedList<String>();
    public String uzorak;

    public void pretraziFolder(File folder, String uzorak){
        //File sadrzaj[] = folder.listFiles();
        if(folder.listFiles() != null) {
            for (File sadrzaj : folder.listFiles()) {
                if (zaustavi){
                    trazi.setDisable(false);
                    prekini.setDisable(true);
                    break;
                }
                ///File nesto = new File(sadrzaj[i]);
                //System.out.println(sadrzaj[i]);
                if (sadrzaj.isFile()) {
                    //System.out.println("FILE");
                    String s1 = sadrzaj.getName().toLowerCase();
                    if (s1.contains(uzorak)) {
                        Platform.runLater(() -> {
                            listaDatoteka.add(sadrzaj.toString());
                        });
                    }
                }
                if (sadrzaj.isDirectory() && !sadrzaj.isHidden()) {
                    //System.out.println(sadrzaj);
                    pretraziFolder(sadrzaj, uzorak);
                }
            }
        }
    }
    public void pretraga() {
        uzorak = unos.getText().toLowerCase();
        if(uzorak.length() != 0) {
            lista.getItems().clear();
            //File roots[] = File.listRoots();
            /*for (int i = 0; i < roots.length ; i++) {
                pretraziFolder(roots[i], listaDatoteka, uzorak);
            }*/
            zaustavi = false;
            prekini.setDisable(false);
            new Thread(() -> {
                pretraziFolder(new File("D:\\Users\\Eman"), uzorak);
                Platform.runLater(() ->{
                    trazi.setDisable(true);
                });
            }).start();
            lista.getItems().addAll(listaDatoteka);
            trazi.setDisable(false);
        }
    }

    public void zaustaviPretragu(ActionEvent actionEvent) {
        zaustavi = true;
        trazi.setDisable(false);
    }
}
