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
 * The interface extended by tables with a specific number of columns.
 *
 * @author Wilfred Springer
 *
 * @param <T>
 *            The class of the element containing this element.
 * @param <V>
 *            The type of row. (An object allowing you to populate all columns.)
 */
public interface Table<T, V> extends DocumentElement<T> {

    /**
     * The top row of the table.
     *
     * @return The object allowing you to populate a header row.
     */
    Row<V> header();

    /**
     * A non-header row.
     *
     * @return The object allowing you to populate an ordinary row.
     */
    Row<V> row();

}
