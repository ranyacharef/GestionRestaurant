package application;	
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.rowset.spi.SyncFactory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application implements EventHandler<ActionEvent>{
	
	Button ouvrirt = new Button();
	Button valider = new Button();
	Button annuler = new Button();
	Button supprimerLigne = new Button();
	Button imprimer = new Button();
	Button payer = new Button();
	Button total = new Button();
	Button tartefraise = new Button();
	Button pizzaThon = new Button();
	Button jusOrange = new Button();
	Button cafe = new Button();
	Button cappucino = new Button();
	Button serveur1Clicked = new Button();
	Button serveur2Clicked = new Button();
	Button serveur3Clicked = new Button();
	Button serveur4Clicked = new Button();
	Button un = new Button();
	Button deux = new Button();
	Button trois = new Button();
	Button quatre = new Button();
	Button cinq = new Button();
	Button six = new Button();
	Button sept = new Button();
	Button huit = new Button();
	Button neuf = new Button();
	Button virgule = new Button();
	Button okButton = new Button();
	Label dateEcran;

	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("GestionCommandes.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
		ouvrirt.setOnAction(this);
		valider.setOnAction(this);
		annuler.setOnAction(this);
		supprimerLigne.setOnAction(this);
		imprimer.setOnAction(this);
		payer.setOnAction(this);
		total.setOnAction(this);
		cafe.setOnAction(this);
		jusOrange.setOnAction(this);
		pizzaThon.setOnAction(this);
		tartefraise.setOnAction(this);
		cappucino.setOnAction(this);
		serveur1Clicked.setOnAction(this);
		serveur2Clicked.setOnAction(this);
		serveur3Clicked.setOnAction(this);
		serveur4Clicked.setOnAction(this);
		un.setOnAction(this);
		deux.setOnAction(this);
		trois.setOnAction(this);
		quatre.setOnAction(this);
		cinq.setOnAction(this);
		six.setOnAction(this);
		sept.setOnAction(this);
		huit.setOnAction(this);
		neuf.setOnAction(this);
		virgule.setOnAction(this);
		okButton.setOnAction(this);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dat = new Date();
		dateEcran = new Label( format.format(dat).toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Affichage Date
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date dat = new Date();
				System.out.println(format.format(dat));
				dateEcran.setText(""+format.format(dat).toString());;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}
