package sample;

import javafx.application.Application;
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

import java.util.Random;

public class Main extends Application {

    // Main window width and height
    int windowHeight = 800;
    int windowWidth = 1310;

    // Card width and height
    int cardWidth = 120;
    int cardHeight = 140;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Max and min values for randomizing
        int max = 5;
        int min = -5;

        // Creating our UI elements and Card variables. It is convenient to use arrays for neatness and
        // so we can avoid lengthy code and use a for loop for initializing
        final Card cards[] = new Card[8];
        Button reverseSignButtons[] = new Button[8];
        StackPane cardSlots[] = new StackPane[8];
        GridPane cardOrganizerPanes[] = new GridPane[8];

        /**
         * This section creates all of the cards that the players will hold in their hands. cards[0-3] are Player One
         * cards[4-7] are Player Two, it also attaches button functionality to the cards and organizes them a little
         * more neatly
         */
        for(int i = 0; i < cards.length; i++){

            // We have to initialize the elements before the window can be displayed
            cardSlots[i] = new StackPane();

            cardOrganizerPanes[i] = new GridPane();
            cardOrganizerPanes[i].setHgap(10);
            cardOrganizerPanes[i].setVgap(10);
            cardOrganizerPanes[i].setPadding(new Insets(20,20,20,20));

            // Generating numbers to be sent into the card values making sure it's not 0 for hand cards
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            while (randomNum == 0){
                randomNum = rand.nextInt((max - min) + 1) + min;
            }

            // Creating cards with their values and attaching text and rectangles to them
            cards[i] = new Card(randomNum);
            cards[i].SetText(MakeText(cards[i]));
            cards[i].SetRectangle(MakeRectangle());

            // The event handle does not like taking in the i value, so we create a final int and set it equal to i
            // to make the compiler happy
            final int index = i;
            reverseSignButtons[i] = new Button("Reverse Sign");
            reverseSignButtons[i].setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    ReverseSign(cards[index]);
                }
            });

            // This section is where we organize the UI elements into specific slots to make things neater, making sure
            // buttons and text aren't overlapping
            cardOrganizerPanes[i].add(cards[i].text, 2,1,2,1);
            cardOrganizerPanes[i].add(reverseSignButtons[i], 1,2,2,1);
            cardSlots[i].getChildren().addAll(cards[i].rectangle, cardOrganizerPanes[i]);
        }

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

        /**
         * Construction of Player 2's Hand UI
         */
        GridPane playerTwoHand = new GridPane();
        playerTwoHand.setVgap(10);
        playerTwoHand.setHgap(10);
        playerTwoHand.setPadding(new Insets(10,10,10,10));
        playerTwoHand.add(cardSlots[4], 0, 0,1, 1);
        playerTwoHand.add(cardSlots[5],1,0,1,1);
        playerTwoHand.add(cardSlots[6],2,0,1,1);
        playerTwoHand.add(cardSlots[7],3,0,1,1);

        /**
         * Holds the player's hands. Probably needs to be replaced for better looking UI
         */
        GridPane playingField = new GridPane();
        playingField.add(playerOneHand,0,0,4,1);
        playingField.add(playerTwoHand,5,0, 4,1);

        /**
         * Must make a scene to display things on the stage
         */
        Scene scene = new Scene(playingField, windowWidth, windowHeight, true);

        /**
         * Making our stage display our scene.
         */
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This has to be here in order to run the program.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * We can create rectangles of the proper card size on the fly.
     *
     * Used to construct a card with SetRectangle(MakeRectangle())
     * @return rectangle
     */
    public Rectangle MakeRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(cardWidth);
        rectangle.setHeight(cardHeight);
        return rectangle;
    }

    /**
     * Creating a text object to be used in our cards.
     *
     * It takes a card object in order to find its value and set the text to that value.
     *
     * Used to construct a card.
     * @param card
     * @return Text object
     */
    public Text MakeText(Card card){
        Text text = new Text();
        text.setText(card.GetValueAsString());
        text.setFill(Color.WHITE);
        text.setFont(Font.font(36));
        text.setBoundsType(TextBoundsType.VISUAL);
        return text;
    }

    /**
     * This takes a card object in order to get its value, and then sets its value to the opposite (either positive or
     * negative), then also updates the world card object's text value at the same time.
     * @param card
     */
    public void ReverseSign(Card card){
        int value = -card.GetValue();
        card.SetValue(value);
        card.text.setText(Integer.toString(value));
    }
}
