package UI;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SearchButton extends Button {
    public SearchButton() {
        super.setText("");
        ImageView img= new ImageView("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Search_Icon.svg/500px-Search_Icon.svg.png");
        img.setFitHeight(10.5);
        img.setFitWidth(10.5);
    super.setGraphic(img);
    }
}