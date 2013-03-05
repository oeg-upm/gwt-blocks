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
package name.alexdeleon.lib.gwtblocks.client.widget.prettypopup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class PrettyPopup extends PopupPanel {

	public interface Stylesheet {
		String containerBox();

		String shadeBox();

		String contentBox();

		String closeButton();

		String shadeWidth();

	}

	public interface CloseListener {
		boolean onClose(PrettyPopup popup);
	}

	private final FlowPanel containerBox = new FlowPanel();
	private final FlowPanel shadeBox = new FlowPanel();
	private final FlowPanel contentBox = new FlowPanel();
	private final CloseButton closeButton = new CloseButton();
	private final Stylesheet style;

	public PrettyPopup(Stylesheet style) {
		super();
		this.style = style;
		setWidget(createUi());
		bindEvents();
	}

	public PrettyPopup(Stylesheet style, boolean modal) {
		super(false, modal);
		this.style = style;
		setWidget(createUi());
		bindEvents();
	}

	public FlowPanel getContentPanel() {
		return contentBox;
	}

	private Widget createUi() {
		setStyleName(""); // clear GWT style

		containerBox.setStyleName(style.containerBox());
		shadeBox.setStyleName(style.shadeBox());
		contentBox.setStyleName(style.contentBox());
		closeButton.setStyleName(style.closeButton());

		containerBox.add(shadeBox);
		containerBox.add(closeButton);
		shadeBox.add(contentBox);

		DOM.setStyleAttribute(shadeBox.getElement(), "padding", style.shadeWidth());

		return containerBox;
	}

	private void bindEvents() {
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PrettyPopup.this.hide();

			}
		});
	}

	private static class CloseButton extends FlowPanel implements HasClickHandlers {

		public HandlerRegistration addClickHandler(ClickHandler handler) {
			return addDomHandler(handler, ClickEvent.getType());
		}

	}
}
