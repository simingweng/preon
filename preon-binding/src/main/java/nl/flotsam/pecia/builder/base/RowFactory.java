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

package nl.flotsam.pecia.builder.base;

import nl.flotsam.pecia.Entry;
import nl.flotsam.pecia.LastEntry;
import nl.flotsam.pecia.Row;
import nl.flotsam.pecia.builder.DocumentBuilder;

/**
 * A utility class supporting the construction of tables.
 * 
 * @author Wilfred Springer
 * 
 */
public class RowFactory {

    /**
     * Constructs a table row.
     * 
     * @param <T>
     *            The type of parent.
     * @param parent
     *            The parent.
     * @param builder
     *            The object to be used to track progress.
     * @param header
     *            A boolean indicating if the row being built is meant to
     *            produce a header.
     * @return A {@link LastColDef} object, allowing you to append more column
     *         definition and eventually invoke {@link ColDef#create(boolean)}.
     */
    public static <T> LastColDef<T> start(T parent, DocumentBuilder builder,
            boolean header) {
        return new LastColDef<T>(builder, parent, header);
    }

    /**
     * An object allowing you to either create a new column, or to construct a
     * new row.
     * 
     * @param <T>
     *            The type of parent.
     */
    public static class ColDef<T> {

        /**
         * The object used to track progress.
         */
        private DocumentBuilder builder;

        /**
         * The parent.
         */
        private T parent;

        /**
         * 
         */
        private boolean header;

        /**
         * Constructs a new instance.
         * 
         * @param builder
         *            The object used to track progress while building the
         *            document.
         * @param parent
         *            The parent object.
         * @param header
         *            A boolean indicating if this cell represents a header cell
         *            or not.
         */
        public ColDef(DocumentBuilder builder, T parent, boolean header) {
            this.builder = builder;
            this.parent = parent;
            this.header = header;
        }

        /**
         * Constructs the row.
         * 
         * @param first
         *            Whether or not this is the first row of the table.
         * @return The row consisting of all column elements.
         */
        public Row<Entry<T>> create(boolean first) {
            return builder.createRow(
                    (Entry<T>) builder.createEntry(parent, header, builder),
                    header, first, builder).start();
        }

        /**
         * Inserts a new column.
         * 
         * @return A {@link ColDef} defining the new column.
         */
        public ColDef<Entry<T>> col() {
            return new ColDef<Entry<T>>(builder, builder.createEntry(parent,
                    header, builder), header);
        }

    }

    public static class LastColDef<T> {

        private DocumentBuilder builder;

        private T parent;

        private boolean header;

        public LastColDef(DocumentBuilder builder, T parent, boolean header) {
            this.builder = builder;
            this.parent = parent;
            this.header = header;
        }

        public Row<LastEntry<T>> create(boolean first) {
            return builder.createRow(
                    (LastEntry<T>) builder.createLastEntry(parent, header,
                            builder), header, first, builder).start();
        }

        public ColDef<LastEntry<T>> col() {
            return new ColDef<LastEntry<T>>(builder, builder.createLastEntry(
                    parent, header, builder), header);
        }

    }

}
