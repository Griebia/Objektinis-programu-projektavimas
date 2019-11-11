package tconq.io;

/**
 * Please use this class booleans to enable your console logs!
 * TODO:Please use this class booleans to enable your console logs!
 */
public class DebugHandler {

    public final boolean requestDebug = false;
    public final boolean factoryDebug = true;
    public final boolean facadeDebug = false;
    public final boolean fpsDebug = false;

    public static DebugHandler debugmode = new DebugHandler();

    private DebugHandler(){}

}