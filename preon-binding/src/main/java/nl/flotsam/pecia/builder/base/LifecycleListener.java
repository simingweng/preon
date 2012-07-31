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

/**
 * The interface for receiving callbacks from a {@link DefaultDocumentBuilder}
 * subclass.
 *
 * @author Wilfred Springer
 *
 */
public interface LifecycleListener {

    /**
     * Invoked when a new document element is commited to the stack.
     *
     * @param documentElement
     *            The document element pushed on the stack.
     */
    void started(Object documentElement);

    /**
     * Invoked when a document element is popped from the stack.
     *
     * @param documentElement
     *            The document element popped from the stack.
     */
    void ended(Object documentElement);

}
