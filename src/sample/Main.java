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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        final Card card = new Card(1);


        Button button = new Button("Reverse Sign");
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                ReverseSign(card);
            }


        });

        card.SetText(MakeText(card));
        card.SetRectangle(MakeRectangle());

        StackPane cardSlotOne = new StackPane();
        cardSlotOne.getChildren().addAll(card.rectangle,card.text, button);

        StackPane cardSlotTwo = new StackPane();
        Rectangle rectangle1 = MakeRectangle();

        Card card1 = new Card(2);
        Text text1 = MakeText(card1);
        text1.setText(card1.GetValueAsString());
        card1.SetText(text1);
        card1.SetRectangle(rectangle1);
        cardSlotTwo.getChildren().addAll(rectangle1,text1,button);

        /**
         * Construction of Player 1's Hand UI
         */
        GridPane playerOneHand = new GridPane();
        playerOneHand.setVgap(10);
        playerOneHand.setHgap(10);
        playerOneHand.setPadding(new Insets(10,10,10,10));
        playerOneHand.add(cardSlotOne, 0, 0,1, 1);
        playerOneHand.add(cardSlotTwo,1,0,1,1);

        GridPane playerTwoHand = new GridPane();
        playerTwoHand.setVgap(10);
        playerTwoHand.setHgap(10);
        playerTwoHand.setPadding(new Insets(10,10,10,10));
        playerTwoHand.add(cardSlotOne, 0, 0,1, 1);
        playerTwoHand.add(cardSlotTwo,1,0,1,1);

        GridPane playingField = new GridPane();
        playingField.add(playerOneHand,0,0,2,1);
        playingField.add(playerTwoHand,2,0, 2,1);

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
