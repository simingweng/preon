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

import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.TableBuilder;

public abstract class AbstractTableBuilder<T> extends AbstractBuilder<T>
        implements TableBuilder {

    public AbstractTableBuilder(DocumentBuilder builder, T parent, LifecycleListener listener) {
        super(builder, parent, listener);
    }

    public enum State {

        BeforeFirstHeaderRow {

            public State next(boolean header) {
                return header ? BeforeFirstBodyRow : ExpectingOnlyBodyRows;
            }

        },

        BeforeFirstBodyRow {

            public State next(boolean header) {
                return header ? BeforeFirstBodyRow : ExpectingOnlyBodyRows;
            }

        },

        ExpectingOnlyBodyRows {

            public State next(boolean header) {
                if (header) {
                    throw new IllegalStateException("Not expected.");
                } else {
                    return ExpectingOnlyBodyRows;
                }
            }

        };

        public abstract State next(boolean header);

    }

    private State state = State.BeforeFirstHeaderRow;

    private boolean isFirst(boolean header) {
        if (state == State.BeforeFirstHeaderRow && header) {
            return true;
        } else if (state == State.BeforeFirstBodyRow && !header) {
            return true;
        } else if (state == State.BeforeFirstHeaderRow && !header) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean proceed(boolean header) {
        boolean result = isFirst(header);
        state = state.next(header);
        return result;
    }

}
