package tconq.worldmap;

public abstract class Texture {
    ITextureColor color;
    public Texture(ITextureColor color) {
        this.color = color;
    }
}
