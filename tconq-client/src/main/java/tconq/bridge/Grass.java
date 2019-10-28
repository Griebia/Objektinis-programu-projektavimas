package tconq.bridge;

public class Grass extends TextureType {

    private String name;

    public Grass(ITextureColor color) {
        super(color);
        name = "grass";
    }

    @Override
    public String getName() {
        return name + color.getColor();
    }


}
