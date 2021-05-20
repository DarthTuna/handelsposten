package de.darthtuna.gw2.handelsposten;

import de.darthtuna.gw2.handelsposten.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Handelsposten extends Application
{
    public static void main(final String[] args)
    {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception
    {
        var mainView = new MainView();
        var scene = new Scene(mainView.getView());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Handelsposten-Tool");
        primaryStage.show();
    }

}
