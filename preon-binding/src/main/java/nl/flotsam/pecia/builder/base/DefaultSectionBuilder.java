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

import nl.flotsam.pecia.AnnotatedSection;
import nl.flotsam.pecia.Documenter;
import nl.flotsam.pecia.ItemizedList;
import nl.flotsam.pecia.Para;
import nl.flotsam.pecia.Section;
import nl.flotsam.pecia.SimpleContents;
import nl.flotsam.pecia.Table2Cols;
import nl.flotsam.pecia.Table3Cols;
import nl.flotsam.pecia.Table4Cols;
import nl.flotsam.pecia.Table5Cols;
import nl.flotsam.pecia.Verbatim;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.SectionBuilder;

public class DefaultSectionBuilder<T> extends AbstractBuilder<T> implements
        SectionBuilder<T> {

    private String title;

    private boolean started;

    private String mark;

    public DefaultSectionBuilder(DocumentBuilder builder, String title,
            T parent, LifecycleListener listener) {
        super(builder, parent, listener);
        this.title = title;
        this.started = false;
    }

    public ItemizedList<? extends Section<T>> itemizedList() {
        considerStart();
        return getBuilder().createItemizedList(this, getBuilder()).start();
    }

    public ItemizedList<? extends Section<T>> orderedList() {
        considerStart();
        return getBuilder().createOrderedList(this, getBuilder()).start();
    }

    public Para<? extends Section<T>> para() {
        considerStart();
        return getBuilder().createPara(this, getBuilder()).start();
    }

    public AnnotatedSection<? extends Section<T>> section(String title) {
        considerStart();
        return getBuilder().createSection(this, title, getBuilder()).start();
    }

    public Table2Cols<? extends Section<T>> table2Cols() {
        considerStart();
        return getBuilder().createTable2Cols(this, getBuilder()).start();
    }

    public Table3Cols<? extends Section<T>> table3Cols() {
        considerStart();
        return getBuilder().createTable3Cols(this, getBuilder()).start();
    }

    public Table4Cols<? extends Section<T>> table4Cols() {
        considerStart();
        return getBuilder().createTable4Cols(this, getBuilder()).start();
    }

    public Table5Cols<? extends Section<T>> table5Cols() {
        considerStart();
        return getBuilder().createTable5Cols(this, getBuilder()).start();
    }

    public Verbatim<? extends Section<T>> verbatim() {
        considerStart();
        return getBuilder().createVerbatim(this, getBuilder()).start();
    }

    private void considerStart() {
        if (!started) {
            getListener().started(this);
            started = true;
        }
    }

    public AnnotatedSection<T> start() {
        // getListener().started(this);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AnnotatedSection<T> mark(String id) {
        this.mark = id;
        return this;
    }

    public String getId() {
        return mark;
    }

    public Section<T> para(String text) {
        getBuilder().createPara(this, getBuilder()).start().text(text).end();
        return this;
    }

    public Section<T> document(Documenter<SimpleContents<?>> target) {
        target.document(this);
        return this;
    }

}
