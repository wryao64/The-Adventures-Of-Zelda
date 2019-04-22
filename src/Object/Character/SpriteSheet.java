package Object.Character;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    private int imageWidth;
    private int imageHeight;
    private int sidePadding;
    private int verticalPadding;


    public SpriteSheet(BufferedImage image, int imageWidth, int imageHeight) {
        this.image = image;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
    }
}
