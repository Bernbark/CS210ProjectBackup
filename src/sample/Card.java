package sample;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The Card class holds on to any information that a Card might contain, such as its shape, size, text, and value.
 */
public class Card {
    // value of the Card
    int value;
    // text that will hold a String representation of value
    Text text;
    // The shape, size, color, etc. of the card
    Rectangle rectangle;

    // Default constructor
    public Card(){
        value = 0;
        text = new Text();
        rectangle = new Rectangle();
    }

    /**
     * Takes a value and makes a new card with it, however it will have empty text and rectangle objects that need
     * filling out
     * @param value
     */
    public Card(int value){
        this.value = value;
        text = new Text();
        rectangle = new Rectangle();
    }

    /**
     * A helper method to get the cards value returned as a String
     * @return String of value
     */
    public String GetValueAsString(){
        return Integer.toString(value);
    }

    public void SetValue(int value){
        this.value = value;
    }

    public int GetValue(){
        return value;
    }

    /**
     * We send the Text UI object in and we can manipulate it mid-game
     * @param text
     */
    public void SetText(Text text){
        this.text = text;
    }

    /**
     * We send the Rectangle UI object in and we can manipulate it mid-game
     * @param rect
     */
    public void SetRectangle(Rectangle rect){
        this.rectangle = rect;
    }
}
