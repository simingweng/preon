/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.flotsam.pecia;

/**
 * The interface defining all operations for adding content to a {@link Para},
 * without any operations to return to the outer context. Useful in cases in
 * which you want some operation to add contents to a paragraph,
 * <em>without</em> having the ability to write outside of that paragraph.
 * 
 * @author Wilfred Springer
 * 
 * @param <T>
 *            The type of outer context.
 */
public interface ParaContents<T> {

    /**
     * Constructs a link to the documet item identified by the specified id.
     * 
     * @param id
     *            The identifier of the document element referenced.
     * @param text
     *            The text to be included in the link.
     * @return The parent element of the link.
     */
    Para<T> link(Object id, String text);

    /**
     * Adds an inline emphasized section.
     * 
     * @param text
     *            The text to be emphasized.
     * @return The same parent element as the one on which this operation is
     *         invoked.
     */
    Para<T> emphasis(String text);

    /**
     * Adds some <em>code</em> to the paragraph. (Processing expectation:
     * monospaced, 'code'-like rendering.)
     * 
     * @param text
     *            The text to be produced as code.
     * @return The same parent element as the one on which this operation is
     *         invoked.
     */
    Para<T> code(String text);

    /**
     * Appends some text to the paragraph.
     * 
     * @param text
     *            The text to be appended to the paragraph.
     * @return The same parent element as the one on which this operation is
     *         invoked.
     */
    Para<T> text(String text);

    /**
     * Inserts a footnote.
     * 
     * @return The footnote inserted.
     */
    Footnote<? extends Para<T>> footnote();

    /**
     * Inserts a footnote, containing a {@link Para} containing the text passed
     * in.
     * 
     * @param text
     *            The text in the paragraph added to the footnote.
     */
    Para<T> footnote(String text);

    /**
     * Inserts an email address.
     * 
     * @param email
     *            The email address.
     * @return The paragraph in which the email address is inserted.
     */
    Para<T> email(String email);

    /**
     * Inserts a cross reference to another document element.
     * 
     * @param id
     *            The identifier of the element to which you are referring.
     * @return The paragraph in which the email address is inserted.
     */
    Para<T> xref(String id);

    /**
     * Inserts a term into the content.
     * 
     * @param id
     *            The object identifying this term.
     * @param text
     *            The term itself.
     */
    Para<T> term(Object id, String text);
    
    /**
     * Generates documentation, and returns the current context.
     * 
     * @param target The object generating the documentation.
     */
    Para<T> document(Documenter<ParaContents<?>> target);

}
