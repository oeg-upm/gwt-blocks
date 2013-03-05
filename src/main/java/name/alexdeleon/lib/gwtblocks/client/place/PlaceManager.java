/**
 * Copyright (c) 2009 Alexander De Leon Battista
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package name.alexdeleon.lib.gwtblocks.client.place;

import name.alexdeleon.lib.gwtblocks.client.event.EventBus;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Alexander De Leon
 */
@Singleton
public class PlaceManager {

	private static final String DELIMITER = "::";

	private final EventBus eventBus;
	private final PlaceFactory factory;
	private Place current;

	@Inject
	public PlaceManager(EventBus eventBus, PlaceFactory factory) {
		this.eventBus = eventBus;
		this.factory = factory;
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			public void onValueChange(ValueChangeEvent<String> event) {
				processHistoryToken(event.getValue());
			}
		});
	}

	public void goTo(Place place) {
		History.newItem(createHistoryToken(place), false);
		firePlaceChangeEvent(place);
	}

	private void processHistoryToken(String historyToken) {
		int delimiterPos = historyToken.indexOf(DELIMITER);
		String name, state;
		if (delimiterPos == -1) {
			name = historyToken;
			state = null;
		} else {
			name = historyToken.substring(0, delimiterPos);
			state = historyToken.substring(delimiterPos + DELIMITER.length(), historyToken.length());
		}
		Place place = factory.getPlace(name);
		if (place != null) {
			if (state != null) {
				place.deserializeState(state);
			}
			firePlaceChangeEvent(place);
		}
	}

	private String createHistoryToken(Place place) {
		String state = place.serializeState();
		String name = place.getName();
		if (state == null || state.length() == 0) {
			return name;
		} else {
			return name + DELIMITER + state;
		}
	}

	private void firePlaceChangeEvent(Place place) {
		current = place;
		eventBus.fireEvent(new PlaceChangeEvent(place));
	}

	public Place getCurrentPlace() {
		return current;
	}

}
