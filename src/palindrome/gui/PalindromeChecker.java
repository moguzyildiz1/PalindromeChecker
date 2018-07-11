package palindrome.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import java.text.AttributedString;
import java.util.HashMap;

/**
 * Palindrome Checker GUI
 * Input: String
 * Output: If String is palindrome returns: "This is a palindrome."
 * 		   Else returns Strings with Capital letters to unfit letters
 * Example: aaa aaa aaa => This is a palindrome.
 * 			aaa aaa abb => AAa aaa aBB (Capital letters distort palindrome)
 * @author t420
 *
 */
public class PalindromeChecker extends Application {

	private Label inputLabel = new Label("Enter A String: ");
	private Label resultLabel = new Label("Result : ");

	private TextField inputField = new TextField();
	private TextField resultField = new TextField();

	@Override
	public void start(Stage primaryStage) throws Exception{
		//Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

		resultField.setEditable(false);
		Button checkBtn = new Button();
		checkBtn.setText("Check");
		checkBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				HashMap<Integer,Integer> map = palindromeChecker(inputField.getText());
				if(map.size()==0){
					resultField.setText("This is Palindrome");
				}
				else{
					manipulateHelper(map,inputField.getText());
				}
			}
		});

		GridPane  gridPane = new GridPane();
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(3);
		gridPane.setVgap(2);
		gridPane.setAlignment(Pos.TOP_LEFT);

		//StackPane stackPane = new StackPane();
		gridPane.add(inputLabel,0,0);
		gridPane.add(inputField,1,0);
		gridPane.add(resultLabel,0,1);
		gridPane.add(resultField,1,1);
		gridPane.add(checkBtn,1,2);
		primaryStage.setTitle("Palindrome Checker");
		Scene scene = new Scene(gridPane,600,250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private HashMap<Integer,Integer> palindromeChecker(String text){
		HashMap<Integer,Integer> returnMap = new HashMap<Integer,Integer>();
		char[] charText = text.toCharArray();
		for(int i=0,j=text.length()-1;i<j;i++,j--){
			if(charText[i] != charText[j]){
				returnMap.put(i,j);
			}
		}
		//System.out.println(returnMap.toString());
		return returnMap;
	}
	
	private void manipulateHelper(HashMap<Integer,Integer> hashMap,String text){

		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		// AttributedString a = new AttributedString(text);
		// a.addAttribute(TextAttribute.FOREGROUND, Color.RED, 0, 2);

		int counter = 0;
		if(text.length()%2==0)
			counter=text.length()/2;
		if(text.length()%2==1)
			counter=text.length()/2+1;

		for(int i=0;i<counter;i++){
			if(hashMap.get(i)!=null) { //this is red block
				//charTextResult[i] = charText[i];
				//charTextResult[hashMap.get(i)] = charText[hashMap.get(i)];
				map.put(i,0);
				map.put(hashMap.get(i),0);
				/*
                a.addAttribute(TextAttribute.FOREGROUND, Color.RED, i, i+1);
                a.addAttribute(TextAttribute.FOREGROUND, Color.RED, hashMap.get(i), hashMap.get(i)+1);*/
			}
			else{  //this is green block
				map.put(i,1);
				map.put(text.length()-i-1,1);
				/*
                a.addAttribute(TextAttribute.FOREGROUND, Color.GREEN, i, i+1);
                a.addAttribute(TextAttribute.FOREGROUND, Color.GREEN, text.length()-1-i, text.length()-i);
				 */
			}
		}
		//System.out.println("map = "+map.toString());
		String sumStr = "";
		for(int i=0;i<text.length();i++){
			if(map.get(i)==0){
				sumStr += String.valueOf(text.charAt(i)).toUpperCase();
			}
			else
				sumStr += String.valueOf(text.charAt(i)).toLowerCase();
		}
		resultField.setText(sumStr);
	}
}
