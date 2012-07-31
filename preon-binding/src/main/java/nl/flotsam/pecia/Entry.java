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
 * The representation of a cell in a table.
 * 
 * @author Wilfred Springer
 * 
 * @param <T>
 *            The type of the next entry. (Either {@link Entry} or {@link LastEntry}.)
 */
public interface Entry<T> extends SimpleContents<Entry<T>> {

    /**
     * Creates a paragraph.
     * 
     * @return A paragraph embedded in the table cell.
     */
    Para<? extends Entry<T>> para();

    /**
     * Adds a new paragraph containing just the text passed in.
     * 
     * @param text The text to be added to the paragraph.
     * @return The current context.
     */
    Entry<T> para(String text);

    /**
     * Creates the next table cell entry.
     * 
     * @return The new table cell entry.
     */
    T entry();

}
