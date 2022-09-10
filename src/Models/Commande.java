package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Commande {
	
	private int id;
	private int NumCommande=0;
	private float prix=0;
	private String libelleProduit;
	private static ArrayList<Menu> elementsCommandes;
	private int qty;
	private String date;
	
	
	public Commande() {
		
	}
	
	public Commande(int id, int numCommande, String libelleProduit, int qty, float prix) {

		this.id = id;
		NumCommande = numCommande;
		this.libelleProduit = libelleProduit;
		this.prix = prix;
		this.qty = qty;
		this.date = datede;
	}

	public Commande(int id, String libelleProduit, float prix, int qty) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.libelleProduit = libelleProduit;
		this.prix = prix;
		this.qty = qty;
		this.date = datede;
	}

	DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date d = new Date();
	String datede=format.format(d).toString();
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getNumCommande() {
		return NumCommande;
	}



	public void setNumCommande(int numCommande) {
		NumCommande = numCommande;
	}



	public float getPrix() {
		return prix;
	}



	public void setPrix(float prix) {
		this.prix = prix;
	}



	public String getLibelleProduit() {
		return libelleProduit;
	}



	public void setLibelleProduit(String libelleProduit) {
		this.libelleProduit = libelleProduit;
	}



	public int getQty() {
		return qty;
	}



	public void setQty(int qty) {
		this.qty = qty;
	}



	public static ArrayList<Menu> getElementsCommandes() {
		return elementsCommandes;
	}

	public static void setElementsCommandes(ArrayList<Menu> elementsCommandes) {
		Commande.elementsCommandes = elementsCommandes;
	}
	
	public void ajouterElement(Menu e) {
		for(int i=0; i<elementsCommandes.size();i++)
			elementsCommandes.add(e);
	}
	
	public void afficherListeCommande(){
		for(int i=0; i<elementsCommandes.size();i++)
			System.out.println(elementsCommandes.get(i));
		
	}
}
