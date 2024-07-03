package game.gui;

import game.engine.Battle;
import game.engine.BattlePhase;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Node;

public class Controller extends Application{
	private static Battle battle;
	private static View view;
	private static Group root;
	private static Scene scene;
	private Stage PopUpStage;
	private Scene PopUpScene;
	private static Stage MainStage;
	private static String ChoosenMode;
	private Group PopUpRoot;
	private static String passOrBuy="pass";
	private Image icon;
	private int weaponCode;
	private static int LaneChoosen;
	boolean close=false;
	private ChoiceDialog d1;
	private ChoiceDialog d2;
	private ChoiceDialog d3;
	private Alert alert1;
	private Alert alert2;
	
	public static String getChoosenMode() {
		return ChoosenMode;
	}
	public static View getView() {
		return view;
	}
	public void start(Stage stage) throws IOException {//how to handle IO exceptions???
		MainStage=stage;
	    view = new View();
	    view.addAllComponents();
	    root=view.loadScene1();
	    scene=new Scene(root,1200,700);
	    battle = null;//initialize only ,it will for sure change whenever the player presses the next button
	    view.setScore("0");
		view.setResources("0");
		view.setTurn("0");
		view.setPhase("EARLY");
		view.setLanes("1,2,3,4,5");//for now only
	    Button Next=view.getNext();
	    Button Start=view.getStart();
	    Button returntoStart=view.getReturntoStart();
	    Button WeaponShop1=view.getWeaponShopButtonEasy();
	    Button WeaponShop2=view.getWeaponShopButtonHard();
	    Button PiercingCannon = view.getPiercingCannon();
	    Button SniperCannon = view.getSniperCannon();
	    Button VolleySpread = view.getVolleySpread();
	    Button WallTrap = view.getWallTrap();
	    Button deployWeaponEasy=view.getDeployWeapon1();
	    Button deployWeaponHard=view.getDeployWeapon2();
	    Next.setOnMouseClicked(event->handleButtonNext(event));
	    Start.setOnMouseClicked(event->handleButtonStart(event));
	    returntoStart.setOnMouseClicked(event->handleReturnButton(event));
	    WeaponShop1.setOnMouseClicked(event->this.OpenWeaponShop());
	    WeaponShop2.setOnMouseClicked(event->this.OpenWeaponShop());
		icon = new Image("AOT.jpg");
		stage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to exit the game?");
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.initOwner(stage); 
            alert.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    close = true;
                    if(d1!=null)
                        d1.close();
                    if(d2!=null)
                        d2.close();
                    if(d3!=null)
                        d3.close();
                    if(alert1!=null)
                    	alert1.close();
                    if(alert2!=null)
                	    alert2.close();
                    Platform.exit();
                }
            });
        });

		Media media = new Media(getClass().getResource("/backSound.mp3").toExternalForm());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	    stage.setScene(scene);
		stage.setResizable(true);
		stage.setTitle("Attack on Titan: Utopia");
		stage.getIcons().add(icon);
		stage.show();
	}
	
	public void handleButtonNext(Event event){//handles which mode the player chooses and if he did not a pop up will appear
    	 if(view.getMode()==null) {
    		 PopUpRoot=view.loadSelectModePopUp();
    		 Scene scene=new Scene(PopUpRoot);
    		 PopUpStage=new Stage();
    		 PopUpStage.setScene(scene);
    		 PopUpStage.show();
    	    }
    	 else
    	    {
    		 root=view.loadGameInstructionScene();
    		 Stage stage=MainStage;
    		 scene=new Scene(root,1200,700);
    		 stage.setScene(scene);
    		 stage.setResizable(false);
    		 stage.show();

    	    }
    	 ChoosenMode = view.getMode();
	}
    public void handleButtonStart(Event event){
    	if(ChoosenMode.equals("Hard")){
			 root=view.loadHardScene();
			 Stage stage=MainStage;
		     scene=new Scene(root,1200,700);
		     stage.setScene(scene);
		     stage.show();
		     }
		 else{
			 root=view.loadEasyScene();
			 Stage stage=MainStage;
		     scene=new Scene(root,1200,700);
		     stage.setScene(scene);
		     stage.show();
		     }
    	 int initialNumOfLanes = ChoosenMode=="Easy"?3:5;
		 int initialResourcesPerLane = ChoosenMode=="Easy"?250:125;
		 try {
			battle = new Battle( 1, 0, 330, initialNumOfLanes ,initialResourcesPerLane);
		}
		 catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		 battle.refillApproachingTitans();
		 String Lane=ChoosenMode=="Hard"?"1,2,3,4,5":"1,2,3";//we should update all elements in view after performing battle methods
		 view.updateInfo(""+battle.getBattlePhase(), ""+battle.getResourcesGathered()
		 ,Lane, ""+battle.getNumberOfTurns(), ""+battle.getScore(),ChoosenMode);
		 view.setApproachingTitans(approachingTitansImages(battle.getApproachingTitans()));
		 view.setApproachingTitansHealth(approachingTitansHealth(battle.getApproachingTitans()));
		 view.setNumberOfTitansPerTurn(battle.getNumberOfTitansPerTurn());
		 view.setLaneCode(lanes(battle.getLanes(),battle.getOriginalLanes()));
		 view.setAllLanes(imgInit(battle.getLanes()));
		 this.passOrBuy="Pass";
			 while(!battle.isGameOver()){
				 if(this.close==true)
					 break;
				 PassOrBuy();
				 if(this.passOrBuy.equals("Pass")){
					 battle.passTurn();
					 view.AddTurnTitans();
					 view.performTurnTitans();
					 this.updateViewInfo();
				 }
				 else{
					 try {
						this.OpenWeaponShop();
						this.handleSelectedLane();
						battle.purchaseWeapon(weaponCode,battle.getOriginalLanes().get(this.LaneChoosen-1));
						deployWeapon();
						view.AddTurnTitans();
						view.performTurnTitans();
					    this.updateViewInfo();
					  } 
					 catch (InsufficientResourcesException e) {
						    alert2 = new Alert(Alert.AlertType.INFORMATION);
						    alert2.setContentText("You do not have enough resources to buy this weapon");
				            alert2.setTitle("NO RESOURCES");
				            alert2.setHeaderText(null);
				            alert2.initOwner(MainStage); 
				            alert2.showAndWait();
					 }
					 catch( InvalidLaneException e){
						    alert1 = new Alert(Alert.AlertType.INFORMATION);
				            alert1.setContentText("You cannot by a Weapon in this Lane");
				            alert1.setTitle("INVALID LANE");
				            alert1.setHeaderText(null);
				            alert1.initOwner(MainStage); 
				            alert1.showAndWait();
					 }
				 }
				 this.passOrBuy="Pass";
			 }
			 view.getScorefinal().setText("Your Score is: "+battle.getScore());
			 root=view.loadGameOverScene();
			 Stage stage=MainStage;
		     scene=new Scene(root,1200,700);
		     stage.setScene(scene);
		     stage.show();
		 }
    public void PassOrBuy(){
    	if(this.close==true)return;
             d1 = new ChoiceDialog<>("Pass", "Pass", "Buy");
             d1.setTitle("Decision");
             d1.setHeaderText("Please select an option:");
             d1.setContentText("Pass or Buy?");
             d1.setX(100);
             d1.setY(550);
             d1.initOwner(MainStage);
             d1.initModality(Modality.NONE);
             Optional<String> result = d1.showAndWait();
             result.ifPresent(choice -> {
                 if (choice.equals("Pass")) {
                     this.passOrBuy="Pass";
                 } else if (choice.equals("Buy")) {
                    this.passOrBuy="Buy";
                 }
             });
    }
    public void OpenWeaponShop() {
    	if(this.close==true)return;
    	 d2 = new ChoiceDialog<>("Weapon", "Anti-Titan Shell"+"\n"+"Price: 25"+"\n"+"Damage: 10",
    			"Long Range Spear"+"\n"+"Price: 25"+"\n"+"Damage: 35","Wall Spread Cannon"+"\n"+"Price: 100"+"\n"+"Damage: 5"+"\n"+"Range: 20-25"
    	 ,"Proximity Trap"+"\n"+"Price: 75"+"\n"+"Damage: 100");
         d2.setTitle("WeaponShop");
         d2.setHeaderText("Please select a Weapon:");
         d2.setContentText("Choose A Weapon");
         d2.getDialogPane().setPrefWidth(600);
         d2.getDialogPane().setPrefHeight(200);
         d2.initOwner(MainStage);
         d2.initModality(Modality.NONE);
         Optional<String> result = d2.showAndWait();
         result.ifPresent(choice -> {
             if (choice.equals("Anti-Titan Shell"+"\n"+"Price: 25"+"\n"+"Damage: 10")) {
                 this.weaponCode=1;
             } else if (choice.equals("Long Range Spear"+"\n"+"Price: 25"+"\n"+"Damage: 35")) {
            	 this.weaponCode=2;
             }
               else if(choice.equals("Wall Spread Cannon"+"\n"+"Price: 100"+"\n"+"Damage: 5"+"\n"+"Range: 20-25")) {
            	   this.weaponCode=3;
               }
               else if(choice.equals("Proximity Trap"+"\n"+"Price: 75"+"\n"+"Damage: 100")){
            	   this.weaponCode=4;
               }
         });
    }
    public void deployWeapon() {
    	view.deployWeapon(weaponCode, LaneChoosen);
    }
    public static void handleButtonPass(){
    	passOrBuy="Pass";
    }
    public void handleSelectedLane(){
    	if(this.close==true)return;
    	 if(this.ChoosenMode=="Hard") {
    		 d3=new ChoiceDialog<>("1","1","2","3","4","5");
         d3.setTitle("Lanes");
         d3.setHeaderText("Please select a Lane:");
         d3.setContentText("Choose a lane");
         d3.initOwner(MainStage);
         d3.initModality(Modality.NONE);
         Optional<String> result = d3.showAndWait();
         result.ifPresent(choice -> {
             if (choice.equals("1")) {
                 this.LaneChoosen=1;
             } else if (choice.equals("2")) {
            	 this.LaneChoosen=2;
             }
             else if (choice.equals("3")) {
            	 this.LaneChoosen=3;
             }
             else if (choice.equals("4")) {
            	 this.LaneChoosen=4;
             }
             else if (choice.equals("5")) {
            	 this.LaneChoosen=5;
             }
         });
    	 }
    	 else {
    		 d3=new ChoiceDialog<>("1","2","3");
             d3.setTitle("Lanes");
             d3.setHeaderText("Please select a Lane:");
             d3.setContentText("Choose a lane");
             Optional<String> result = d3.showAndWait();
             result.ifPresent(choice -> {
                 if (choice.equals("1")) {
                     this.LaneChoosen=1;
                 } else if (choice.equals("2")) {
                	 this.LaneChoosen=2;
                 }
                 else if (choice.equals("3")) {
                	 this.LaneChoosen=3;
                 }
             });
    	 }
    }
    
    public void handleButtonBuy(){//we should handle if player closes the pop up without choosing
    	OpenWeaponShop();
    }
    public void handleWeaponShop(Event event){
    	root = view.loadWeaponShop();
    	scene = new Scene(root);
       	Stage stage = MainStage;
    	stage.setScene(scene);
    	stage.show();
    }
  
    public ArrayList<TitanImageView> getImg(PriorityQueue<Titan> titans,ArrayList<TitanImageView> titanImages){
    	ArrayList<TitanImageView> res=new ArrayList<TitanImageView> ();
    	PriorityQueue<Titan> tmp=new PriorityQueue<Titan>();
    	while(!titans.isEmpty()) {
    		Titan t=titans.remove();
    		boolean flag=false;
    		for(TitanImageView v:titanImages) {
    			if(v.t==t) {
    				flag= true;
    				res.add(v);
    				tmp.add(t);
    				break;
    			}
    		}
    		if(flag)
    			continue;
    		ImageView v=null;
    		Label health=new Label();
    		if(t instanceof PureTitan) {
   			 Image Pure = new Image("pure.png");
   			 v= new ImageView(Pure);
   			 v.setPreserveRatio(true);
   			 v.setFitHeight(100);
   			health.setText("Health: "+100);
   			}
   		else if(t instanceof AbnormalTitan) {
   			Image Abnormal = new Image("abnormal.png");
   			v = new ImageView(Abnormal);
            v.setPreserveRatio(true);
   			v.setFitHeight(80);//10
   			health.setText("Health: "+100);
   			}
   		else if(t instanceof ArmoredTitan) {
   			Image Armored = new Image("armored.png");
   			v= new ImageView(Armored);
   			v.setPreserveRatio(true);
   			v.setFitHeight(100);//15
   			health.setText("Health: "+200);
   			}
   		else {
   			Image Colossal = new Image("colossal.png");
   			v = new ImageView(Colossal);
   			v.setPreserveRatio(true);
   			v.setFitHeight(150);//60
   			health.setText("Health: "+1000);
   			}
    		TitanImageView x=new TitanImageView(t,v,health);
    		health.setTextFill(Color.WHITE);
    		res.add(x);
    		tmp.add(t);
    	}
    	titans.addAll(tmp);
    	return res;
    }
    public ArrayList<TitanImageView> getImgInit(PriorityQueue<Titan> titans){
    	ArrayList<TitanImageView> res=new ArrayList<TitanImageView> ();
    	PriorityQueue<Titan> tmp=new PriorityQueue<Titan>();
    	while(!titans.isEmpty()) {
    		Titan t=titans.remove();
    		ImageView v=null;
    		Label health=new Label();
    		if(t instanceof PureTitan) {
   			 Image Pure = new Image("pure.png");
   			 v= new ImageView(Pure);
   			 v.setPreserveRatio(true);
   			 v.setFitHeight(100);
   			health.setText("Health: "+100);
   			}
   		else if(t instanceof AbnormalTitan) {
   			Image Abnormal = new Image("abnormal.png");
   			v = new ImageView(Abnormal);
            v.setPreserveRatio(true);
   			v.setFitHeight(80);//10
   			health.setText("Health: "+100);
   			}
   		else if(t instanceof ArmoredTitan) {
   			Image Armored = new Image("armored.png");
   			v= new ImageView(Armored);
   			v.setPreserveRatio(true);
   			v.setFitHeight(100);//15
   			health.setText("Health: "+200);
   			}
   		else {
   			Image Colossal = new Image("colossal.png");
   			v = new ImageView(Colossal);
   			v.setPreserveRatio(true);
   			v.setFitHeight(180);//60
   			health.setText("Health: "+1000);
   			}
    		TitanImageView x=new TitanImageView(t,v,health);
    		res.add(x);
    		tmp.add(t);
    	}
    	titans.addAll(tmp);
    	return res;
    }
    public ArrayList<ArrayList<TitanImageView>> img(ArrayList<Lane> lanes){
    	ArrayList<ArrayList<TitanImageView>> res=new ArrayList<ArrayList<TitanImageView>> ();
    	ArrayList<Lane> tmp=new ArrayList<Lane>();
    	int i=0;
    	while(i<view.getAllLanes().size()) {
    		Lane l=battle.getOriginalLanes().get(i);
    		if(!battle.getOriginalLanes().get(i).isLaneLost())
    		res.add(getImg(l.getTitans(),view.getAllLanes().get(i)));
    		i++;
    	}
    	return res;
    }
    public ArrayList<ArrayList<TitanImageView>> imgInit(PriorityQueue<Lane> lanes){
    	ArrayList<ArrayList<TitanImageView>> res=new ArrayList<ArrayList<TitanImageView>> ();
    	PriorityQueue<Lane> tmp=new PriorityQueue<Lane>();
    	int i=0;
    	while(!lanes.isEmpty()) {
    		Lane l=lanes.remove();
    		tmp.add(l);
    		res.add(getImgInit(l.getTitans()));
    		}
    		i++;
    	lanes.addAll(tmp);
    	return res;
    }
    //GameOver Scene
    public void handleReturnButton(Event event){
    	root=view.loadScene1();
    	scene=new Scene(root);
    	Stage stage = MainStage;
    	stage.setScene(scene);
    	stage.show();
    }
    public static ArrayList<ImageView> Refill(){//change the approachingTitans list to ImageView
    	int[] phaseApproachingTitans;
    	int[][] PHASES_APPROACHING_TITANS =
    		{
    			{ 1, 1, 1, 2, 1, 3, 4 },
    			{ 2, 2, 2, 1, 3, 3, 4 },
    			{ 4, 4, 4, 4, 4, 4, 4 } };
		switch (battle.getBattlePhase())
		{
		case EARLY:
			phaseApproachingTitans =PHASES_APPROACHING_TITANS[0] ;
			break;
		case INTENSE:
			phaseApproachingTitans = PHASES_APPROACHING_TITANS[1];
			break;
		case GRUMBLING:
			phaseApproachingTitans = PHASES_APPROACHING_TITANS[2];
			break;
		default:
			phaseApproachingTitans = new int[0];
		}
		ArrayList<ImageView> Images=new ArrayList<ImageView>();
		for (int code : phaseApproachingTitans)
		{
			if(code==1) {
   			 Image Pure = new Image("pure.png");
   			ImageView P = new ImageView(Pure);
   			P.setPreserveRatio(true);
   			P.setFitHeight(100);//15
   			Images.add(P);
   			}
   		else if(code==2) {
   			Image Abnormal = new Image("abnormal.png");
   			ImageView abnormal = new ImageView(Abnormal);
   			abnormal.setPreserveRatio(true);
   			abnormal.setFitHeight(80);//10
   			Images.add(abnormal);
   			}
   		else if(code==3) {
   			Image Armored = new Image("armored.png");
   			ImageView armored = new ImageView(Armored);
   			armored.setPreserveRatio(true);
   			armored.setFitHeight(100);//15
   			Images.add(armored);
   			}
   		else {
   			Image Colossal = new Image("colossal.png");
   			ImageView colossal = new ImageView(Colossal);
   			colossal.setPreserveRatio(true);
   			colossal.setFitHeight(210);//60
   			Images.add(colossal);
   			}	
		}
		return Images;

    }
    public static ArrayList<Label> RefillHealth() {
    	int[] phaseApproachingTitans;
    	int[][] PHASES_APPROACHING_TITANS =
    		{
    			{ 1, 1, 1, 2, 1, 3, 4 },
    			{ 2, 2, 2, 1, 3, 3, 4 },
    			{ 4, 4, 4, 4, 4, 4, 4 } };
		switch (battle.getBattlePhase())
		{
		case EARLY:
			phaseApproachingTitans =PHASES_APPROACHING_TITANS[0] ;
			break;
		case INTENSE:
			phaseApproachingTitans = PHASES_APPROACHING_TITANS[1];
			break;
		case GRUMBLING:
			phaseApproachingTitans = PHASES_APPROACHING_TITANS[2];
			break;
		default:
			phaseApproachingTitans = new int[0];
		}
		ArrayList<Label> labels=new ArrayList<Label>();
		for (int code : phaseApproachingTitans)
		{
			if(code==1) {
				Label health=new Label();
	    		health.setText("Health: "+100);
	    		health.setFont(new Font(15));
	    		health.setTextFill(Color.WHITE);
	    		labels.add(health);
   			}
   		else if(code==2) {
   			Label health=new Label();
    		health.setText("Health: "+100);
    		health.setFont(new Font(15));
    		health.setTextFill(Color.WHITE);
    		labels.add(health);
   			}
   		else if(code==3) {
   			Label health=new Label();
    		health.setText("Health: "+200);
    		health.setFont(new Font(15));
    		health.setTextFill(Color.WHITE);
    		labels.add(health);
   			}
   		else {
   			Label health=new Label();
    		health.setText("Health: "+1000);
    		health.setFont(new Font(15));
    		health.setTextFill(Color.WHITE);
    		labels.add(health);
   			}	
		}
		return labels;

    }
    public ArrayList<ImageView> approachingTitansImages(ArrayList<Titan> approachingTitans){//change the approachingTitans list to ImageView
    	ArrayList<ImageView> Images=new ArrayList<ImageView>();
    	int i=0;
    	while(i<approachingTitans.size()){
    		Titan t=approachingTitans.get(i);
    		if(t instanceof PureTitan) {
    			 Image Pure = new Image("pure.png");
    			ImageView P = new ImageView(Pure);
    			P.setPreserveRatio(true);
    			P.setFitHeight(100);//15
    			Images.add(P);
    			}
    		else if(t instanceof AbnormalTitan) {
    			Image Abnormal = new Image("abnormal.png");
    			ImageView abnormal = new ImageView(Abnormal);
    			abnormal.setPreserveRatio(true);
    			abnormal.setFitHeight(80);//10
    			Images.add(abnormal);
    			}
    		else if(t instanceof ArmoredTitan) {
    			Image Armored = new Image("armored.png");
    			ImageView armored = new ImageView(Armored);
    			armored.setPreserveRatio(true);
    			armored.setFitHeight(100);//15
    			Images.add(armored);
    			}
    		else {
    			Image Colossal = new Image("colossal.png");
    			ImageView colossal = new ImageView(Colossal);
    			colossal.setPreserveRatio(true);
    			colossal.setFitHeight(210);//60
    			Images.add(colossal);
    			}
    		i++;
    	}
    	return Images;
    }
    public ArrayList<Label> approachingTitansHealth(ArrayList<Titan> approachingTitans){//change the approachingTitans list to ImageView
    	ArrayList<Label> labels=new ArrayList<Label>();
    	int i=0;
    	while(i<approachingTitans.size()){
    		Titan t=approachingTitans.get(i);
    		Label health=new Label();
    		health.setText("Health: "+t.getBaseHealth());
    		health.setFont(new Font(15));
    		health.setTextFill(Color.WHITE);
    		labels.add(health);
    		i++;
    	}
    	return labels;
    }
    public void updateViewInfo(){//we should update all elements in view after performing battle methods
    	 view.setApproachingTitans(approachingTitansImages(battle.getApproachingTitans()));
		 view.setApproachingTitansHealth(approachingTitansHealth(battle.getApproachingTitans()));
		 view.setNumberOfTitansPerTurn(battle.getNumberOfTitansPerTurn());
		 view.setLaneCode(lanes(battle.getLanes(),battle.getOriginalLanes()));
		 view.updateInfo(""+battle.getBattlePhase(), ""+battle.getResourcesGathered()
		 ,""+view.getLaneCode(), ""+battle.getNumberOfTurns(), ""+battle.getScore(),ChoosenMode);
		 view.setAllLanes(img(battle.getOriginalLanes()));
		 
    }
    public void updateLaneTitans(PriorityQueue<Lane> lanes){
    	PriorityQueue tmp= new PriorityQueue();
    	while(!lanes.isEmpty()){
    		
    	}
    }
    public ArrayList<Integer> lanes(PriorityQueue<Lane> lanes,ArrayList<Lane>originalLanes){//change the lanes list to int
    	ArrayList<Integer> codes=new ArrayList<Integer>();
    	PriorityQueue<Lane> tmp=new PriorityQueue<Lane>();
    	while(!lanes.isEmpty()){
    		tmp.offer(lanes.peek());
    		Lane l=lanes.remove();
    		codes.add(originalLanes.indexOf(l)+1);
    	}
    	while(!tmp.isEmpty())
    		lanes.offer(tmp.poll());
    	return codes;
    }
//    public ArrayList<Integer> distance(PriorityQueue<Lane> lanes,ArrayList<Lane>originalLanes){
//    	ArrayList<Integer> codes=new ArrayList<Integer>();
//    }
    
	public static void main(String[] args) {
		launch(args);
	}

}
