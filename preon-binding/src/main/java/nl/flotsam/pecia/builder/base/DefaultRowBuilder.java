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

import nl.flotsam.pecia.Row;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.Initializer;
import nl.flotsam.pecia.builder.RowBuilder;

public class DefaultRowBuilder<T> extends AbstractBuilder<T> implements
        RowBuilder<T> {

    private boolean header;

    private boolean first;

    public DefaultRowBuilder(DocumentBuilder builder, T parent, boolean header,
            boolean first, LifecycleListener listener) {
        super(builder, parent, listener);
        this.header = header;
        this.first = first;
    }

    public T entry() {
        T parent = getParent();
        if (parent instanceof Initializer) {
            ((Initializer) parent).start();
        }
        return parent;
    }

    public Row<T> start() {
        getListener().started(this);
        return this;
    }

    public boolean isHeader() {
        return header;
    }

    public boolean isFirst() {
        return first;
    }

}
