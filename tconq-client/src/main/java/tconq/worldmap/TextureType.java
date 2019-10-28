package tconq.worldmap;

public abstract class TextureType {
    ITextureColor color;

    public TextureType(ITextureColor color) {
        this.color = color;
    }

    public abstract String getName();
}
