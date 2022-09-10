package Models;

public class Menu {
	private int id;
	private String Libelle;
	private float prix;
	
	public Menu(int id, String libelle, float prix) {
		this.id = id;
		Libelle = libelle;
		this.prix = prix;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return Libelle;
	}

	public void setLibelle(String libelle) {
		Libelle = libelle;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	
}
