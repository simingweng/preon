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
import nl.flotsam.pecia.Footnote;
import nl.flotsam.pecia.Para;
import nl.flotsam.pecia.ParaContents;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.ParaBuilder;

public class DefaultParaBuilder<T> extends AbstractBuilder<T> implements ParaBuilder<T> {

    public DefaultParaBuilder(DocumentBuilder builder, T parent, LifecycleListener listener) {
        super(builder, parent, listener);
    }

    public Para<T> code(String text) {
        return this;
    }

    public Para<T> email(String email) {
        return this;
    }

    public Para<T> emphasis(String text) {
        return this;
    }

    public Footnote<? extends Para<T>> footnote() {
        return getBuilder().createFootnote(this, getBuilder()).start();
    }

    public Para<T> text(String text) {
        return this;
    }

    public Para<T> xref(String id) {
        return this;
    }

    public ParaBuilder<T> start() {
        getListener().started(this);
        return this;
    }

    public Para<T> link(Object id, String text) {
        return this;
    }

    public Para<T> term(Object id, String text) {
        return this;
    }

    public Para<T> footnote(String text) {
        getBuilder().createFootnote(this, getBuilder()).start().para().text(text).end().end();
        return this;
    }

    public Para<T> document(Documenter<ParaContents<?>> target) {
        target.document(this);
        return this;
    }

}
