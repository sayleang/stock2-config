package sl.test.bluebird;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class BlueBirdMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setResources(ResourceBundle.getBundle("BlueBird", new Locale("en", "EN")));
            Parent root = fxmlLoader.load(this.getClass().getResource("/BlueBird.fxml").openStream());


            Scene scene=new Scene(root);

            scene.getStylesheets().add("BlueBird.css");

            primaryStage.setTitle(fxmlLoader.getResources().getString("window.title"));

            primaryStage.setScene(scene);

            primaryStage.show();

            //FlatterFX.style(FlatterInputType.DEFAULT);

            primaryStage.setOnCloseRequest(ev -> {
                System.out.println("closed....");
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
