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

import nl.flotsam.pecia.Documenter;
import nl.flotsam.pecia.ItemizedList;
import nl.flotsam.pecia.LastEntry;
import nl.flotsam.pecia.Para;
import nl.flotsam.pecia.SimpleContents;
import nl.flotsam.pecia.Table2Cols;
import nl.flotsam.pecia.Table3Cols;
import nl.flotsam.pecia.Table4Cols;
import nl.flotsam.pecia.Table5Cols;
import nl.flotsam.pecia.Verbatim;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.LastEntryBuilder;

public class DefaultLastEntryBuilder<T> extends AbstractBuilder<T> implements
        LastEntryBuilder<T> {

    private boolean header;

    public DefaultLastEntryBuilder(DocumentBuilder builder, T parent,
            boolean header, LifecycleListener listener) {
        super(builder, parent, listener);
        this.header = header;
    }

    public T end() {
        return getParent();
    }

    public Para<? extends LastEntry<T>> para() {
        return getBuilder().createPara(this, getBuilder()).start();
    }

    boolean isHeader() {
        return header;
    }

    public LastEntryBuilder<T> start() {
        getListener().started(this);
        return this;
    }

    public LastEntry<T> para(String text) {
        getBuilder().createPara(this, getBuilder()).start().text(text).end();
        return this;
    }

    public LastEntry<T> document(Documenter<SimpleContents<?>> target) {
        target.document(this);
        return this;
    }

    public ItemizedList<? extends LastEntry<T>> itemizedList() {
        return getBuilder().createItemizedList(this, getBuilder()).start();
    }

    public ItemizedList<? extends LastEntry<T>> orderedList() {
        return getBuilder().createOrderedList(this, getBuilder()).start();
    }

    public Table2Cols<? extends LastEntry<T>> table2Cols() {
        return getBuilder().createTable2Cols(this, getBuilder()).start();
    }

    public Table3Cols<? extends LastEntry<T>> table3Cols() {
        return getBuilder().createTable3Cols(this, getBuilder()).start();
    }

    public Table4Cols<? extends LastEntry<T>> table4Cols() {
        return getBuilder().createTable4Cols(this, getBuilder()).start();
    }

    public Table5Cols<? extends LastEntry<T>> table5Cols() {
        return getBuilder().createTable5Cols(this, getBuilder()).start();
    }

    public Verbatim<? extends LastEntry<T>> verbatim() {
        return getBuilder().createVerbatim(this, getBuilder()).start();
    }

}
