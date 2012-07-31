package nl.flotsam.pecia;

/**
 * The interface defined for callbacks. (Used when you don't tell the framework
 * what you want to generate, but the framework asks you what you want to
 * generate.)
 * 
 * @author Wilfred Springer
 * 
 * @param <T>
 *            The type of context on which this class will render its
 *            documentation.
 */
public interface Documenter<T> {

    /**
     * Generates some documentation.
     * 
     * @param target
     *            The object receiving the documentation.
     */
    void document(T target);

}
