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
 * An annotated {@link Article}. {@link Article Articles} can have metadata.
 * This interface allows you to add that metadata. Once you start adding content
 * to the article, there is no way of turning back to the article's metadata.
 * 
 * @author Wilfred Springer
 * 
 * @param <T>
 *            The outer context.
 */
public interface AnnotatedArticle<T> extends Article<T> {

    /**
     * Adds author metadata to the start of the article.
     * 
     * @return The {@link Author} context, allowing you to add author metadata.
     */
    Author<AnnotatedArticle<T>> author();

    /**
     * Adds a copyright notice to the start of the article.
     * 
     * @param holder
     *            The copyright holder.
     * @param year
     *            The copyright year.
     * @return This context.
     */
    AnnotatedArticle<T> copyright(String holder, int... year);

}
