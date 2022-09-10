package application;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//import Controller.FXMLProfileController;
import Models.Commande;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CommandesController implements Initializable{
	ArrayList<String> sommeDonnée= new ArrayList();
	@FXML
    private Pane tabpangeneral;

    @FXML
    private Button ouvrirt, valider, supprimerLigne, imprimer, payer, total, annuler;
    @FXML
    private Button jusOrange;

    @FXML
    private Button tartefraise;

    @FXML
    private Button pizzaThon;

    @FXML
    private Button cafe, cappuccion;
    
    @FXML
    private Label dateEcran;
    @FXML
    private Label serveur1 = new Label();

    @FXML
    private Label serveur2 = new Label();

    @FXML
    private Label serveur3 = new Label();

    @FXML
    private Label serveur4 = new Label();
    
    private Button serveur3Clicked;

    @FXML
    private Button serveur1Clicked;

    @FXML
    private Button serveur4Clicked;

    @FXML
    private Button serveur2Clicked;
    
    @FXML
    private Button quatre;
    
    @FXML
    private Button okButton;

    @FXML
    private Button cinq;

    @FXML
    private Button six;

    @FXML
    private Button un;

    @FXML
    private Button zero;

    @FXML
    private Button deux;

    @FXML
    private Button trois;

    @FXML
    private Button virgule;

    @FXML
    private Button sept;

    @FXML
    private Button huit;

    @FXML
    private Button neuf;

    
    @FXML
    private Label serveurnom= new Label();
    
    Commande c = new Commande();
    @FXML
    private TableView<Commande> commandeListe;

    @FXML
    private TableColumn<Commande, Integer> id;

    @FXML
    private TableColumn<Commande, String> libelleProduit;

    @FXML
    private TableColumn<Commande, Float> prix;
    
    @FXML
    private Label totalnet;

    @FXML
    private TableColumn<Commande, Integer> qty;
    
    ObservableList<Commande> olist = FXCollections.observableArrayList();
    
    DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String datede=format.format(date).toString();

    @FXML
    void afficherTotalFacture(ActionEvent event){
    	
    	Connection cx=SingletonConnection.getConnection();
    	File f= new File("TotalCommandes.txt");
    	Float totalPrix = (float)0;
    	FileWriter fw= null;
 		BufferedWriter bw = null;
 		 
    	DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    	Date d = new Date();
    	String datede=format.format(d).toString();
        try {
        	 ArrayList<Float> total = new ArrayList();
        	 String sql = "Select Prix from commande"; //where dateCommande = @datede
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next()) {
            	 total.add(rs.getFloat("Prix"));
             }
             
             for(int i=0; i<total.size(); i++) {
            	 totalPrix+=total.get(i);
             }
            
     		
     		try {
     			fw = new FileWriter(f,true);
     			bw = new BufferedWriter(fw);
     			  
     			  bw.write("Date : "+datede.toString()+"  Total : "+totalPrix.toString());
     			  bw.newLine();

     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
     		finally {
     			try {
     				bw.close();
     				fw.close();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
     		}
        }catch(SQLException e){
        	e.printStackTrace();
        }
    	
 
    }

    @FXML
    void imprimerFacture(ActionEvent event) {
    	
    	Connection cx=SingletonConnection.getConnection();
    	File f= new File("Facture.txt");
    	FileWriter fw= null;
 		BufferedWriter bw = null;
 		
        try {
        	 String sql = "Select id, libelleProduit, Quantite, Prix from commande"; //where id=c.getId()
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
       		try {
     			fw = new FileWriter(f,true);
     			bw = new BufferedWriter(fw);
     			 while(rs.next()) {
     			  bw.write(rs.getInt("id")+"  "+rs.getString("libelleProduit")+"  "+rs.getInt("Quantite")+"  "+rs.getFloat("Prix"));
       			  bw.newLine();
                 }
     			 bw.write("---------------------------------------------------------------------");
     			 bw.newLine();
     			 bw.write("Total : "+totalnet.getText().toString());
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
     		finally {
     			try {
     				bw.close();
     				fw.close();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
     		}

        }catch(SQLException e){
        	e.printStackTrace();
        }
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle("Message");
    	alert.setContentText("Facture Imprimée");
    	alert.show();
    	
    }

    @FXML
    void ouvrirTiroir(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle("Message");
    	alert.setContentText("Tiroir Ouvert");
    	alert.show();
    }

    @FXML
    void payer(ActionEvent event) {
    	ouvrirTiroir(event);
    	
    	Commande c = new Commande();
    	//c.setNumCommande((int) Math.random()*1000);
    	olist = null;
    	id.setCellValueFactory(new PropertyValueFactory<>("0"));
		libelleProduit.setCellValueFactory(new PropertyValueFactory<>("0"));
		qty.setCellValueFactory(new PropertyValueFactory<>("0"));
		prix.setCellValueFactory(new PropertyValueFactory<>("0"));
		commandeListe.setItems(olist);
		commandeListe.autosize();
    	commandeListe.impl_updatePeer();
    	
    	totalnet.setText("0");
    	//Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	//alert.setTitle("Message");
    	//alert.setContentText("Tiroir Ouvert");
    	//alert.show();
    }
    
    @FXML
    void commanderPizzaThon(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=cx.prepareStatement("insert into commande value(?,?,?,?,?,?)");
			ps.setInt(1, c.getId());
			ps.setInt(2, c.getNumCommande());
			ps.setString(3, "Pizza Thon");
			ps.setInt(4, 1);
			ps.setFloat(5, (float)14.7);
			ps.setString(6, datede);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		try {
			int q = 0;
			 String sql = "Select count(*) As count from commande"; //where id=c.getId()
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next())
            	  q=rs.getInt("count");
             olist.add(new Commande(q+1,"Pizza Thon",(float)14.7,1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float x =new Float(totalnet.getText())+(float)14.7;
		totalnet.setText(""+x);
    }
    @FXML
    void commanderJusOrange(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=cx.prepareStatement("insert into commande value(?,?,?,?,?,?)");
			ps.setInt(1, c.getId());
			ps.setInt(2, c.getNumCommande());
			ps.setString(3, "Jus d'Orange");
			ps.setInt(4, 1);
			ps.setFloat(5, (float)8.6);
			ps.setString(6, datede);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		try {
			int q = 0;
			 String sql = "Select count(*) As count from commande"; //where id=c.getId()
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next())
            	  q=rs.getInt("count");
             olist.add(new Commande(q+1,"Jus d'Orange",(float)8.6,1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float x =new Float(totalnet.getText())+(float)8.6;
		totalnet.setText(""+x);
    }
    @FXML
    void commanderCafe(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=cx.prepareStatement("insert into commande value(?,?,?,?,?,?)");
			ps.setInt(1, c.getId());
			ps.setInt(2, c.getNumCommande());
			ps.setString(3, "Cafe Turc");
			ps.setInt(4, 1);
			ps.setFloat(5, 3);
			ps.setString(6, datede);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		try {
			int q = 0;
			 String sql = "Select count(*) As count from commande"; //where id=c.getId()
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next())
            	  q=rs.getInt("count");
             olist.add(new Commande(q+1,"Cafe Turc",3,1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float x =new Float(totalnet.getText())+3;
		totalnet.setText(""+x);
    }
    @FXML
    void commanderCappuccion(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=cx.prepareStatement("insert into commande value(?,?,?,?,?,?)");
			ps.setInt(1, c.getId());
			ps.setInt(2, c.getNumCommande());
			ps.setString(3, "Cafe Cappuccino");
			ps.setInt(4, 1);
			ps.setFloat(5, (float)6.8);
			ps.setString(6, datede);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			int q = 0;
			 String sql = "Select count(*) As count from commande"; //where id=c.getId()
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next())
            	  q=rs.getInt("count");
             olist.add(new Commande(q+1,"Cappuccion",(float)6,1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float x =new Float(totalnet.getText())+6;
		totalnet.setText(""+x);
    }
    @FXML
    void commanderTarteFraise(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=cx.prepareStatement("insert into commande value(?,?,?,?,?,?)");
			ps.setInt(1, c.getId());
			ps.setInt(2, c.getNumCommande());
			ps.setString(3, "Tarte Fraise");
			ps.setInt(4, 1);
			ps.setFloat(5, 7);
			ps.setString(6, datede);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		try {
			int q = 0;
			 String sql = "Select count(*) As count from commande"; //where id=c.getId()
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next())
            	  q=rs.getInt("count");
             olist.add(new Commande(q+1,"Tarte Fraise",7,1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float x =new Float(totalnet.getText())+7;
		totalnet.setText(""+x);
    }
    @FXML
    void showTime() {
    
    }
   
   @FXML
    void supprimerLigne(ActionEvent event) {
    	
    	int selectedRow = commandeListe.getSelectionModel().getSelectedIndex();
    	System.out.println(selectedRow);
    	Connection cx=SingletonConnection.getConnection();
		try {
			Statement ps=cx.createStatement();
			int rs = ps.executeUpdate("DELETE FROM commande WHERE id='$selectedRow'");
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	if (selectedRow >= 0) {
           	commandeListe.getItems().remove(selectedRow);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Aucune ligne selectionnée");
            alert.setContentText("S'ils vous plait selectionnez une ligne");
            alert.showAndWait();
        }

    }
   @FXML
   void validerCommande(ActionEvent event){
   	String somme="";
   	for(int i=0; i<sommeDonnée.size(); i++) {
   		somme=somme+sommeDonnée.get(i);
   	}
   	System.out.println(somme);
   	float n = new Float(somme);
   	float nb = new Float(totalnet.getText());
   	float sr = n-nb;
   	Alert alert = new Alert(Alert.AlertType.INFORMATION);
   	alert.setTitle("Message");
   	alert.setContentText("Somme à rendre : "+sr);
   	alert.showAndWait();
   

   }
    @FXML
    void annulerCommande(ActionEvent event) {
    		int m= c.getNumCommande();
    		Connection cx=SingletonConnection.getConnection();
    		try {
    			PreparedStatement ps=cx.prepareStatement("delete from commande where numelement = @m");
    			ResultSet rs = ps.executeQuery();
    		}catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	alert.setTitle("Message");
        	alert.setContentText("Commande annulée");
        	alert.showAndWait();
    }
    
    @FXML
    void serveur1Clicked(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
        try {
        	ArrayList<String> serveur = new ArrayList();
        	 String sql = "select nom from serveur";
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next()) {
            	 serveur.add(rs.getString("nom"));
             }
             
             serveurnom.setText("  "+serveur.get(0));
	         
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void serveur2Clicked(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
        try {
        	ArrayList<String> serveur = new ArrayList();
        	 String sql = "select nom from serveur";
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next()) {
            	 serveur.add(rs.getString("nom"));
             }
             
             serveurnom.setText("  "+serveur.get(1));
	         
	            
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void serveur3Clicked(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
        try {
        	ArrayList<String> serveur = new ArrayList();
        	 String sql = "select nom from serveur";
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next()) {
            	 serveur.add(rs.getString("nom"));
             }
             
             serveurnom.setText("  "+serveur.get(2));
	         
	            
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void serveur4Clicked(ActionEvent event) {
    	Connection cx=SingletonConnection.getConnection();
        try {
        	ArrayList<String> serveur = new ArrayList();
        	 String sql = "select nom from serveur";
             PreparedStatement pst1 = null;
             pst1 = cx.prepareStatement(sql);
             ResultSet rs = null;
             rs = pst1.executeQuery();
             while(rs.next()) {
            	 serveur.add(rs.getString("nom"));
             }
             
             serveurnom.setText("  "+serveur.get(3));
	         
	            
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void zero(ActionEvent event) {
		sommeDonnée.add("0");
    }
    @FXML
    void un(ActionEvent event) {
		sommeDonnée.add("1");

    }
    @FXML
    void deux(ActionEvent event) {
    	sommeDonnée.add("2");

    }
    @FXML
    void trois(ActionEvent event) {
    	sommeDonnée.add("3");

    }
    @FXML
    void quatre(ActionEvent event) {
    	sommeDonnée.add("4");
    }
    @FXML
    void cinq(ActionEvent event) {
    	sommeDonnée.add("5");
		
    }
    
    @FXML
    void six(ActionEvent event) {
    	sommeDonnée.add("6");
    }
    @FXML
    void sept(ActionEvent event) {
    	sommeDonnée.add("7");
    }
    @FXML
    void neuf(ActionEvent event) {
    	sommeDonnée.add("9");

    }
    @FXML
    void huit(ActionEvent event) {
    	sommeDonnée.add("8");
    }
    @FXML
    void virgule(ActionEvent event) {
    	sommeDonnée.add(".");
    }
    @FXML
    void okButton(ActionEvent event){
    	
    	sommeDonnée.remove(sommeDonnée.size()-1);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		commandeListe.setEditable(false);
		Connection cx=SingletonConnection.getConnection();
			try {
				
				PreparedStatement ps= cx.prepareStatement("select id, NumCommande, libelleProduit, Quantite, Prix from commande");
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					olist.add(new Commande(rs.getInt("id"), rs.getInt("NumCommande"), rs.getString("libelleProduit"), rs.getInt("Quantite"), rs.getFloat("Prix")));
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
			libelleProduit.setCellValueFactory(new PropertyValueFactory<>("libelleProduit"));
			qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
			prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
			commandeListe.setItems(olist);
			//commandeListe.autosize();
			//commandeListe.setStyle("-fx-background-color: black");
			
			try {
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    	Date date = new Date();
		    	String datede=format.format(date).toString();
		    	dateEcran.setText("       "+datede);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	
	        try {
	        	ArrayList<String> serveur = new ArrayList();
	        	 String sql = "Select nom from serveur";
	             PreparedStatement pst1 = null;
	             pst1 = cx.prepareStatement(sql);
	             ResultSet rs = null;
	             rs = pst1.executeQuery();
	             while(rs.next()) {
	            	 serveur.add(rs.getString("nom"));
	             }
	             
	            	 serveur1.setText("  "+serveur.get(0));
		             serveur2.setText("  "+serveur.get(1));
		             serveur3.setText("  "+serveur.get(2));
		             serveur4.setText("  "+serveur.get(3));
	             
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        try {
	        	ArrayList<Float> prixtotal = new ArrayList();
	        	ArrayList<Integer> qtylist = new ArrayList();
	        	float prix=0;
	        	 String sql = "Select Prix from commande c where c.NumCommande = c.NumCommande";
	        	 PreparedStatement pst1 = null;
	             pst1 = cx.prepareStatement(sql);
	             ResultSet rs = null;
	             rs = pst1.executeQuery();
	             
	        	 String sql2 = "Select Quantite from commande c where c.NumCommande = c.NumCommande";       
	             PreparedStatement pst2 = null;
	             pst2 = cx.prepareStatement(sql2);
	             ResultSet rs1 = null;
	             rs1 = pst2.executeQuery();
	             
	             while(rs.next()) {
	            	 prixtotal.add(rs.getFloat("Prix")); 
	             }
	             while(rs1.next()) {
	            	 qtylist.add(rs1.getInt("Quantite"));
	             }
	             
	            	 for (int i=0; i<prixtotal.size();i++) {
	            		 prix=prix+(prixtotal.get(i))*(qtylist.get(i));
						
					}
	            	 System.out.println(prix);
	            	 totalnet.setText(" "+prix);
	             
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	}
}
	

