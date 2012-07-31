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
 * A document element representing an itemized list. Note that - contrary to the
 * DocBook document model - the {@link ItemizedList} abstraction is used both
 * for itemized lists as well as ordered lists. So, if you invoke
 * {@link Contents#orderedList()}, you still get an {@link ItemizedList} in
 * return.
 *
 * @author Wilfred Springer
 *
 * @param <T>
 *            The type of the parent container element.
 */
public interface ItemizedList<T> extends DocumentElement<T> {

	/**
	 * Adds a new list item, populates it with a {@link Para} containing the
	 * <code>text</code> passed in.
	 *
	 * @param text
	 *            The text to be inserted into the list item.
	 * @return The current itemized list.
	 */
	ItemizedList<T> item(String text);

	/**
	 * Adds a new list item, allowing the list item to be populated in any
	 * specific way you desire.
	 *
	 * @return The {@link ListItem} to be populated.
	 */
	ListItem<? extends ItemizedList<T>> item();

}
