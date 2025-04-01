package programa3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Practica3Jocs extends Application {

	// ---VARIABLES GLOBALS 3 EN
	// RATLLA------------------------------------------------------
	String tirada3R = "x";
	char accio3R = 'E'; // escollir
	boolean guanyador3R = false;
	int numTirada3R = 0;

	// ---VARIABLES GLOBALS 3 EN RATLLA
	// VERTICAL---------------------------------------------
	static boolean guanyador3RV = false;
	static String tirada3RV = "blau";
	static int [] comptador3RV = new int[8];

	// ---VARIABLES GLOBALS JOC DEL PENJAT
	// -------------------------------------------------

	Random r = new Random();
	String [] auxParaules = { "jugador", "barcelona", "tisores", "universitat", "platja", "mussol", "gat", "farmacia", 
			"jove", "familia", "escola", "llibre", "guitarra", "platja", "carrer", "amic", "muntanya",
			"germans", "univers", "ocell", "cultura", "cavall", "lluna", "enigma", "esport", "carreter",
			"viatge", "roig", "acte", "terra", "sol", "dona", "horari", "venedor", "mercat","cuina",
			"tramvia", "banc", "nevat", "hospital", "amable", "pintor", "postre", "groc", "crema",
			"obstacle", "sant", "teatre", "gall", "maquinista", "carrer", "roda", "piscina", "flor",
			"camioner", "discoteca", "valent", "carro", "tortuga", "lupa", "exercici", "tenda" };
	String paraulaTriada2 = "";
	String paraulaTriada = "";
	String auxParaula = "";
	String paraulaLlarga = "";
	int numErrorsPenjat = 0;

	// --VARIABLES GLOBALS JOC
	// CAMELLS-----------------------------------------------------
	static boolean guanyadorCamells = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage teatre) throws Exception {

		// --creacio dels menús i la barra de menú------------------------------------
		Menu menu1 = new Menu("2 JUGADORS");
		MenuBar barraMenu = new MenuBar();
		MenuItem opcioTresRatlla = new MenuItem("Tres en Ratlla");
		MenuItem opcioTresRatllaV = new MenuItem("Tres en Ratlla Vertical");
		menu1.getItems().addAll(opcioTresRatlla, opcioTresRatllaV);

		Menu menu2 = new Menu("1 JUGADOR");
		MenuItem opcioPenjat = new MenuItem("Joc del Penjat");
		MenuItem opcioCamells = new MenuItem("Joc dels Camells");
		menu2.getItems().addAll(opcioPenjat, opcioCamells);
		barraMenu.getMenus().addAll(menu1, menu2);
		barraMenu.setLayoutX(200);
		barraMenu.setLayoutY(20);

		// ---BOTONS i PANE DEL 3 EN
		// RATLLA-----------------------------------------------------
		Label etiqueta3R = new Label();
		etiqueta3R.setLayoutX(200);
		etiqueta3R.setLayoutY(350);
		etiqueta3R.setFont(new Font("Tahoma", 18));
		etiqueta3R.setText("");
		Button[] botons3R = new Button[9];
		Button bRePlay3R = new Button();
		bRePlay3R.setLayoutX(270);
		bRePlay3R.setLayoutY(420);
		bRePlay3R.setText("RePlay");
		bRePlay3R.setDisable(true);

		Pane decorat2 = new Pane();

		decorat2.setStyle("-fx-background-color: #FCF768");
		Scene escenari2 = new Scene(decorat2, 600, 500, Color.AZURE);
		teatre.setTitle("Tres en ratlla");
		teatre.setScene(escenari2);

		// ----BOTONS I PANE DEL TRES EN RATLLA
		// VERTICAL--------------------------------------------------
		Pane decorat3 = new Pane();

		decorat3.setStyle("-fx-background-color: #A5F5F6");
		Scene escenari3 = new Scene(decorat3, 600, 700, Color.AZURE);
		teatre.setTitle("Quatre en Ratlla Vertical");
		teatre.setScene(escenari3);

		ImageView[][] taulell3RV = new ImageView[10][8];

		Button[] taulaBotons3RV = new Button[8];

		Label etiquetaInfo3RV = new Label();
		etiquetaInfo3RV.setLayoutX(400);
		etiquetaInfo3RV.setLayoutY(650);
		etiquetaInfo3RV.setFont(new Font("Tahoma", 16));
		decorat3.getChildren().add(etiquetaInfo3RV);

		Button bRePlay3RV = new Button();
		bRePlay3RV.setText("Replay");
		bRePlay3RV.setLayoutX(250);
		bRePlay3RV.setLayoutY(650);
		bRePlay3RV.setDisable(true);
		decorat3.getChildren().add(bRePlay3RV);

		Image cercleBlanc = new Image(getClass().getResourceAsStream("/images/cercle_buit.png"));

		Image cercleBlau = new Image(getClass().getResourceAsStream("/images/cercle_blau.png"));

		// creo una taula de ImageViews de la mateixa mida que la dels botons per a
		// poder col·locar el cercle al botó
		ImageView[] botonsBlaus = new ImageView[8];
		for (int i = 0; i < 8; i++) {
			botonsBlaus[i] = new ImageView(cercleBlau);
			botonsBlaus[i].setFitHeight(20);
			botonsBlaus[i].setFitWidth(20);
		}

		Image cercleTaronja = new Image(getClass().getResourceAsStream("/images/cercle_taronja.png"));
		// creo una taula de ImageViews de la mateixa mida que la dels botons per a
		// poder col·locar el cercle al botó
		ImageView[] botonsTaronges = new ImageView[8];
		for (int i = 0; i < 8; i++) {
			botonsTaronges[i] = new ImageView(cercleTaronja);
			botonsTaronges[i].setFitHeight(20);
			botonsTaronges[i].setFitWidth(20);
		}

		// ---BOTONS I PANE JOC DEL PENJAT------------------------------------------
		String[] auxLletres = { "A", "B", "C", "Ç", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Button[] botonsLletres = new Button[auxLletres.length];
		Label etiquetaImatgePenjat = new Label();
		etiquetaImatgePenjat.setLayoutX(50);
		etiquetaImatgePenjat.setLayoutY(150);
		Label etiquetaTextPenjat = new Label();
		etiquetaTextPenjat.setLayoutX(400);
		etiquetaTextPenjat.setLayoutY(350);
		etiquetaTextPenjat.setFont(new Font("Tahoma", 25));
		Button bRePlayPenjat = new Button();
		bRePlayPenjat.setLayoutX(270);
		bRePlayPenjat.setLayoutY(420);
		bRePlayPenjat.setText("RePlay");
		bRePlayPenjat.setDisable(true);

		Image penjat0 = new Image(getClass().getResourceAsStream("/images/penjat0.png"));
		ImageView imatgePenjat0 = new ImageView(penjat0);

		// busco la paraula més llarga per a crear l'etiqueta
		paraulaLlarga = auxParaules[0];
		for (int i = 0; i < auxParaules.length; i++) {
			if (auxParaules[i].length() > paraulaLlarga.length()) {
				paraulaLlarga = auxParaules[i];
			}
		}

		// creo l'etiqueta utilitzant la paraula més llarga
		Label[] etiquetaParaula = new Label[paraulaLlarga.length()];

		// inicialitzo l'etiqueta on és mostrarà la paraula a descobrir
		int auxEtiquetaP = 50;
		for (int i = 0; i < paraulaLlarga.length(); i++) {
			etiquetaParaula[i] = new Label();
			etiquetaParaula[i].setLayoutX(300 + i * auxEtiquetaP);
			etiquetaParaula[i].setLayoutY(200);
			etiquetaParaula[i].setFont(new Font("Tahoma", 50));
		}

		Pane decorat4 = new Pane();

		decorat4.setStyle("-fx-background-color: #AEF894");
		Scene escenari4 = new Scene(decorat4, 1000, 500, Color.AZURE);
		teatre.setTitle("Joc del penjat");
		teatre.setScene(escenari4);
		decorat4.getChildren().add(etiquetaImatgePenjat);
		decorat4.getChildren().add(etiquetaTextPenjat);
		decorat4.getChildren().add(bRePlayPenjat);

		// ---BOTONS i PANE DELS
		// CAMELLS----------------------------------------------------
		ArrayList<Integer> guanyadorsCamells = new ArrayList<Integer>();

		Label etiquetaCamells = new Label();
		etiquetaCamells.setText("");
		etiquetaCamells.setLayoutX(550);
		etiquetaCamells.setLayoutY(500);
		etiquetaCamells.setFont(new Font("Tahoma", 24));

		Button botoCamells = new Button();
		botoCamells.setText("PREM AQUÍ");
		botoCamells.setLayoutX(400);
		botoCamells.setLayoutY(500);
		Button rePlayCamells = new Button();
		rePlayCamells.setText("RePlay");
		rePlayCamells.setLayoutX(300);
		rePlayCamells.setLayoutY(500);

		Image camell = new Image(getClass().getResourceAsStream("/images/Camel.png"));
		ImageView[] camells = new ImageView[8];

		Image corona = new Image(getClass().getResourceAsStream("/images/corona.png"));
		ImageView imatgeCorona = new ImageView(corona);

		Image meta = new Image(getClass().getResourceAsStream("/images/linia_meta.gif"));
		ImageView imatgeMeta = new ImageView(meta);

		imatgeMeta.setLayoutX(800);
		imatgeMeta.setLayoutY(40);
		imatgeMeta.setFitHeight(400);

		Pane decorat5 = new Pane();

		decorat5.setStyle("-fx-background-color: #F9CE90");
		Scene escenari5 = new Scene(decorat5, 900, 550, Color.BEIGE);
		teatre.setTitle("Joc dels camells");
		teatre.setScene(escenari5);

		for (int i = 0; i < camells.length; i++) {
			camells[i] = new ImageView(camell);
			camells[i].setFitHeight(50);
			camells[i].setFitWidth(50);
			camells[i].setLayoutX(100);
			camells[i].setLayoutY(50 + (i * 50));
			decorat5.getChildren().add(camells[i]);
		}

		decorat5.getChildren().add(etiquetaCamells);
		decorat5.getChildren().add(botoCamells);
		decorat5.getChildren().add(imatgeMeta);
		decorat5.getChildren().add(rePlayCamells);
		rePlayCamells.setDisable(true);

		// PANTALLA
		// D'INICI----------------------------------------------------------------
		Pane decorat1 = new Pane();

		decorat1.getChildren().add(barraMenu);

		Scene escenari1 = new Scene(decorat1, 600, 500, Color.AZURE);
		teatre.setTitle("INICI");
		teatre.setScene(escenari1);
		teatre.show();

		decorat1.setStyle("-fx-background-color: #000000");
		Image fotoDaus = new Image(getClass().getResourceAsStream("/images/daus.jpg"));
		ImageView imatgeDaus = new ImageView(fotoDaus);
		imatgeDaus.setLayoutY(100);
		imatgeDaus.setLayoutX(100);
		imatgeDaus.setFitHeight(300);
		imatgeDaus.setFitWidth(400);
		decorat1.getChildren().add(imatgeDaus);

		// OPCIONS DEL MENU BAR PER CANVIAR DE
		// JOC----------------------------------------------------------------
		// ---ANEM AL JOC DEL TRES EN
		// RATLLA----------------------------------------------
		opcioTresRatlla.setOnAction((ActionEvent e) -> {
			teatre.setScene(escenari2);
			decorat2.getChildren().add(barraMenu);

			opcioTresRatlla.setDisable(true);
			opcioTresRatllaV.setDisable(false);
			opcioPenjat.setDisable(false);
			opcioCamells.setDisable(false);

			// reinicialitzem el joc
			for (int i = 0; i < 9; i++) {
				botons3R[i].setText(".");
				botons3R[i].setDisable(false);
			}
			etiqueta3R.setText("");
			numTirada3R = 0;
			tirada3R = "x";
			accio3R = 'E'; // escollir
			guanyador3R = false;
			bRePlay3R.setDisable(true);
		});

		// --ANEM AL JOC DEL TRES EN RATLLA
		// VERTICAL-------------------------------------
		opcioTresRatllaV.setOnAction((ActionEvent e) -> {
			teatre.setScene(escenari3);
			decorat3.getChildren().add(barraMenu);

			opcioTresRatlla.setDisable(false);
			opcioTresRatllaV.setDisable(true);
			opcioPenjat.setDisable(false);
			opcioCamells.setDisable(false);

			// reinici del joc
			bRePlay3RV.setDisable(true);
			tirada3RV = "blau";

			// inicialitzo el taulell amb les etiquetes
			for (int i = 0; i < taulell3RV.length; i++) {
				for (int j = 0; j < taulell3RV[i].length; j++) {
					taulell3RV[i][j].setFitHeight(40);
					taulell3RV[i][j].setFitWidth(40);
					taulell3RV[i][j].setLayoutX(100 + (j * 50));
					taulell3RV[i][j].setLayoutY(80 + (i * 50));
					taulell3RV[i][j].setVisible(true);
					taulell3RV[i][j].setImage(cercleBlanc);
				}
			}

			// inicialitzo els botons de poder seleccionar la columna on tirem
			for (int i = 0; i < taulaBotons3RV.length; i++) {
				taulaBotons3RV[i].setDisable(false);
				taulaBotons3RV[i].setGraphic(botonsBlaus[i]);
				taulaBotons3RV[i].setLayoutX(100 + (i * 50));
				taulaBotons3RV[i].setLayoutY(600);
			}

			// poso el comptador de columnes a 0 fitxes
			for (int i = 0; i < 8; i++) {
				comptador3RV[i] = 0;
			}

			etiquetaInfo3RV.setText("");

		});

		// --ANEM AL JOC DEL
		// PENJAT----------------------------------------------------------
		opcioPenjat.setOnAction((ActionEvent e) -> {
			teatre.setScene(escenari4);
			decorat4.getChildren().add(barraMenu);

			opcioTresRatlla.setDisable(false);
			opcioTresRatllaV.setDisable(false);
			opcioPenjat.setDisable(true);
			opcioCamells.setDisable(false);

			// reinici del joc
			paraulaTriada2 = auxParaules[r.nextInt(auxParaules.length)];
			paraulaTriada = paraulaTriada2.toUpperCase();
			auxParaula = "";
			numErrorsPenjat = 0;
			etiquetaImatgePenjat.setGraphic(imatgePenjat0);
			imatgePenjat0.setFitHeight(250);
			imatgePenjat0.setFitWidth(200);
			etiquetaTextPenjat.setText("");

			// creo la paraula auxiliar a partir de la triada
			for (int i = 0; i < paraulaTriada.length(); i++) {
				auxParaula = auxParaula + "_";
			}
			// empleno l'etiqueta amb _ per a saber la longitud de la paraula triada(paraula
			// que mostraré) amb "_";
			for (int i = 0; i < etiquetaParaula.length; i++) {
				if (i < paraulaTriada.length()) {
					etiquetaParaula[i].setText("_");
				} else {
					etiquetaParaula[i].setText("");
				}
			}
			// inicalitzo els botons amb totes les lletres
			for (int i = 0; i < auxLletres.length; i++) {
				botonsLletres[i].setDisable(false);
			}
		});

		// ---ANEM AL JOC DELS
		// CAMELLS-------------------------------------------------------------
		opcioCamells.setOnAction((ActionEvent e) -> {
			teatre.setScene(escenari5);
			decorat5.getChildren().add(barraMenu);

			opcioTresRatlla.setDisable(false);
			opcioTresRatllaV.setDisable(false);
			opcioPenjat.setDisable(false);
			opcioCamells.setDisable(true);

			// reinici del joc
			guanyadorCamells = false;
			guanyadorsCamells.clear();
			etiquetaCamells.setText("");
			for (int i = 0; i < camells.length; i++) {
				camells[i].setImage(camell);
				camells[i].setLayoutX(100);
			}
			botoCamells.setDisable(false);
			rePlayCamells.setDisable(true);
			rePlayCamells.setDisable(true);

		});

		// PROGRAMACIÓ DE TOTS ELS
		// JOCS-------------------------------------------------------
		// --JOC DEL TRES EN
		// RATLLA------------------------------------------------------

		int auxX = 70;
		int multiplicarX = 0;
		for (int i = 0; i < 9; i++) {
			final int k = i;
			// faig un modular per saber per quan he de muliplicar la X
			if (i % 3 == 0) {
				multiplicarX = 0;
			} else if (i % 3 == 1) {
				multiplicarX = 1;
			} else if (i % 3 == 2) {
				multiplicarX = 2;
			}
			// col·loco els botons a la pantalla
			botons3R[i] = new Button();
			if (i == 0 || i == 1 || i == 2) {
				botons3R[i].setLayoutX(200 + multiplicarX * auxX);
				botons3R[i].setLayoutY(130);
			} else if (i == 3 || i == 4 || i == 5) {
				botons3R[i].setLayoutX(200 + multiplicarX * auxX);
				botons3R[i].setLayoutY(200);
			} else if (i == 6 || i == 7 || i == 8) {
				botons3R[i].setLayoutX(200 + multiplicarX * auxX);
				botons3R[i].setLayoutY(270);
			}
			botons3R[i].setPrefHeight(50);
			botons3R[i].setPrefWidth(50);

			botons3R[i].setText(".");
			botons3R[i].setFont(new Font("Tahoma", 24));
			// creo els events dels botons
			botons3R[i].setOnAction((ActionEvent) -> {
				numTirada3R++;
				// fem 6 tirades inicials
				if (numTirada3R <= 6) {
					if (botons3R[k].getText().equals(".")) {
						botons3R[k].setDisable(false);
					} else
						botons3R[k].setDisable(true);

					botons3R[k].setText(tirada3R);
					// a partir de la tirada 5 hem de començar a mirar si hi ha un guanyador
					if (numTirada3R == 5 || numTirada3R == 6) {
						mirarVertical3R(botons3R, etiqueta3R);
						if (!guanyador3R) {
							mirarHoritzontal3R(botons3R, etiqueta3R);
						}
						if (!guanyador3R) {
							mirarDiagonal3R(botons3R, etiqueta3R);
						}
						if (guanyador3R) {
							deshabilitarBotons3R(botons3R);
							bRePlay3R.setDisable(false);
						}
					}
					// canviem de jugador (tirada)
					if (tirada3R.equals("x"))
						tirada3R = "o";
					else
						tirada3R = "x";

					// si la tirada es 6 i no he guanyat, he de deshabilitar els botons
					if (numTirada3R == 6 && !guanyador3R) {
						habilitarBotons3R(botons3R);
					}
				}
				// a partir de la tirada 7 podem canviar la fitxa de lloc
				else if (numTirada3R >= 7 && !guanyador3R) {
					habilitarBotons3R(botons3R);
					if (accio3R == 'E') {
						botons3R[k].setText(".");
						for (int j = 0; j < 9; j++) {
							if (botons3R[j].getText().equals("."))
								botons3R[j].setDisable(false); // aqui veiem si té un punt per a que s'activi per a
																// posteriorment poder colocar la fitxa
							else
								botons3R[j].setDisable(true);
						}
						botons3R[k].setDisable(true);
						accio3R = 'C';
					} else if (accio3R == 'C') {
						botons3R[k].setText(tirada3R);
						mirarVertical3R(botons3R, etiqueta3R);
						if (!guanyador3R) {
							mirarHoritzontal3R(botons3R, etiqueta3R);
						}
						if (!guanyador3R) {
							mirarDiagonal3R(botons3R, etiqueta3R);
						}
						if (guanyador3R) {
							deshabilitarBotons3R(botons3R);
							bRePlay3R.setDisable(false);
						} else {
							if (tirada3R.equals("x"))
								tirada3R = "o"; // s'ha de canviar la tirada
							else
								tirada3R = "x";
							if (botons3R[k].getText().equals(tirada3R))
								botons3R[k].setDisable(false); // s'han de deixar activats nomes les o, s'han d'activar
																// les que no són o.
							else
								botons3R[k].setDisable(true);
							accio3R = 'E';
							habilitarBotons3R(botons3R);
						}
					}
				}
			});
		}
		// botó de reinciar el joc
		bRePlay3R.setOnAction((ActionEvent e) -> {
			// reinicialitzem el joc per quan donem a la opcio
			for (int i = 0; i < 9; i++) {
				botons3R[i].setText(".");
				botons3R[i].setDisable(false);
			}
			etiqueta3R.setText("");
			numTirada3R = 0;
			tirada3R = "x";
			accio3R = 'E'; // escollir
			guanyador3R = false;
			bRePlay3R.setDisable(true);
		});

		// col·loco els botons i l'etiqueta al decorat del 3 en ratlla
		for (int i = 0; i < 9; i++) {
			decorat2.getChildren().add(botons3R[i]);
		}
		decorat2.getChildren().add(etiqueta3R);
		decorat2.getChildren().add(bRePlay3R);

		// ----JOC DEL 3 EN RATLLA
		// VERTICAL----------------------------------------------

		// inicialitzo el taulell amb les etiquetes
		for (int i = 0; i < taulell3RV.length; i++) {
			for (int j = 0; j < taulell3RV[i].length; j++) {
				taulell3RV[i][j] = new ImageView(cercleBlanc);
				taulell3RV[i][j].setFitHeight(40);
				taulell3RV[i][j].setFitWidth(40);
				taulell3RV[i][j].setLayoutX(100 + (j * 50));
				taulell3RV[i][j].setLayoutY(80 + (i * 50));
				taulell3RV[i][j].setVisible(true);
				decorat3.getChildren().add(taulell3RV[i][j]);
			}
		}

		// inicialitzo el comptador de les columnes
		for (int i = 0; i < comptador3RV.length; i++) {
			comptador3RV[i] = 0;
		}

		// inicialitzo els botons de poder seleccionar la columna on tirem
		for (int i = 0; i < taulaBotons3RV.length; i++) {
			taulaBotons3RV[i] = new Button();
			taulaBotons3RV[i].setGraphic(botonsBlaus[i]);
			taulaBotons3RV[i].setLayoutX(100 + (i * 50));
			taulaBotons3RV[i].setLayoutY(600);
			decorat3.getChildren().add(taulaBotons3RV[i]);
			final int r = i;
			// faig l'event dels botons
			taulaBotons3RV[i].setOnAction((ActionEvent e) -> {
				if (tirada3RV.equals("blau")) {
					etiquetaInfo3RV.setText("");
					// col·loquem la fitxa al lloc indicat i sumem la fitxa al comptador de la taula
					taulell3RV[9 - comptador3RV[r]][r].setImage(cercleBlau);
					comptador3RV[r]++;
					if (comptador3RV[r] >= 3) { // si el comptador d'aquella columna és igual o més de 3 hem de
												// comprovar si hi ha 3 en ratlla
						if (taulell3RV[9 - comptador3RV[r] + 1][r].getImage().equals(cercleBlau)) {
							if (taulell3RV[9 - comptador3RV[r] + 2][r].getImage().equals(cercleBlau)) {
								if (taulell3RV[9 - comptador3RV[r] + 3][r].getImage().equals(cercleBlau)) {
									for (int j = 0; j < 8; j++) {
										taulaBotons3RV[j].setDisable(true);
										etiquetaInfo3RV.setText("Guanyador: BLAU");
										bRePlay3RV.setDisable(false);
									}
								}
							}
						}
						if (comptador3RV[r] == 10) { // si en aquella columna hi ha 10 fitxes, deshabilitem el botó
														// perque no s'hi pot tirar més
							taulaBotons3RV[r].setDisable(true);
						}
					}
					tirada3RV = "taronja"; // canviem la tirada i el color dels botons
					for (int j = 0; j < 8; j++) {
						taulaBotons3RV[j].setGraphic(botonsTaronges[j]);
					}
				} else if (tirada3RV.equals("taronja")) {
					etiquetaInfo3RV.setText("");
					taulell3RV[9 - comptador3RV[r]][r].setImage(cercleTaronja);
					comptador3RV[r]++;
					if (comptador3RV[r] >= 3) {
						if (taulell3RV[9 - comptador3RV[r] + 1][r].getImage().equals(cercleTaronja)) {
							if (taulell3RV[9 - comptador3RV[r] + 2][r].getImage().equals(cercleTaronja)) {
								if (taulell3RV[9 - comptador3RV[r] + 3][r].getImage().equals(cercleTaronja)) {
									for (int j = 0; j < 8; j++) {
										taulaBotons3RV[j].setDisable(true);
										etiquetaInfo3RV.setText("Guanyador: TARONJA");
										bRePlay3RV.setDisable(false);
									}
								}
							}
						}
						if (comptador3RV[r] == 10) {
							taulaBotons3RV[r].setDisable(true);
						}
					}
					tirada3RV = "blau";
					for (int j = 0; j < 8; j++) {
						taulaBotons3RV[j].setGraphic(botonsBlaus[j]);
					}
				}
				// si emplenem tot el taulell i no hi ha guanyador, ens deshabilitarà els botons
				// i dirà GAME OVER.
				int comptadorTaulellPle = 0;
				for (int j = 0; j < comptador3RV.length; j++) {
					if (comptador3RV[j] == 10) {
						comptadorTaulellPle++;
						if (comptadorTaulellPle == comptador3RV.length) {
							for (int m = 0; m < 8; m++) {
								taulaBotons3RV[m].setDisable(true);
								etiquetaInfo3RV.setText("GAME OVER");
								bRePlay3RV.setDisable(false);
							}
						}
					}
				}

			});
		}
		// fem el botó de reset per a poder començar la partida si hi ha hagut un
		// guanyador o s'ha omplert el taulell
		bRePlay3RV.setOnAction((ActionEvent e) -> {
			bRePlay3RV.setDisable(true);
			tirada3RV = "blau";
			// inicialitzo el taulell amb les etiquetes
			for (int i = 0; i < taulell3RV.length; i++) {
				for (int j = 0; j < taulell3RV[i].length; j++) {
					taulell3RV[i][j].setFitHeight(40);
					taulell3RV[i][j].setFitWidth(40);
					taulell3RV[i][j].setLayoutX(100 + (j * 50));
					taulell3RV[i][j].setLayoutY(80 + (i * 50));
					taulell3RV[i][j].setVisible(true);
					taulell3RV[i][j].setImage(cercleBlanc);
				}
			}

			// inicialitzo els botons de poder seleccionar la columna on tirem
			for (int i = 0; i < taulaBotons3RV.length; i++) {
				taulaBotons3RV[i].setDisable(false);
				taulaBotons3RV[i].setGraphic(botonsBlaus[i]);
				taulaBotons3RV[i].setLayoutX(100 + (i * 50));
				taulaBotons3RV[i].setLayoutY(600);
			}

			for (int i = 0; i < 8; i++) {
				comptador3RV[i] = 0;
			}

			etiquetaInfo3RV.setText("");

		});

		// ---JOC DEL
		// PENJAT-------------------------------------------------------------

		// col·loco la paraula etiqueta per a que es vegi
		for (int i = 0; i < paraulaLlarga.length(); i++) {
			decorat4.getChildren().add(etiquetaParaula[i]);
		}

		// faig que l'etiqueta mostri el que hi ha a la paraulaAux
		for (int i = 0; i < paraulaTriada.length(); i++) {
			etiquetaParaula[i].setText(String.valueOf(auxParaula.charAt(i)));
		}

		// INICIALITZO ELS BOTONS
		int auxXP = 30;
		for (int i = 0; i < botonsLletres.length; i++) {
			botonsLletres[i] = new Button();
			decorat4.getChildren().add(botonsLletres[i]);
			botonsLletres[i].setText(auxLletres[i]);
			botonsLletres[i].setLayoutY(80);
			botonsLletres[i].setLayoutX(50 + i * auxXP);
			botonsLletres[i].setFont(new Font("Tahoma", 14));
			final int m = i;
			botonsLletres[i].setOnAction((ActionEvent) -> {
				botonsLletres[m].setDisable(true);
				// agafem la lletra del botó i mirem si es troba a la nostra paraula
				String lletraTriada = botonsLletres[m].getText().toUpperCase();
				int pos = paraulaTriada.toUpperCase().indexOf(lletraTriada);
				// si la pos és més gran de -1, vol dir que la lletra hi és
				if (pos > -1) {
					auxParaula = auxParaula.substring(0, pos) + lletraTriada + auxParaula.substring(pos + 1);
					boolean trobat = false;
					// si la troba, ha de mirar la posició on és i ens substitueix aquella lletra a
					// la paraula
					while (!trobat) {
						pos = paraulaTriada.indexOf(lletraTriada, pos + 1);
						if (pos >= 0 && pos < paraulaTriada.length()) {
							auxParaula = auxParaula.substring(0, pos) + lletraTriada + auxParaula.substring(pos + 1);
						} else {
							trobat = true;
						}
					}
					// cada vegada comprovem si la paraula auxiliar (la que mostrem en pantalla) té
					// la lletra i la canviem
					for (int j = 0; j < paraulaTriada.length(); j++) {
						if (auxParaula.charAt(j) != '_')
							etiquetaParaula[j].setText(String.valueOf(paraulaTriada.charAt(j)));
					}
				} else
					numErrorsPenjat++;

				boolean errorImatgesPenjat = false;
				// inicialitzo les imatges del penjat
				try {
					Image penjat1 = new Image(getClass().getResourceAsStream("/images/penjat1.png"));
					ImageView imatgePenjat1 = new ImageView(penjat1);
					Image penjat2 = new Image(getClass().getResourceAsStream("/images/penjat2.png"));
					ImageView imatgePenjat2 = new ImageView(penjat2);
					Image penjat3 = new Image(getClass().getResourceAsStream("/images/penjat3.png"));
					ImageView imatgePenjat3 = new ImageView(penjat3);
					Image penjat4 = new Image(getClass().getResourceAsStream("/images/penjat4.png"));
					ImageView imatgePenjat4 = new ImageView(penjat4);
					Image penjat5 = new Image(getClass().getResourceAsStream("/images/penjat5.png"));
					ImageView imatgePenjat5 = new ImageView(penjat5);
					Image penjat6 = new Image(getClass().getResourceAsStream("/images/penjat6.png"));
					ImageView imatgePenjat6 = new ImageView(penjat6);
					// poso la foto que toca pel penjat quan vaig fallant les lletres
					if (numErrorsPenjat == 1) {
						etiquetaImatgePenjat.setVisible(true);
						etiquetaImatgePenjat.setGraphic(imatgePenjat1);
						imatgePenjat1.setFitHeight(250);
						imatgePenjat1.setFitWidth(200);
					} else if (numErrorsPenjat == 2) {
						etiquetaImatgePenjat.setGraphic(imatgePenjat2);
						imatgePenjat2.setFitHeight(250);
						imatgePenjat2.setFitWidth(200);
					} else if (numErrorsPenjat == 3) {
						etiquetaImatgePenjat.setGraphic(imatgePenjat3);
						imatgePenjat3.setFitHeight(250);
						imatgePenjat3.setFitWidth(200);
					} else if (numErrorsPenjat == 4) {
						etiquetaImatgePenjat.setGraphic(imatgePenjat4);
						imatgePenjat4.setFitHeight(250);
						imatgePenjat4.setFitWidth(200);
					} else if (numErrorsPenjat == 5) {
						etiquetaImatgePenjat.setGraphic(imatgePenjat5);
						imatgePenjat5.setFitHeight(250);
						imatgePenjat5.setFitWidth(200);
					} else if (numErrorsPenjat == 6) {
						etiquetaImatgePenjat.setGraphic(imatgePenjat6);
						imatgePenjat6.setFitHeight(250);
						imatgePenjat6.setFitWidth(200);
						etiquetaTextPenjat.setText("HAS PERDUT! :( \n\nLa paraula era: " + paraulaTriada);
						bRePlayPenjat.setDisable(false);
						for (int j = 0; j < botonsLletres.length; j++) {
							botonsLletres[j].setDisable(true);
						}
					}
				} catch (Exception e1) {
					System.out.println("Imatge no trobada");
					errorImatgesPenjat = true;
				}
				// si fallen les imatges del penjat, es podrà jugar igual i ens sortirà
				// l'etiqueta als 6 errors
				if (errorImatgesPenjat) {
					if (numErrorsPenjat == 6) {
						etiquetaTextPenjat.setText("HAS PERDUT! :( \n\nLa paraula era: " + paraulaTriada);
						bRePlayPenjat.setDisable(false);
						for (int j = 0; j < botonsLletres.length; j++) {
							botonsLletres[j].setDisable(true);
						}
					}
				}

				// si les dues paraules son iguals, vol dir que hem guanyat i desactivem tots
				// els botons
				if (auxParaula.toUpperCase().equals(paraulaTriada.toUpperCase())) {
					for (int j = 0; j < botonsLletres.length; j++) {
						botonsLletres[j].setDisable(true);
					}
					etiquetaTextPenjat.setText("HAS GUANYAT! :)");
					bRePlayPenjat.setDisable(false);
				}
			});
		}

		// Botó de reset del penjat
		bRePlayPenjat.setOnAction((ActionEvent e) -> {
			// reinici del joc
			paraulaTriada2 = auxParaules[r.nextInt(auxParaules.length)];
			paraulaTriada = paraulaTriada2.toUpperCase();
			auxParaula = "";
			numErrorsPenjat = 0;
			etiquetaImatgePenjat.setGraphic(imatgePenjat0);
			imatgePenjat0.setFitHeight(250);
			imatgePenjat0.setFitWidth(200);
			etiquetaTextPenjat.setText("");

			for (int j = 0; j < paraulaTriada.length(); j++) {
				auxParaula = auxParaula + "_";
			}

			// empleno la auxParaula(paraula que mostraré) amb "_";
			for (int i = 0; i < etiquetaParaula.length; i++) {
				if (i < paraulaTriada.length()) {
					etiquetaParaula[i].setText("_");
				} else {
					etiquetaParaula[i].setText("");
				}
			}

			// inicalitzo els botons amb totes les lletres i els seus setOnAction
			for (int i = 0; i < auxLletres.length; i++) {
				botonsLletres[i].setDisable(false);
			}
			bRePlayPenjat.setDisable(true);
		});

		// -- JOC DELS CAMELLS---------------------------------------------------------

		// fem el joc d'anar avançant el camell fins que arriba a la meta
		botoCamells.setOnAction((ActionEvent A1) -> {
			generarTiradaCamells(camells);
			comprovarSiGuanyadorCamells(camells, guanyadorsCamells);

			if (guanyadorCamells) {
				String guanyadorFinal = "";
				guanyadorFinal = triarGuanyadorCamells(guanyadorsCamells, corona, camells);
				etiquetaCamells.setText(guanyadorFinal);

				botoCamells.setDisable(true);
				rePlayCamells.setDisable(false);
			}

		});
		// quan un ha guanyat, renicialitzem el joc
		rePlayCamells.setOnAction((ActionEvent A1) -> {
			guanyadorCamells = false;
			guanyadorsCamells.clear();
			etiquetaCamells.setText("");
			for (int i = 0; i < camells.length; i++) {
				camells[i].setImage(camell);
				camells[i].setLayoutX(100);
			}
			botoCamells.setDisable(false);
			rePlayCamells.setDisable(true);
		});
	}

	// --FUNCIONS 3 EN
	// RATLLA-----------------------------------------------------------
	public void habilitarBotons3R(Button[] botons) {
		for (int i = 0; i < 9; i++) {
			if (botons[i].getText().equals(tirada3R))
				botons[i].setDisable(false);
			if (!botons[i].getText().equals(tirada3R))
				botons[i].setDisable(true);
		}
	}

	public void mirarVertical3R(Button[] botons, Label etiqueta) {

		if (botons[0].getText().equals(tirada3R) && botons[3].getText().equals(tirada3R)
				&& botons[6].getText().equals(tirada3R)) {
			guanyador3R = true;
			etiqueta.setText("Guanyador Jugador: " + tirada3R);
		} else if (botons[1].getText().equals(tirada3R) && botons[4].getText().equals(tirada3R)
				&& botons[7].getText().equals(tirada3R) && !guanyador3R) {
			guanyador3R = true;
			etiqueta.setText("Guanyador Jugador: " + tirada3R);
		} else if (botons[2].getText().equals(tirada3R) && botons[5].getText().equals(tirada3R)
				&& botons[8].getText().equals(tirada3R) && !guanyador3R) {
			guanyador3R = true;
			etiqueta.setText("Guanyador Jugador: " + tirada3R);
		}
	}

	public void mirarHoritzontal3R(Button[] botons, Label etiqueta) {
		if (botons[0].getText().equals(tirada3R) && botons[1].getText().equals(tirada3R)
				&& botons[2].getText().equals(tirada3R)) {
			guanyador3R = true;
			etiqueta.setText("Guanyador Jugador: " + tirada3R);
		} else if (botons[3].getText().equals(tirada3R) && botons[4].getText().equals(tirada3R)
				&& botons[5].getText().equals(tirada3R) && !guanyador3R) {
			guanyador3R = true;
			etiqueta.setText("Guanyador Jugador: " + tirada3R);
		} else if (botons[6].getText().equals(tirada3R) && botons[7].getText().equals(tirada3R)
				&& botons[8].getText().equals(tirada3R) && !guanyador3R) {
			guanyador3R = true;
			etiqueta.setText("Guanyador Jugador: " + tirada3R);
		}
	}

	public void mirarDiagonal3R(Button[] botons, Label etiqueta) {
		if (botons[0].getText().equals(tirada3R) && botons[4].getText().equals(tirada3R)
				&& botons[8].getText().equals(tirada3R)) {
			guanyador3R = true;
			etiqueta.setText("Guanyador Jugador: " + tirada3R);
		} else if (botons[2].getText().equals(tirada3R) && botons[4].getText().equals(tirada3R)
				&& botons[6].getText().equals(tirada3R) && !guanyador3R) {
			guanyador3R = true;
			etiqueta.setText("Guanyador Jugador: " + tirada3R);
		}
	}

	public void deshabilitarBotons3R(Button[] botons) {
		for (int i = 0; i < 9; i++) {
			botons[i].setDisable(true);
		}
	}

	// FUNCIONS JOC DELS
	// CAMELLS--------------------------------------------------------
	static void generarTiradaCamells(ImageView[] taula) {
		Random r = new Random();

		for (int i = 0; i < taula.length; i++) {
			int tirada = r.nextInt(5);
			taula[i].setLayoutX(taula[i].getLayoutX() + (tirada * 35));
			if (taula[i].getLayoutX() >= 800) {
				taula[i].setLayoutX(800);
			}
		}
	}

	static void comprovarSiGuanyadorCamells(ImageView[] taula, ArrayList guanyadors) {

		for (int i = 0; i < taula.length; i++) {
			if (taula[i].getLayoutX() == 800) {
				guanyadorCamells = true;
				guanyadors.add(i + 1);
			}
		}
	}

	static String triarGuanyadorCamells(ArrayList guanyadors, Image corona, ImageView[] camells) {

		int pos = posGuanyadorCamells(guanyadors);
		String guanyadorFinal = "";
		int valor = (Integer) guanyadors.get(pos);
		guanyadorFinal = "El guanyador és: " + guanyadors.get(pos);
		camells[valor - 1].setImage(corona);

		return guanyadorFinal;
	}

	static int posGuanyadorCamells(ArrayList guanyadors) {
		int pos = 0;
		Random r = new Random();
		int random = 0;

		if (guanyadors.size() > 1) {
			random = r.nextInt(guanyadors.size());
			pos = random;
		} else
			pos = 0;

		return pos;
	}
}
