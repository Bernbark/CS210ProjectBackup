package sample;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Card {
    int value;
    Text text;
    Rectangle rectangle;

    public Card(){
        value = 0;

    }

    public Card(int value){
        this.value = value;

    }

    public String GetValueAsString(){
        return Integer.toString(value);
    }

    public void SetValue(int value){
        this.value = value;
    }

    public int GetValue(){
        return value;
    }

    public void SetText(Text text){
        this.text = text;
    }

    public void SetRectangle(Rectangle rect){
        this.rectangle = rect;
    }
}
