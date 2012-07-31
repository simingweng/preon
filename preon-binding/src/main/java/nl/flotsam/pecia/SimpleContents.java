package nl.flotsam.pecia;

public interface SimpleContents<V> {

    /**
     * Adds a paragraph.
     * 
     * @return The paragraph added.
     */
    Para<? extends V> para();

    /**
     * Adds a paragraph ({@link Para}) containing the text passed in.
     * 
     * @param text
     *            The text to be added to the paragraph.
     * @return The current element.
     */
    V para(String text);

    /**
     * Adds verbatim content.
     * 
     * @return The object representing verbatim content.
     */
    Verbatim<? extends V> verbatim();

    /**
     * Adds an itemized list.
     * 
     * @return The itemized list just created.
     */
    ItemizedList<? extends V> itemizedList();

    /**
     * Adds an ordered list.
     * 
     * @return The ordered list just created.
     */
    ItemizedList<? extends V> orderedList();

    /**
     * Adds a two-column table.
     * 
     * @return A two column table.
     */
    Table2Cols<? extends V> table2Cols();

    /**
     * Adds a three-column table.
     * 
     * @return A three column table.
     */
    Table3Cols<? extends V> table3Cols();

    /**
     * Adds a four-column table.
     * 
     * @return A four column table.
     */
    Table4Cols<? extends V> table4Cols();

    /**
     * Adds a five-column table.
     * 
     * @return A five column table.
     */
    Table5Cols<? extends V> table5Cols();
    
    /**
     * Generates documentation, and returns the current context.
     * 
     * @param target The object generating the documentation.
     */
    V document(Documenter<SimpleContents<?>> target);

    
}
