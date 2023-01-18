/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stib.app;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.jdbc.DBManager;
import g54018.stib.model.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presenter.Presenter;
import stib.view.MainViewController;

/**
 *
 * @author basile
 */
public class App extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        ConfigManager.getInstance().load();
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stib/stibPlan.fxml"));
        Parent root = loader.load();
        
        
        MainViewController mv = loader.getController();
        Model model = new Model();
        Presenter presenter = new Presenter(model, mv);
        
        model.addPropertyChangeListener(presenter);
        mv.setPresenter(presenter);
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    
}
