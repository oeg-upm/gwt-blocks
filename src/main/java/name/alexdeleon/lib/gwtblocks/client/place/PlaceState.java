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

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * @author Alexander De Leon
 */
public class PlaceState extends JSONObject {

	protected PlaceState() {

	}

	protected PlaceState(JSONObject obj) {
		for (String key : obj.keySet()) {
			put(key, obj.get(key));
		}
	}

	protected void put(String key, String value) {
		JSONValue val = JSONNull.getInstance();
		if (value != null) {
			val = new JSONString(value);
		}
		put(key, val);
	}

	protected void put(String key, Number value) {
		JSONValue val = JSONNull.getInstance();
		if (value != null) {
			val = new JSONNumber(value.doubleValue());
		}
		put(key, val);
	}

	protected void put(String key, boolean value) {
		put(key, JSONBoolean.getInstance(value));
	}

}
