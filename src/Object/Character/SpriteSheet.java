package Object.Character;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    private int imageWidth;
    private int imageHeight;

    public SpriteSheet(BufferedImage image, int imageWidth, int imageHeight) {
        this.image = image;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
    }

    /**
     * Cut outs a sub image at the given co-ords.
     */
    public BufferedImage getImage(int x, int y) {
        BufferedImage smallImg = image.getSubimage(x, y, imageWidth, imageHeight);
        return smallImg;
    }

    /**
     * Cut outs a sub image at the given co-ords.
     */
    public BufferedImage getImage(int x, int y, int w, int h) {
        BufferedImage smallImg = image.getSubimage(x, y, w, h);
        return smallImg;
    }

    /**
     * Cut outs a mirrored sub image at the given co-ords.
     */
    public BufferedImage getFlippedImage(int x, int y) {
        BufferedImage smallImg = image.getSubimage(x, y, imageWidth, imageHeight);
        BufferedImage leftImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = leftImage.createGraphics();
        g.drawImage(smallImg, 0, 0, imageWidth, imageHeight, imageWidth, 0, 0, imageHeight, null);
        g.dispose();

        return leftImage;
    }

    public BufferedImage getPerpendicularImage(int x, int y) {
        BufferedImage smallImg = image.getSubimage(x, y, imageWidth, imageHeight);
        BufferedImage rotatedImage = new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = rotatedImage.createGraphics();
        g.translate((imageHeight - imageWidth) / 2, (imageHeight - imageWidth) / 2);
        g.rotate(Math.PI / 2, imageHeight / 2, imageWidth / 2);
        g.drawRenderedImage(smallImg, null);
        g.dispose();

        return rotatedImage;
    }
}
