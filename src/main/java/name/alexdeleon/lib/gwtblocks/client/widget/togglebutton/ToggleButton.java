/**
 * Copyright (c) 2010 Alexander De Leon Battista
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
package name.alexdeleon.lib.gwtblocks.client.widget.togglebutton;

import name.alexdeleon.lib.gwtblocks.client.event.HasToggleHandler;
import name.alexdeleon.lib.gwtblocks.client.event.ToggleEvent;
import name.alexdeleon.lib.gwtblocks.client.event.ToggleHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class ToggleButton extends Composite implements HasToggleHandler, HasMouseDownHandlers, HasMouseOutHandlers,
		HasMouseOverHandlers, HasMouseUpHandlers {

	public interface Stylesheet {

		String toggleButton();

		String toggleButtonPressed();

		String toggleButtonReleased();

		String toggleButtonMouseDown();

	}

	private final FlowPanel panel = new FlowPanel();
	private Stylesheet style;
	private boolean state;

	public ToggleButton(Stylesheet style) {
		setStylesheet(style);
		initWidget(panel);
		bindEvents();
		setState(false, false); // by default the button is off.
	}

	public void setStylesheet(Stylesheet style) {
		this.style = style;
		panel.setStyleName(style.toggleButton());
	}

	@Override
	public void setWidget(Widget widget) {
		panel.add(widget);
	}

	public void setState(boolean isPressed) {
		setState(isPressed, true);
	}

	public void setState(boolean isPressed, boolean fireEvent) {
		state = isPressed;
		if (fireEvent) {
			fireToggleEvent();
		}
		applyToogleStyle();
	}

	public boolean isPressed() {
		return state;
	}

	public void addToggleHandler(ToggleHandler handler) {
		addHandler(handler, ToggleEvent.getType());
	}

	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return addDomHandler(handler, MouseDownEvent.getType());
	}

	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return addDomHandler(handler, MouseUpEvent.getType());
	}

	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return addDomHandler(handler, MouseOutEvent.getType());
	}

	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	protected void fireToggleEvent() {
		ToggleEvent.fire(this, isPressed());
	}

	private void applyToogleStyle() {
		if (isPressed()) {
			panel.removeStyleName(style.toggleButtonReleased());
			panel.addStyleName(style.toggleButtonPressed());
		} else {
			panel.removeStyleName(style.toggleButtonPressed());
			panel.addStyleName(style.toggleButtonReleased());
		}
	}

	private void bindEvents() {
		// click events to change state
		addDomHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setState(!isPressed());
			}
		}, ClickEvent.getType());

		// events to change style
		addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				// panel.addStyleName(style.toggleButtonMouseOver());
			}
		});
		addMouseOutHandler(new MouseOutHandler() {

			public void onMouseOut(MouseOutEvent event) {
				// panel.removeStyleName(style.toggleButtonMouseOver());
			}
		});
		addMouseDownHandler(new MouseDownHandler() {

			public void onMouseDown(MouseDownEvent event) {
				panel.addStyleName(style.toggleButtonMouseDown());
			}
		});
		addMouseUpHandler(new MouseUpHandler() {

			public void onMouseUp(MouseUpEvent event) {
				panel.removeStyleName(style.toggleButtonMouseDown());
			}
		});

	}
}
