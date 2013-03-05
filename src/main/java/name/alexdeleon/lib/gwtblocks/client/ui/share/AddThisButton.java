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
package name.alexdeleon.lib.gwtblocks.client.ui.share;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class AddThisButton extends Widget {

	private static final String CLASS = "addthis_button";
	private static final String HREF = "http://www.addthis.com/bookmark.php?v=250&amp;pub=xa-4aef68af72e33445";
	private static final String SRC = "http://s7.addthis.com/static/btn/v2/lg-share-{LANG}.gif";
	private static final String DEFAULT_WIDTH = "125";
	private static final String DEFAULT_HEIGHT = "16";

	private final Element anchor;
	private final Element image;

	public AddThisButton() {
		this("en");
	}

	/**
	 * Localized constructor
	 * 
	 * @param language
	 *            the two letter code of the language
	 */
	public AddThisButton(String language) {
		anchor = DOM.createAnchor();
		DOM.setElementAttribute(anchor, "href", HREF);

		image = DOM.createImg();
		DOM.setElementAttribute(image, "src", SRC.replace("{LANG}", language));
		DOM.setStyleAttribute(image, "border", "0");

		anchor.appendChild(image);

		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);

		setElement(anchor);

		addStyleName(CLASS);
		addEvents();
	}

	private void addEvents() {
		// anchor
		// .setAttribute("onmouseover",
		// "return addthis_open(this, '' , this['addthis:url'], this['addthis:title']);");
		// anchor.setAttribute("onmouseout", "return addthis_close();");
		// anchor.setAttribute("onclick", "return addthis_sendto();");
		addDomHandler(new MouseOverHandler() {

			public void onMouseOver(MouseOverEvent event) {
				addThisOpen(anchor);
			}
		}, MouseOverEvent.getType());
		addDomHandler(new MouseOutHandler() {

			public void onMouseOut(MouseOutEvent event) {
				addThisClose();
			}
		}, MouseOutEvent.getType());
		addDomHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				addThisSendTo();
				event.preventDefault();
			}
		}, ClickEvent.getType());
	}

	@Override
	public void setWidth(String width) {
		DOM.setElementProperty(image, "width", width);
	}

	@Override
	public void setHeight(String height) {
		DOM.setElementProperty(image, "height", height);
	}

	public void setShareUrl(String url) {
		DOM.setElementProperty(anchor, "addthis:url", url);
	}

	@Override
	public void setTitle(String title) {
		DOM.setElementProperty(anchor, "addthis:title", title);
	}

	public void setDescription(String description) {
		DOM.setElementProperty(anchor, "addthis:description", description);
	}

	public void setImageAlt(String alt) {
		DOM.setElementProperty(image, "alt", alt);
	}

	private static final native void addThisOpen(Element e)/*-{ $wnd.addthis_open(e, '' , e['addthis:url'], e['addthis:title']); }-*/;

	private static final native void addThisClose()/*-{ $wnd.addthis_close(); }-*/;

	private static final native void addThisSendTo()/*-{ $wnd.addthis_sendto(); }-*/;
}
