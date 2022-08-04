package UI;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ChoiceButton extends Button {
	public ChoiceButton() {
		super.setText("");
		ImageView img= new ImageView("https://www.freeiconspng.com/thumbs/plus-icon/plus-icon-black-2.png");
        img.setFitHeight(9.5);
        img.setFitWidth(9.5);
    super.setGraphic(img);
	}

}
