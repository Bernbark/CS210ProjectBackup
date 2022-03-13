package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        int max = 10;
        int min = 1;

        final Card cards[] = new Card[8];
        Button reverseSignButtons[] = new Button[8];
        StackPane cardSlots[] = new StackPane[8];
        GridPane cardOrganizerPanes[] = new GridPane[8];

        for(int i = 0; i < cards.length; i++){
            cardSlots[i] = new StackPane();
            cardOrganizerPanes[i] = new GridPane();
            cardOrganizerPanes[i].setPadding(new Insets(10,10,10,10));
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            cards[i] = new Card(randomNum);
            cards[i].SetText(MakeText(cards[i]));
            cards[i].SetRectangle(MakeRectangle());
            final int index = i;
            reverseSignButtons[i] = new Button("Reverse Sign");
            reverseSignButtons[i].setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    ReverseSign(cards[index]);
                }
            });
            cardOrganizerPanes[i].add(cards[i].text, 0,0,1,1);
            cardOrganizerPanes[i].add(reverseSignButtons[i], 0,2,1,1);
            cardSlots[i].getChildren().addAll(cards[i].rectangle, cardOrganizerPanes[i]);
        }

        //StackPane cardSlotOne = new StackPane();
        //cardSlotOne.getChildren().addAll(card.rectangle,card.text, button);

        //StackPane cardSlotTwo = new StackPane();
        //Rectangle rectangle1 = MakeRectangle();

        //Card card1 = new Card(2);
        //Text text1 = MakeText(card1);
        //text1.setText(card1.GetValueAsString());
        //card1.SetText(text1);
        //card1.SetRectangle(rectangle1);
        //cardSlotTwo.getChildren().addAll(rectangle1,text1,button);

        /**
         * Construction of Player 1's Hand UI
         */
        GridPane playerOneHand = new GridPane();
        playerOneHand.setVgap(10);
        playerOneHand.setHgap(10);
        playerOneHand.setPadding(new Insets(10,10,10,10));
        playerOneHand.add(cardSlots[0], 0, 0,1, 1);
        playerOneHand.add(cardSlots[1],1,0,1,1);
        playerOneHand.add(cardSlots[2],2,0,1,1);
        playerOneHand.add(cardSlots[3],3,0,1,1);

        GridPane playerTwoHand = new GridPane();
        playerTwoHand.setVgap(10);
        playerTwoHand.setHgap(10);
        playerTwoHand.setPadding(new Insets(10,10,10,10));
        playerTwoHand.add(cardSlots[4], 0, 0,1, 1);
        playerTwoHand.add(cardSlots[5],1,0,1,1);
        playerTwoHand.add(cardSlots[6],2,0,1,1);
        playerTwoHand.add(cardSlots[7],3,0,1,1);

        GridPane playingField = new GridPane();
        playingField.add(playerOneHand,0,0,4,1);
        playingField.add(playerTwoHand,4,0, 4,1);

        Scene scene = new Scene(playingField, 1024, 800, true);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Rectangle MakeRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(200);
        rectangle.setHeight(350);
        return rectangle;
    }

    public Text MakeText(Card card){
        Text text = new Text();
        text.setText(card.GetValueAsString());
        text.setFill(Color.WHITE);
        text.setFont(Font.font(36));
        text.setBoundsType(TextBoundsType.VISUAL);
        return text;
    }

    public int ReverseSign(Card card){
        int value = -card.GetValue();
        card.SetValue(value);
        card.text.setText(Integer.toString(value));
        return value;
    }
}
