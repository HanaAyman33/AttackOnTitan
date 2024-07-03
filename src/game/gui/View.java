package game.gui;


import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import game.engine.titans.ColossalTitan;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class View {
	private AnchorPane Scene1;
	private AnchorPane EasyScene;
	private AnchorPane HardScene;
	private AnchorPane GameInstructionScene;
	private AnchorPane GameOverScene;
	private BorderPane WeaponShop; //i changed this to border pane so we have both weapon shop and weapons purchased
	private HBox info;	//player's 
	private String Score;
	private String Turn;
	private String Resources;
	private String Phase;
	private String Lanes;
	private AnchorPane InsufficientResourcesPopUp;
	private AnchorPane InvalidLanePopUp;
	private AnchorPane PassOrBuyPopUp;
	private AnchorPane SelectLanePopUpEasy;
	private AnchorPane SelectLanePopUpHard;
	private AnchorPane SelectModePopUp;
	private AnchorPane SelectWeaponPopUp;
	private ComboBox<String> Mode;
	private ComboBox<String> EasyLanes;
	private ComboBox<String> HardLanes;
	private Button next;
	private Button Start;
	private Button pass;
	private Button buy;
	private Button returntoStart;
	private Button PiercingCannon;
	private Button SniperCannon;
	private Button VolleySpread;
	private Button WallTrap;
	private Button deployWeapon1;
	private Button deployWeapon2;
	private static ImageView abnormal;
	private static ImageView armored;
	private static ImageView colossal;
	private static ImageView pure;
	private Label score;
	private Label turn;
	private Label phase;
	private Label resources;
	private Label lanes;
	private Label score2;
	private Label turn2;
	private Label phase2;
	private Label resources2;
	private Label lanes2;
	private Label selectALane1;
	private Label selectALane2;
	private Button weaponShopButtonEasy;
	private Button weaponShopButtonHard;
	private ArrayList <ImageView> ApproachingTitans;
	private ArrayList <Label> ApproachingTitansHealth;
	private static ArrayList <ImageView> TitansLane1Original;
	private static ArrayList <ImageView> TitansLane2Original;
	private static ArrayList <ImageView> TitansLane3Original;
	private static ArrayList <ImageView> TitansLane4Original;
	private static ArrayList <ImageView> TitansLane5Original;
	private ArrayList <Label> TitansHealthLane1;
	private ArrayList <Label> TitansHealthLane2;
	private ArrayList <Label> TitansHealthLane3;
	private ArrayList <Label> TitansHealthLane4;
	private ArrayList <Label> TitansHealthLane5;
	private ArrayList<Integer> laneCode;
	private int numberOfTitansPerTurn;
	private int speedColossal=15;
	private GridPane hard;
	private GridPane easy;
	private Label scorefinal;
	private ArrayList<ArrayList<TitanImageView>> allLanes;
	private String instructions="1. **Objective**:\n" +
    	    "   - Your goal is to defend the Utopia District Walls from incoming titan attacks for as long as possible.\n\n" +
    	    "2. **Game Setup**:\n" +
    	    "   - Titans are approaching the walls from multiple lanes.\n" +
    	    "   - You have the option to purchase and deploy different types of weapons to defend the walls.\n\n" +
    	    "3. **Turn Actions**:\n" +
    	    "   - On each turn, you can either:\n" +
    	    "   - Purchase and deploy a weapon.\n" +
    	    "   - Pass your turn without taking any actions.\n" +
    	    "   - After your action (or pass), titans will move closer to the walls, and weapons will attack them.\n" +
    	    "   - Titans will then attack the walls.\n" +
    	    "   - New titans may be added to the lanes based on the game phase and elapsed turns.\n\n" +
    	    "4. **Winning and Losing**:\n" +
    	    "   - There's no winning condition; your goal is to survive for as many turns as possible.\n" +
    	    "   - You lose when all starting lanes have their wall parts destroyed.\n"+
    	    "     Your final score is based on the number of defeated titans.\n\n" +
    	    "5. **Enemy Characters (Titans)**:\n" +
    	    "   - Titans come in different types with varying stats and special traits.\n" +
    	    "   - Defeat titans by reducing their health points to zero. Each defeated titan adds to your resources and score.\n\n" +
    	    "6. **Friendly Pieces (Weapons)**:\n" +
    	    "   - Deploy various types of weapons to attack incoming titans.\n" +
    	    "   - Each weapon type has different attack actions and ranges. Choose strategically based on the situation.\n\n" +
    	    "7. **Game Phases**:\n" +
    	    "   - The game progresses through three phases: Early, Intense, and Grumbling.\n"+
    	    "   The number and types of titans added to lanes change based on the phase and elapsed turns.\n\n" +
    	    "8. **Resource Management**:\n" +
    	    "   - Manage your resources effectively to purchase and deploy weapons.\n" +
    	    "   - Your resources are deducted when purchasing weapons and increased when defeating titans.\n\n" +
    	    "Remember to strategize carefully, prioritize defending vulnerable lanes, and adapt your tactics as the game progresses.\n"+
    	    "Good luck defending the walls of Utopia District!";
	
	
	public ArrayList<ArrayList<TitanImageView>> getAllLanes() {
		return allLanes;
	}
	public void setAllLanes(ArrayList<ArrayList<TitanImageView>> allLanes) {
		this.allLanes = allLanes;
	}
	public Label getScorefinal() {
		return scorefinal;
	}
	public Label getSelectALane1() {
		return selectALane1;
	}
	public void setApproachingTitansHealth(ArrayList<Label> approachingTitansHealth) {
		ApproachingTitansHealth = approachingTitansHealth;
	}
	public Label getSelectALane2() {
		return selectALane2;
	}
	public Button getDeployWeapon1() {
		return deployWeapon1;
	}
	public Button getDeployWeapon2() {
		return deployWeapon2;
	}
	public String getEasyLanes() {
		int lastIndex = ((String)EasyLanes.getValue()).lastIndexOf(' ');
		return ((String)EasyLanes.getValue()).substring(lastIndex + 1);
	}
	public String getHardLanes() {
		int lastIndex = ((String)HardLanes.getValue()).lastIndexOf(' ');
		return ((String)HardLanes.getValue()).substring(lastIndex + 1);
	}
	public Button getPiercingCannon() {
		return PiercingCannon;
	}
	public Button getSniperCannon() {
		return SniperCannon;
	}
	public Button getVolleySpread() {
		return VolleySpread;
	}
	public Button getWallTrap() {
		return WallTrap;
	}
	public int getNumberOfTitansPerTurn() {
		return numberOfTitansPerTurn;
	}
	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}
	public ImageView getAbnormal() {
		return abnormal;
	}
	public void setAbnormal(ImageView abnormal) {
		this.abnormal = abnormal;
	}
	public ImageView getArmored() {
		return armored;
	}
	public void setArmored(ImageView armored) {
		this.armored = armored;
	}
	public ImageView getColossal() {
		return colossal;
	}
	public void setColossal(ImageView colossal) {
		this.colossal = colossal;
	}
	public ImageView getPure() {
		return pure;
	}
	public void setPure(ImageView pure) {
		this.pure = pure;
	}
	public String getScore() {
		return Score;
	}
	public void setScore(String score) {
		Score = score;
	}
	public String getTurn() {
		return Turn;
	}
	public void setTurn(String turn) {
		Turn = turn;
	}
	public String getResources() {
		return Resources;
	}
	public void setResources(String resources) {
		Resources = resources;
	}
	public String getPhase() {
		return Phase;
	}
	public void setPhase(String phase) {
		Phase = phase;
	}
	public String getLanes() {
		return Lanes;
	}
	public void setLanes(String lanes) {
		Lanes = lanes;
	}
	public Button getPass() {
		return pass;
	}
	public Button getBuy() {
		return buy;
	}
	public Button getStart() {
		return Start;
	}
	public Button getNext() {
		return next;
	}
	
	public String getMode() {
		return (String) Mode.getValue();
	}
	public Button getReturntoStart() {
		return returntoStart;
	}
	
	public void setApproachingTitans(ArrayList<ImageView> approachingTitans) {
		ApproachingTitans = approachingTitans;
	}
	public ArrayList<Integer> getLaneCode() {
		return laneCode;
	}
	public void setLaneCode(ArrayList<Integer> laneCode) {
		this.laneCode = laneCode;
	}
	public ArrayList<ImageView> getApproachingTitans() {
		return ApproachingTitans;
	}
	public View(){

		Scene1 = new AnchorPane();
		Scene1.setPrefSize(1200, 700);
		
		EasyScene = new AnchorPane();
		EasyScene.setPrefSize(1200, 700);
		
		HardScene = new AnchorPane();
		HardScene.setPrefSize(1200, 700);
		
		GameInstructionScene = new AnchorPane();
		GameInstructionScene.setPrefSize(1200,700);
		
		WeaponShop = new BorderPane();
		WeaponShop.setPrefSize(1200, 700);
		
		InsufficientResourcesPopUp = new AnchorPane();
		InsufficientResourcesPopUp.setPrefSize(600, 200);
		
		InvalidLanePopUp=new AnchorPane();
		InvalidLanePopUp.setPrefSize(600, 200);
		
		PassOrBuyPopUp=new AnchorPane();
		PassOrBuyPopUp.setPrefSize(600, 200);
		
		SelectLanePopUpEasy=new AnchorPane();
		SelectLanePopUpEasy.setPrefSize(600, 300);
		
		SelectLanePopUpHard=new AnchorPane();
		SelectLanePopUpHard.setPrefSize(600, 300);
		
		SelectModePopUp=new AnchorPane();
		SelectModePopUp.setPrefSize(600, 200);
		
		SelectWeaponPopUp=new AnchorPane();
		SelectWeaponPopUp.setPrefSize(600, 200);
		
		GameOverScene=new AnchorPane();
		GameOverScene.setPrefSize(1200,700);
		
		this.TitansLane1Original=new ArrayList();
		this.TitansLane2Original=new ArrayList();
		this.TitansLane3Original=new ArrayList();
		this.TitansLane4Original=new ArrayList();
		this.TitansLane5Original=new ArrayList();
		this.TitansHealthLane1=new ArrayList();
		this.TitansHealthLane2=new ArrayList();
		this.TitansHealthLane3=new ArrayList();
		this.TitansHealthLane4=new ArrayList();
		this.TitansHealthLane5=new ArrayList();
		Image Abnormal = new Image("abnormal.png");
		Image Armored = new Image("armored.png");
		  Image Colossal = new Image("colossal.png");
		  Image Pure = new Image("pure.png");
		  abnormal = new ImageView(Abnormal);
		  armored = new ImageView(Armored);
		  colossal = new ImageView(Colossal);
		  pure = new ImageView(Pure);
		  pure.setPreserveRatio(true);
		  abnormal.setPreserveRatio(true);
		  armored.setPreserveRatio(true);
		  colossal.setPreserveRatio(true);
		  pure.setFitHeight(100);//15
		  abnormal.setFitHeight(80);//10
		  armored.setFitHeight(100);//15
		  colossal.setFitHeight(210);//60
		
	}
	public void addAllComponents(){
		//Scene1 setup
	      next = new Button();
		  next.setText("Next");
		  next.setPrefSize(70,40);
		  next.setFont(new Font(22));
		  next.setAlignment(Pos.CENTER);
		
	      Mode = new ComboBox();
		  Mode.setPromptText("Mode");
		  Mode.getItems().add("Easy");
		  Mode.getItems().add("Hard");
		  Mode.setPrefSize(100,25);
		
		  Label label1 = new Label();
		  label1.setText("Select a game mode");
		  label1.setFont(new Font(30));
		  label1.setTextFill(Color.WHITE);
		  label1.setAlignment(Pos.CENTER);
		  Media media = new Media(getClass().getResource("/backVid.mp4").toExternalForm());
		  MediaPlayer mediaPlayer = new MediaPlayer(media);
		  MediaView backgroundVideo = new MediaView(mediaPlayer);
		  backgroundVideo.setPreserveRatio(false);
		  backgroundVideo.fitWidthProperty().bind(Scene1.widthProperty());
		  backgroundVideo.fitHeightProperty().bind(Scene1.heightProperty());
		  mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		  mediaPlayer.setAutoPlay(true);
		  mediaPlayer.play();

		  Scene1.getChildren().add(0, backgroundVideo);
		  Scene1.getChildren().addAll(Mode,label1,next);
		  Scene1.setTopAnchor(backgroundVideo, 0.0);
		  Scene1.setBottomAnchor(backgroundVideo, 0.0);
		  Scene1.setLeftAnchor(backgroundVideo, 0.0);
		  Scene1.setRightAnchor(backgroundVideo, 0.0);


		  Scene1.setLeftAnchor(label1, (double)400);
		  Scene1.setRightAnchor(label1, (double) 430);
		  Scene1.setTopAnchor(label1, (double) 200);
		
		  Scene1.setLeftAnchor(Mode, (double) 500);
		  Scene1.setRightAnchor(Mode, (double) 530);
		  Scene1.setTopAnchor(Mode, (double) 300);
		
		  Scene1.setLeftAnchor(next, (double) 500);
		  Scene1.setRightAnchor(next, (double) 530);
		  Scene1.setTopAnchor(next, (double) 500);
		
		//GameInstructionsScene set up
		
		  Start = new Button();
		  Start.setText("Start");
		  Start.setPrefSize(70,40);
		  Start.setFont(new Font(22));
		  Start.setAlignment(Pos.CENTER);
			
		  TextArea instructions = new TextArea();
		  instructions.setEditable(false);
		  instructions.setText(this.instructions);
		  instructions.setFont(new Font(22));
		  Image background2 = new Image("mainBackground.jpg");
		  ImageView BackGround2 = new ImageView(background2);
		  BackGround2.setFitHeight(700);
		  BackGround2.setFitWidth(1200);
		  GameInstructionScene.getChildren().add(0, BackGround2);
		  GameInstructionScene.getChildren().addAll(Start,instructions);
			
	      GameInstructionScene.setLeftAnchor(Start, (double) 550);
		  GameInstructionScene.setRightAnchor(Start, (double)550);
		  GameInstructionScene.setBottomAnchor(Start, (double)100);
		  
		  GameInstructionScene.setLeftAnchor(instructions, (double)0);
		  GameInstructionScene.setRightAnchor(instructions, (double) 0);
		  GameInstructionScene.setTopAnchor(instructions, (double) 0);
		  GameInstructionScene.setBottomAnchor(instructions, (double) 200);
			
			//HardScene setup
		  weaponShopButtonHard = new Button();
		  weaponShopButtonHard.setText("Weapon Shop");
		  weaponShopButtonHard.setPrefSize(300,25);
		  weaponShopButtonHard.setMaxSize(300,25);
		  weaponShopButtonHard.setMinSize(300,25);
		  weaponShopButtonHard.setFont(new Font(13));
		  weaponShopButtonHard.setAlignment(Pos.CENTER);
		  hard= new GridPane();
		  hard.setPadding(new Insets(50));
		  hard.setStyle("-fx-background-color: transparent;");
		  for (int i = 0; i < 10; i++) {
	            RowConstraints row = new RowConstraints();
	            row.setPercentHeight(20); 
	            hard.getRowConstraints().add(row);

	            ColumnConstraints column = new ColumnConstraints();
	            column.setPercentWidth(10); 
	            hard.getColumnConstraints().add(column);
	        }
		  hard.setVgap(100); 
		  hard.setHgap(0);
		  hard.setPrefSize(100, 700);
		  Image background = new Image("lane5.png");
		  ImageView BackGround = new ImageView(background);
		  BackGround.setFitHeight(700);
		  BackGround.setFitWidth(1200);
		  HardScene.getChildren().add(0, BackGround);
		  HardScene.getChildren().addAll(weaponShopButtonHard,hard);
		  weaponShopButtonHard.setTranslateX(700);
		  HardScene.setLeftAnchor(hard, (double)0);
		  HardScene.setRightAnchor(hard, (double) 1050);
		  HardScene.setBottomAnchor(hard, (double) 100);
		  HardScene.setTopAnchor(hard, (double) 100);
		  hard.setTranslateX(-20);
		  hard.setTranslateY(-165);
		  BackGround.toBack();
		  
			//EasyScene setup
		  weaponShopButtonEasy = new Button();
		  weaponShopButtonEasy.setText("Weapon Shop");
		  weaponShopButtonEasy.setPrefSize(300,25);
		  weaponShopButtonEasy.setMaxSize(300,25);
		  weaponShopButtonEasy.setMinSize(300,25);
		  weaponShopButtonEasy.setFont(new Font(13));
		  weaponShopButtonEasy.setAlignment(Pos.CENTER);
		  easy= new GridPane();
		  easy.setPadding(new Insets(30));
		  easy.setStyle("-fx-background-color: transparent;");
		  for (int i = 0; i < 10; i++) {
	            RowConstraints row = new RowConstraints();
	            row.setPercentHeight(33); 
	            easy.getRowConstraints().add(row);

	            ColumnConstraints column = new ColumnConstraints();
	            column.setPercentWidth(10); 
	            easy.getColumnConstraints().add(column);
	        }
		  easy.setVgap(100); 
		  easy.setHgap(0); 
		  easy.setPrefSize(100, 700);
		  Image background3 = new Image("3lanes.png");
		  ImageView BackGround3 = new ImageView(background3);
		  BackGround3.setFitHeight(700);
		  BackGround3.setFitWidth(1200);
		  EasyScene.getChildren().add(0, BackGround3);
		  EasyScene.getChildren().addAll(weaponShopButtonEasy,easy);
		  weaponShopButtonEasy.setTranslateX(700);
		  EasyScene.setLeftAnchor(easy, (double)0);
		  EasyScene.setRightAnchor(easy, (double) 850);
		  EasyScene.setBottomAnchor(easy, (double) 100);
		  EasyScene.setTopAnchor(easy, (double) 200);
		  easy.setTranslateY(-165);
		  BackGround3.toBack();
		  //InsufficientResourcesPopUp setup
		  
		  Label label4 = new Label();
		  label4.setText("you do not have enough resources to buy this weapon");
		  label4.setFont(new Font(30));
		  label4.setAlignment(Pos.CENTER);
			
		  InsufficientResourcesPopUp.getChildren().add(label4);
		  InsufficientResourcesPopUp.setLeftAnchor(label4, (double)100);
		  InsufficientResourcesPopUp.setRightAnchor(label4, (double) 100);
		  InsufficientResourcesPopUp.setTopAnchor(label4, (double) 50);
			
			//InvalidLanePopUp set up
		  
		  Label label5 = new Label();
		  label5.setText("you cannot add a weapon in this lane");
		  label5.setFont(new Font(30));
		  label5.setAlignment(Pos.CENTER);
			
		  InvalidLanePopUp.getChildren().add(label5);
		  InvalidLanePopUp.setLeftAnchor(label5, (double)100);
		  InvalidLanePopUp.setRightAnchor(label5, (double) 100);
		  InvalidLanePopUp.setTopAnchor(label5, (double) 50);
			
		  //PassOrBuyPopUp setup
		  
		  pass = new Button();
		  pass.setText("pass");
		  pass.setPrefSize(70,40);
		  pass.setFont(new Font(22));
		  pass.setAlignment(Pos.CENTER);
		  
		  buy = new Button();
		  buy.setText("buy");
		  buy.setPrefSize(70,40);
		  buy.setFont(new Font(22));
		  buy.setAlignment(Pos.CENTER);
		  
		  Label label = new Label();
		  label.setText("do you want to buy a weapon or pass");
		  label.setFont(new Font(30));
		  label.setAlignment(Pos.CENTER);
			
		  PassOrBuyPopUp.getChildren().addAll(label,pass,buy);
		  PassOrBuyPopUp.setLeftAnchor(label, (double)50);
		  PassOrBuyPopUp.setRightAnchor(label, (double) 50);
		  PassOrBuyPopUp.setTopAnchor(label, (double) 50);
		  
		  PassOrBuyPopUp.setLeftAnchor(pass, (double)420);
		  PassOrBuyPopUp.setRightAnchor(pass, (double)100 );
		  PassOrBuyPopUp.setTopAnchor(pass, (double) 125);
		  
		  PassOrBuyPopUp.setLeftAnchor(buy, (double)100);
		  PassOrBuyPopUp.setRightAnchor(buy, (double) 420);
		  PassOrBuyPopUp.setTopAnchor(buy, (double) 125);  	
		  
		  //SelectLanePopUpEasy setup
		  
		  deployWeapon1=new Button();
		  deployWeapon1.setText("purchase");
		  deployWeapon1.setPrefSize(80,40);
		  deployWeapon1.setFont(new Font(22));
		  deployWeapon1.setAlignment(Pos.CENTER);
		  Label label6 = new Label();
		  label6.setText("Select a lane");
		  label6.setFont(new Font(22));
		  label6.setAlignment(Pos.CENTER);
		  selectALane1 = new Label();
		  selectALane1.setText("");
		  selectALane1.setFont(new Font(22));
		  selectALane1.setAlignment(Pos.CENTER);
		  EasyLanes = new ComboBox();
		  EasyLanes.setPromptText("Lanes");
		  EasyLanes.getItems().add("Lane 1");
		  EasyLanes.getItems().add("Lane 2");
		  EasyLanes.getItems().add("Lane 3");
		  EasyLanes.setPrefSize(200,25);
		  SelectLanePopUpEasy.getChildren().addAll(label6,EasyLanes,deployWeapon1,selectALane1);
		  SelectLanePopUpEasy.setLeftAnchor(label6, (double)200);
		  SelectLanePopUpEasy.setRightAnchor(label6, (double) 200);
		  SelectLanePopUpEasy.setTopAnchor(label6, (double) 50);
		  
		  SelectLanePopUpEasy.setLeftAnchor(EasyLanes, (double)200);
		  SelectLanePopUpEasy.setRightAnchor(EasyLanes, (double) 200);
		  SelectLanePopUpEasy.setTopAnchor(EasyLanes, (double) 150);
		  
		  SelectLanePopUpEasy.setLeftAnchor(selectALane1, (double)200);
		  SelectLanePopUpEasy.setRightAnchor(selectALane1, (double) 200);
		  SelectLanePopUpEasy.setTopAnchor(selectALane1, (double) 200);
		  
		  SelectLanePopUpEasy.setLeftAnchor(deployWeapon1, (double) 200);
		  SelectLanePopUpEasy.setRightAnchor(deployWeapon1, (double) 200);
		  SelectLanePopUpEasy.setTopAnchor(deployWeapon1, (double) 250);
		  
		  //SelectLanePopUpHard setup
		  deployWeapon2=new Button();
		  deployWeapon2.setText("Buy");
		  deployWeapon2.setPrefSize(80,40);
		  deployWeapon2.setFont(new Font(22));
		  deployWeapon2.setAlignment(Pos.CENTER);
		  selectALane2 = new Label();
		  selectALane2.setText("");
		  selectALane2.setFont(new Font(22));
		  selectALane2.setAlignment(Pos.CENTER);
		  Label label11 = new Label();
		  label11.setText("Select a lane");
		  label11.setFont(new Font(30));
		  label11.setAlignment(Pos.CENTER);
		  HardLanes = new ComboBox();
		  HardLanes.setPromptText("Lanes");
		  HardLanes.getItems().add("Lane 1");
		  HardLanes.getItems().add("Lane 2");
		  HardLanes.getItems().add("Lane 3");
		  HardLanes.getItems().add("Lane 4");
		  HardLanes.getItems().add("Lane 5");
		  HardLanes.setPrefSize(200,25);
		  SelectLanePopUpHard.getChildren().addAll(label11,HardLanes,deployWeapon2,selectALane2);
		  SelectLanePopUpHard.setLeftAnchor(label11, (double)200);
		  SelectLanePopUpHard.setRightAnchor(label11, (double) 200);
		  SelectLanePopUpHard.setTopAnchor(label11, (double) 50);
		  
		  SelectLanePopUpHard.setLeftAnchor(HardLanes, (double)200);
		  SelectLanePopUpHard.setRightAnchor(HardLanes, (double) 200);
		  SelectLanePopUpHard.setTopAnchor(HardLanes, (double) 150);
		  
		  SelectLanePopUpHard.setLeftAnchor(deployWeapon2, (double) 265);
		  SelectLanePopUpHard.setRightAnchor(deployWeapon2, (double) 265);
		  SelectLanePopUpHard.setTopAnchor(deployWeapon2, (double) 250);
		  
		  SelectLanePopUpHard.setLeftAnchor(selectALane2, (double)200);
		  SelectLanePopUpHard.setRightAnchor(selectALane2, (double) 200);
		  SelectLanePopUpHard.setTopAnchor(selectALane2, (double) 200);
		
		  
		  //SelectModePopUp setup
		  
		  Label label7 = new Label();
		  label7.setText("please select a Mode first");
		  label7.setFont(new Font(30));
		  label7.setAlignment(Pos.CENTER);
			
		  SelectModePopUp.getChildren().add(label7);
		  SelectModePopUp.setLeftAnchor(label7, (double)100);
		  SelectModePopUp.setRightAnchor(label7, (double) 100);
		  SelectModePopUp.setTopAnchor(label7, (double) 50);
		
		  //SelectWeaponPopUp setup
		  
		  Label label8 = new Label();
		  label8.setText("please select a weapon first");
		  label8.setFont(new Font(30));
		  label8.setAlignment(Pos.CENTER);
			
		  SelectWeaponPopUp.getChildren().add(label8);
		  SelectWeaponPopUp.setLeftAnchor(label8, (double)100);
		  SelectWeaponPopUp.setRightAnchor(label8, (double) 100);
		  SelectWeaponPopUp.setTopAnchor(label8, (double) 50);
		  
		  //The Weapon Shop setup
		  
		  Label label9 = new Label();
		  label9.setText("Welcome to the weapon shop");
		  label9.setFont(new Font(22));
		  label9.setVisible(true);
		  label9.setLayoutX(0);
		  label9.setLayoutY(750);
		  PiercingCannon = new Button();		//Weapon 1
		  PiercingCannon.setText("Anti-Titan Shell"+"\n"+"Price: 25"+"\n"+"Damage: 10");
		  PiercingCannon.setPrefSize(200,170);
		  PiercingCannon.setFont(new Font(22));
		  PiercingCannon.setAlignment(Pos.CENTER);
		  SniperCannon = new Button();	//Weapon 2
		  SniperCannon.setText("Long Range Spear"+"\n"+"Price: 25"+"\n"+"Damage: 35");
		  SniperCannon.setPrefSize(200,170);
		  SniperCannon.setFont(new Font(22));
		  SniperCannon.setAlignment(Pos.CENTER);
		  VolleySpread = new Button();	//Weapon 3
		  VolleySpread.setText("Wall Spread Cannon"+"\n"+"Price: 100"+"\n"+"Damage: 5"+"\n"+"Range: 20-25");
		  VolleySpread.setPrefSize(200,170);
		  VolleySpread.setFont(new Font(22));
		  VolleySpread.setAlignment(Pos.CENTER);
		  WallTrap = new Button();	//Weapon 4
		  WallTrap.setText("Proximity Trap"+"\n"+"Price: 75"+"\n"+"Damage: 100");
		  WallTrap.setPrefSize(200,170);
		  WallTrap.setFont(new Font(22));
		  WallTrap.setAlignment(Pos.CENTER);
		  WeaponShop.setTop(label9);
		  WeaponShop.setAlignment(label9, Pos.CENTER);
		  HBox topWeapons = new HBox(PiercingCannon, SniperCannon);
		  topWeapons.setSpacing(200);
		  topWeapons.setAlignment(Pos.CENTER);
		  HBox bottomWeapons = new HBox(VolleySpread, WallTrap);
		  bottomWeapons.setSpacing(200);
		  bottomWeapons.setAlignment(Pos.CENTER);
		  
		  VBox weaponLayout = new VBox(topWeapons, bottomWeapons);
		  weaponLayout.setSpacing(200);
		  weaponLayout.setAlignment(Pos.CENTER);
		  WeaponShop.setCenter(weaponLayout);
		  WeaponShop.setAlignment(weaponLayout, Pos.CENTER);
		  
		  
		  //Player's score,health,turn,resources,weapons and lanes
		  
		  score = new Label();
		  score.setText("Score: "+Score);
		  score.setTextFill(Color.WHITE);
		  score.setFont(new Font(13));
		  turn = new Label();
		  turn.setText("Turn: "+Turn);
		  turn.setFont(new Font(13));
		  turn.setTextFill(Color.WHITE);
		  phase = new Label();
		  phase.setText("Phase: "+Phase);
		  phase.setFont(new Font(13));
		  phase.setTextFill(Color.WHITE);
		  resources = new Label();
		  resources.setText("Resources: "+Resources);
		  resources.setFont(new Font(13));
		  resources.setTextFill(Color.WHITE);
		  lanes = new Label();
		  lanes.setText("Lanes: "+Lanes);
		  lanes.setTextFill(Color.WHITE);
		  lanes.setFont(new Font(13));
		  info = new HBox(score,turn,phase,resources,lanes);
		  info.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		  info.setSpacing(40);
		  info.setPrefSize(500,25);
		  info.setMinWidth(700);
		  info.setMaxWidth(700);
		  info.setMinHeight(25);
		  info.setMaxHeight(25);
		  info.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		  HardScene.getChildren().add(info);
		  HardScene.setLeftAnchor(info, (double)0);
		  HardScene.setRightAnchor(info, (double)700);
		  HardScene.setTopAnchor(info, (double) 0);
		  HardScene.setBottomAnchor(info, (double) 1000);
		  
		  score2 = new Label();
		  score2.setText("Score: "+Score);
		  score2.setTextFill(Color.WHITE);
		  score2.setFont(new Font(13));
		  turn2 = new Label();
		  turn2.setText("Turn: "+Turn);
		  turn2.setFont(new Font(13));
		  turn2.setTextFill(Color.WHITE);
		  phase2 = new Label();
		  phase2.setText("Phase: "+Phase);
		  phase2.setFont(new Font(13));
		  phase2.setTextFill(Color.WHITE);
		  resources2 = new Label();
		  resources2.setText("Resources: "+Resources);
		  resources2.setFont(new Font(13));
		  resources2.setTextFill(Color.WHITE);
		  lanes2 = new Label();
		  lanes2.setText("Lanes: "+Lanes);
		  lanes2.setTextFill(Color.WHITE);
		  lanes2.setFont(new Font(13));
		  HBox info2=new HBox(score2,turn2,phase2,resources2,lanes2);
		  info2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		  info2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		  info2.setSpacing(40);
		  EasyScene.getChildren().add(info2);
		  EasyScene.setLeftAnchor(info2, (double)0);
		  EasyScene.setRightAnchor(info2, (double)700);
		  EasyScene.setTopAnchor(info2, (double) 0);
		  EasyScene.setBottomAnchor(info2, (double) 975);
		  Label wall1 = new Label();
		  wall1.setText("Health: 10000");
		  wall1.setFont(new Font(16));
		  wall1.setTextFill(Color.WHITE);
		  Label wall2 = new Label();
		  wall2.setText("Health: 10000");
		  wall2.setFont(new Font(16));
		  wall2.setTextFill(Color.WHITE);
		  Label wall3 = new Label();
		  wall3.setText("Health: 10000");
		  wall3.setFont(new Font(16));
		  wall3.setTextFill(Color.WHITE);
		  Label wall4 = new Label();
		  wall4.setText("Health: 10000");
		  wall4.setFont(new Font(16));
		  wall4.setTextFill(Color.WHITE);
		  Label wall5 = new Label();
		  wall5.setText("Health: 10000");
		  wall5.setFont(new Font(16));
		  wall5.setTextFill(Color.WHITE);
		  HardScene.getChildren().addAll(wall1,wall2,wall3,wall4,wall5);
		  HardScene.setLeftAnchor(wall1, (double)100);
		  HardScene.setTopAnchor(wall1, (double)50);
		  HardScene.setLeftAnchor(wall2, (double)100);
		  HardScene.setTopAnchor(wall2, (double)160);
		  HardScene.setLeftAnchor(wall3, (double)100);
		  HardScene.setTopAnchor(wall3, (double)290);
		  HardScene.setLeftAnchor(wall4, (double)100);
		  HardScene.setTopAnchor(wall4, (double)400);
		  HardScene.setLeftAnchor(wall5, (double)100);
		  HardScene.setTopAnchor(wall5, (double)510);
		  Label Wall1 = new Label();
		  Wall1.setText("Health: 10000");
		  Wall1.setFont(new Font(20));
		  Wall1.setTextFill(Color.WHITE);
		  Label Wall2 = new Label();
		  Wall2.setText("Health: 10000");
		  Wall2.setFont(new Font(20));
		  Wall2.setTextFill(Color.WHITE);
		  Label Wall3 = new Label();
		  Wall3.setText("Health: 10000");
		  Wall3.setFont(new Font(20));
		  Wall3.setTextFill(Color.WHITE);
		  EasyScene.getChildren().addAll(Wall1,Wall2,Wall3);
		  EasyScene.setLeftAnchor(Wall1, (double) 100);
		  EasyScene.setTopAnchor(Wall1, (double) 100);
		  EasyScene.setLeftAnchor(Wall2, (double) 100);
		  EasyScene.setTopAnchor(Wall2, (double) 300);
		  EasyScene.setLeftAnchor(Wall3, (double)100);
		  EasyScene.setTopAnchor(Wall3, (double) 500);
		  Label Lane1DL = new Label();
		  Lane1DL.setText("Danger level:");
		  Lane1DL.setFont(new Font(16));
		  Lane1DL.setTextFill(Color.WHITE);
		  Label Lane2DL = new Label();
		  Lane2DL.setText("Danger level: ");
		  Lane2DL.setFont(new Font(16));
		  Lane2DL.setTextFill(Color.WHITE);
		  Label Lane3DL = new Label();
		  Lane3DL.setText("Danger level: ");
		  Lane3DL.setFont(new Font(16));
		  Lane3DL.setTextFill(Color.WHITE);
		  Label Lane4DL = new Label();
		  Lane4DL.setText("Danger level: ");
		  Lane4DL.setFont(new Font(16));
		  Lane4DL.setTextFill(Color.WHITE);
		  Label Lane5DL = new Label();
		  Lane5DL.setText("Danger level: ");
		  Lane5DL.setFont(new Font(16));
		  Lane5DL.setTextFill(Color.WHITE);
		  HardScene.getChildren().addAll(Lane1DL,Lane2DL,Lane3DL,Lane4DL,Lane5DL);
		  HardScene.setLeftAnchor(Lane1DL, (double)100);
		  HardScene.setTopAnchor(Lane1DL,(double)110);
		  HardScene.setLeftAnchor(Lane2DL, (double)100);
		  HardScene.setTopAnchor(Lane2DL,(double)210);
		  HardScene.setLeftAnchor(Lane3DL, (double)100);
		  HardScene.setTopAnchor(Lane3DL,(double)340);
		  HardScene.setLeftAnchor(Lane4DL, (double)100);
		  HardScene.setTopAnchor(Lane4DL,(double)450);
		  HardScene.setLeftAnchor(Lane5DL, (double)100);
		  HardScene.setTopAnchor(Lane5DL,(double)565);
		  Label lane1DL = new Label();
		  lane1DL.setText("Danger level: ");
		  lane1DL.setFont(new Font(20));
		  lane1DL.setTextFill(Color.WHITE);
		  Label lane2DL = new Label();
		  lane2DL.setText("Danger level: ");
		  lane2DL.setFont(new Font(20));
		  lane2DL.setTextFill(Color.WHITE);
		  Label lane3DL = new Label();
		  lane3DL.setText("Danger level: ");
		  lane3DL.setFont(new Font(20));
		  lane3DL.setTextFill(Color.WHITE);
		  EasyScene.getChildren().addAll(lane1DL,lane2DL,lane3DL);
		  EasyScene.setLeftAnchor(lane1DL,(double) 100);
		  EasyScene.setTopAnchor(lane1DL,(double) 120);
		  EasyScene.setLeftAnchor(lane2DL,(double) 100);
		  EasyScene.setTopAnchor(lane2DL,(double) 320);
		  EasyScene.setLeftAnchor(lane3DL,(double) 100);
		  EasyScene.setTopAnchor(lane3DL,(double) 520);
		  // characters
		  
		  Image Abnormal = new Image("abnormal.png");
		  Image Armored = new Image("armored.png");
		  Image Colossal = new Image("colossal.png");
		  Image Pure = new Image("pure.png");
		  abnormal = new ImageView(Abnormal);
		  armored = new ImageView(Armored);
		  colossal = new ImageView(Colossal);
		  pure = new ImageView(Pure);
		  pure.setPreserveRatio(true);
		  abnormal.setPreserveRatio(true);
		  armored.setPreserveRatio(true);
		  colossal.setPreserveRatio(true);
		  pure.setFitHeight(52.5);//15
		  abnormal.setFitHeight(35);//10
		  armored.setFitHeight(52.5);//15
		  colossal.setFitHeight(210);//60
		  
		  // Game Over Scene
		  returntoStart=new Button();
		  returntoStart.setText("Main menu");
		  returntoStart.setPrefSize(200,40);
		  returntoStart.setFont(new Font(22));
		  returntoStart.setAlignment(Pos.CENTER);
		  
		  Label label10=new Label();
		  label10.setText("Game Over");
		  label10.setFont(new Font(30));
		  label10.setVisible(true);
		  label10.setAlignment(Pos.CENTER);
		  
		  scorefinal=new Label();
		  scorefinal.setText("your Score is "+Score);
		  scorefinal.setFont(new Font(30));
		  scorefinal.setAlignment(Pos.CENTER);
		  
		  GameOverScene.getChildren().addAll(returntoStart,label10,scorefinal);
		  
		  GameOverScene.setLeftAnchor(label10, (double)400);
		  GameOverScene.setRightAnchor(label10, (double)400);
		  GameOverScene.setTopAnchor(label10, (double) 100);
		
		  GameOverScene.setLeftAnchor(scorefinal, (double) 400);
		  GameOverScene.setRightAnchor(scorefinal, (double) 400);
		  GameOverScene.setTopAnchor(scorefinal, (double) 300);
		
		  GameOverScene.setLeftAnchor(returntoStart, (double) 400);
		  GameOverScene.setRightAnchor(returntoStart, (double) 400);
		  GameOverScene.setTopAnchor(returntoStart, (double) 500);
		  
	}
	public Button getWeaponShopButtonEasy() {
		return weaponShopButtonEasy;
	}
	public Button getWeaponShopButtonHard() {
		return weaponShopButtonHard;
	}
	public Group getInfo() {
		Group root = new Group();
		root.getChildren().add(info);
		return root;
	}
	public Group loadScene1(){
		Group root = new Group();
		root.getChildren().add(Scene1);
		return root;
	}
	public Group loadGameOverScene(){
		Group root = new Group();
		root.getChildren().add(this.GameOverScene);
		return root;
	}
	public Group loadGameInstructionScene(){
		Group root = new Group();
		root.getChildren().add(GameInstructionScene);
		return root;
	}
	public Group loadHardScene(){
		Group root = new Group();
		root.getChildren().add(HardScene);
		return root;
	}
	public Group loadEasyScene(){
		Group root = new Group();
		root.getChildren().add(EasyScene);
		return root;
	}
	public Group loadInsufficientResourcesPopUp(){
		Group root = new Group();
		root.getChildren().add(InsufficientResourcesPopUp);
		return root;
	}
	public Group loadInvalidLanePopUp(){
		Group root = new Group();
		root.getChildren().add(InvalidLanePopUp);
		return root;
	}
	public Group loadPassOrBuyPopUp(){
		Group root = new Group();
		root.getChildren().add(PassOrBuyPopUp);
		return root;
	}
	
	public Group loadSelectLanePopUpHard(){
		Group root = new Group();
		root.getChildren().add(SelectLanePopUpHard);
		return root;
	}
	public Group loadSelectLanePopUpEasy(){
		Group root = new Group();
		root.getChildren().add(SelectLanePopUpEasy);
		return root;
	}
	public Group loadSelectModePopUp(){
		Group root = new Group();
		root.getChildren().add(SelectModePopUp);
		return root;
	}
	public Group loadSelectWeaponPopUp(){
		Group root = new Group();
		root.getChildren().add(SelectWeaponPopUp);
		return root;
	}
	public Group loadWeaponShop(){
		Group root = new Group();
		root.getChildren().add(WeaponShop);
		return root;
	}
	public Group GameOverScene(){
		Group root = new Group();
		root.getChildren().add(GameOverScene);
		return root;
	}
	public void updateInfo(String phase,String resources,String lanes,String turn,String score,String ChoosenMode){
		this.Score=score;
		this.Phase=phase;
		this.Resources=resources;
		this.Lanes=lanes;
		this.Turn=turn;
		if(ChoosenMode=="Hard"){
		this.score.setText("Score: "+Score);
	    this.turn.setText("Turn: "+Turn);
		this.phase.setText("Phase: "+Phase);
		this.resources.setText("Resources: "+Resources);
		this.lanes.setText("Lanes: "+Lanes);
		}
		else{
			this.score2.setText("Score: "+Score);
		    this.turn2.setText("Turn: "+Turn);
			this.phase2.setText("Phase: "+Phase);
			this.resources2.setText("Resources: "+Resources);
			this.lanes2.setText("Lanes: "+Lanes);

		}
	}
	public void move(){//a method to move all titans in all lanes;
		for(int lane :this.laneCode){
			ArrayList<ImageView> TitansLane=new ArrayList<ImageView>();
			ArrayList<Label> TitansLaneHealth=new ArrayList<Label>();
			switch (lane){
			case 1: TitansLane=this.TitansLane1Original;TitansLaneHealth=this.TitansHealthLane1;break;
			case 2: TitansLane=this.TitansLane2Original;TitansLaneHealth=this.TitansHealthLane2;break;
			case 3: TitansLane=this.TitansLane3Original;TitansLaneHealth=this.TitansHealthLane3;break;
			case 4: TitansLane=this.TitansLane4Original;TitansLaneHealth=this.TitansHealthLane4;break;
			case 5: TitansLane=this.TitansLane5Original;TitansLaneHealth=this.TitansHealthLane5;break;
			}
			int i=0;
			for(ImageView t:TitansLane){
				Label h=TitansLaneHealth.get(i);
				int d=100;
				if(sameImg(t,pure)||sameImg(t,armored)) {
					d=30;
					translate(t,d);
					translate(h,d);
				}
				else if(sameImg(t,abnormal)) {
					d=45;
					translate(t,d);
					translate(h,d);
				}
				else {//problem here
					translate(t,this.speedColossal);
					translate(h,this.speedColossal);
					//speedColossal+=3;
				}
				i++;
			}
		}
	}
	
	public boolean sameImg(ImageView imageView1, ImageView imageView2) {
		if (imageView1 == null || imageView2 == null) {
            return false;
        }
        String imagePath1 = getImagePath(imageView1);
        String imagePath2 = getImagePath(imageView2);

        return imagePath1 != null && imagePath1.equals(imagePath2);
    }
    private static String getImagePath(ImageView imageView) {
        if (imageView == null || imageView.getImage() == null) {
            return null;
        }
        String imagePath = imageView.getImage().getUrl();
        return imagePath;
    }
	
	public void performWeaponsAttacks(){//a method to perform all attacks of all weapons 
		                                //it should remove defeated titans as well 
		                                //and update resources if a titan is defeated 
		                                //and update score as well
	}
	
	//a method to perform all attacks of all titans 
    //it should check if the wall is destroyed and declare it as so
	public void performTitansAttacks(){
		
	}

	//this method should take inputs the number of titans
    //to be added as well as a list of which titans specifically 
    // and display them at the titan spawn distance.
	//according to the background
	public void addTurnTitansToLane(){//y values of titans
		if(this.ApproachingTitans.isEmpty())
			this.ApproachingTitans=Controller.Refill();
		if(this.ApproachingTitansHealth.isEmpty())
			this.ApproachingTitansHealth=Controller.RefillHealth();
		int lane = this.laneCode.get(0);
		if(Controller.getChoosenMode().equals("Easy"))lane+=1;
        Random random = new Random();  
        int y = 0;
		for(int i = 0;i<this.numberOfTitansPerTurn;i++){
			switch(lane){
			case 1:y=random.nextInt(81)+10;break;
			case 2:y=random.nextInt(71)+180;break;
			case 3:y=random.nextInt(81)+320;break;
			case 4:y=random.nextInt(81)+470;break;
			case 5:y= random.nextInt(71)+650;break;
			}
			if(this.ApproachingTitans.isEmpty())
				this.ApproachingTitans=Controller.Refill();
			if(this.ApproachingTitansHealth.isEmpty())
				this.ApproachingTitansHealth=Controller.RefillHealth();
			ImageView t = this.ApproachingTitans.remove(0);
			Label h = this.ApproachingTitansHealth.remove(0);
			switch (lane){
			case 1: this.TitansLane1Original.add(t);this.TitansHealthLane1.add(h);break;
			case 2: this.TitansLane2Original.add(t);this.TitansHealthLane2.add(h);break;
			case 3: this.TitansLane3Original.add(t);this.TitansHealthLane3.add(h);break;
			case 4: this.TitansLane4Original.add(t);this.TitansHealthLane4.add(h);break;
			case 5: this.TitansLane5Original.add(t);this.TitansHealthLane5.add(h);break;
			}
			if(Controller.getChoosenMode()=="Easy"){
				this.EasyScene.getChildren().addAll(t,h);
       			EasyScene.setLeftAnchor(t, (double)1200);
				EasyScene.setTopAnchor(t, (double)y);
				EasyScene.setLeftAnchor(h, (double)1190);
				EasyScene.setTopAnchor(h, (double)y-10);
				translate(h,60);
				translate(t,60);
			}
			else{
				this.HardScene.getChildren().addAll(t,h);
        		HardScene.setLeftAnchor(t, (double)1200);
     			HardScene.setTopAnchor(t, (double) y);
    			HardScene.setLeftAnchor(h, (double)1190);
     			HardScene.setTopAnchor(h, (double) y-10);
    			translate(h,60);
    			translate(t,60);
			}
		}
		
	}
	public void titanAttack(Node Titan){
		TranslateTransition translate = new TranslateTransition();//backward
		TranslateTransition translate2 = new TranslateTransition();//forward
		translate.setNode(Titan);
		translate.setDuration(Duration.millis(300));
		translate.setByX(-50);
		translate2.setNode(Titan);
		translate2.setDuration(Duration.millis(300));
		translate2.setByX(50);
        SequentialTransition sequentialTransition = new SequentialTransition(translate2, translate);
        sequentialTransition.play();
		
	}
	public void performTurnTitans() {
		int count=1;
		if(Controller.getChoosenMode().equals("Easy"))count++;
		for(ArrayList<TitanImageView> lane:this.allLanes) {
			for(TitanImageView t:lane) {
				t.l.setText("Health: "+t.t.getCurrentHealth());
				if(t.t.isDefeated()) {
					fade(t.v);
					fade(t.l);
				}
				else {
				if(!t.t.hasReachedTarget()) {
				translate(t.v,t.t.getSpeed()*3);
    			translate(t.l,t.t.getSpeed()*3);
				}
				else {
					titanAttack(t.v);
					titanAttack(t.l);
				}
				}
			}
			count++;
		}
	}
	public void AddTurnTitans() {
		int count=1;
		AnchorPane s=Controller.getChoosenMode().equals("Easy")?EasyScene:HardScene;
		if(Controller.getChoosenMode().equals("Easy"))count++;
		for(ArrayList<TitanImageView> lane:this.allLanes) {
			int y=0;
			switch(count) {
			case 1:y=60;break;
			case 2:y=170;break;
			case 3:y=310;break;
			case 4:y=410;break;
			case 5:y=510;break;
			}
			for(TitanImageView t:lane) {
				if(!s.getChildren().contains(t.v)) {
				s.getChildren().addAll(t.v,t.l);
				s.setLeftAnchor(t.v, (double)1200);
				s.setTopAnchor(t.v, (double)y);
				s.setLeftAnchor(t.l, (double)1190);
				s.setTopAnchor(t.l, (double)y-10);
				if(t.t instanceof ColossalTitan) {
					translate(t.v,10);
					translate(t.l,10);
				}
				else {
				translate(t.v,55);
				translate(t.l,55);
				}
				}
			}
			count++;
		}
	}
	public void deployWeapon(int weaponCode, int lane) {
	    Image i = null;
	    boolean t = false;
	    switch (weaponCode) {
	        case 1:
	            i = new Image("canon.png");
	            break;
	        case 2:
	            i = new Image("sniper.png");
	            break;
	        case 3:
	            i = new Image("volly.png");
	            break;
	        case 4:
	            i = new Image("wallTrap.png");
	            t = true;
	            break;
	    }

	    if (i != null) {
	        ImageView v = new ImageView(i);
	        v.setPreserveRatio(true);
   			v.setFitHeight(45);
	        GridPane p = Controller.getChoosenMode().equals("Hard") ? hard : easy;
	        boolean cellAdded = false;
	        for (int col = 0; col < 10; col++) {
	            boolean cellFree = true;

	            for (Node node : p.getChildren()) {
	                Integer nodeRow = GridPane.getRowIndex(node);
	                Integer nodeCol = GridPane.getColumnIndex(node);
	                if (nodeRow == null) nodeRow = 0;
	                if (nodeCol == null) nodeCol = 0;

	                if (nodeRow == lane && nodeCol == col) {
	                    cellFree = false;
	                    break;
	                }
	            }
	            if (cellFree) {
	                p.add(v, col, lane);
	                
	                cellAdded = true;
	                break;
	            }
	        }
	        if (!cellAdded) {
	            System.out.println("No free cell found in row " + lane);
	        }
	    }
	}


	private void translate(Node Titan,int distance){	//used to move the titan
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(Titan);
		translate.setDuration(Duration.millis(1200));
		distance*=-1;
		translate.setByX(distance);
		translate.play();
	}
	private void fade(Node Titan){		//used when titan dies
			FadeTransition fadeTitan = new FadeTransition();
			fadeTitan.setDuration(Duration.millis(1000));
			fadeTitan.setNode(Titan);
			fadeTitan.setToValue(0);
			fadeTitan.play();
	}
}
