package UI;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MinusButton extends Button {
	public MinusButton() {
		super.setText("");
		ImageView img= new ImageView("https://www.pngarts.com/files/3/Minus-PNG-Download-Image.png");
        img.setFitHeight(9.5);
        img.setFitWidth(9.5);
    super.setGraphic(img);
	}
}
