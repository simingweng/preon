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

import nl.flotsam.pecia.DocumentElement;

/**
 * An implementation of the {@link LifecycleListener} interface that decorates
 * another {@link LifecycleListener}, adding support for preserving the order
 * of constructing elements. Withtout this class, you would basically be able to
 * hold on to a certain document element, continue to add elements to it, and
 * then all of a sudden also add content to the document element that you held
 * on to.
 *
 * @author Wilfred Springer
 *
 */
public class OrderPreservingLifecycleListener implements LifecycleListener {

    /*
     * The {@link LifecycleListener} wrapped by an instance of this class.
     */
    private LifecycleListener listener;

    /**
     * The current document element.
     */
    private Object current;

    public OrderPreservingLifecycleListener(LifecycleListener listener) {
        this.listener = listener;
    }

    public OrderPreservingLifecycleListener() {
        this.listener = new NullLifecycleListener();
    }

    public void ended(Object documentElement) {
        if (current != documentElement) {
            throw new RuntimeException("Out of order document construction.");
        } else {
            current = ((DocumentElement) documentElement).getParent();
        }
    }

    public void started(Object documentElement) {
        DocumentElement element = (DocumentElement) documentElement;
        if (current == null || current == element.getParent()) {
            current = documentElement;
        } else {
            throw new RuntimeException("Out of order document construction.");
        }
    }

}
