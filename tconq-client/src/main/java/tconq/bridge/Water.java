package tconq.bridge;

public class Water extends TextureType {

    private String name;

    public Water(ITextureColor color) {
        super(color);
        name = "water";
    }

    @Override
    public String getName() {
        return name;
    }
}
