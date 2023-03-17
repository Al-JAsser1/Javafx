package sample;

import javafx.scene.paint.Color;

public enum MyColor {
    Red(Color.RED),Blue(Color.BLUE),Black(Color.BLACK);

    private Color color;
    MyColor(Color color){
        this.color = color;
    }
}
