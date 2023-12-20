package org.alexxarisis.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourcesUtil {

    private static BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(Objects.requireNonNull(
                    ResourcesUtil.class.getResource(String.format("/images/%s", imageName)))
            );
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResourcesUtil.getImage("no_image.png");
        }
    }

    public static ImageIcon getIcon(String imageName) {
        return new ImageIcon(Objects.requireNonNull(
                getImage(imageName))
        );
    }

    public static ImageIcon getIcon(String imageName, int width, int height) {
        return new ImageIcon(Objects.requireNonNull(
                getImage(imageName)).getScaledInstance(width, height, Image.SCALE_SMOOTH)
        );
    }
}

