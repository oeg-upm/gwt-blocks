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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;
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

	private final Widget anchor;
	private final Widget image;

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
		anchor = new Anchor();
		
		anchor.getElement().setAttribute("href", HREF);

		image = new Image();
		image.getElement().setAttribute("src", SRC.replace("{LANG}", language));
		image.getElement().getStyle().setProperty("border", "0");


		anchor.getElement().appendChild(image.getElement());

		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);
		
		this.getElement().appendChild(anchor.getElement());

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
				addThisOpen(anchor.getElement());
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
		image.getElement().setAttribute("width", width);
	}

	@Override
	public void setHeight(String height) {
		image.getElement().setAttribute("height", height);
	}

	public void setShareUrl(String url) {
		image.getElement().setAttribute("addthis:url", url);
	}

	@Override
	public void setTitle(String title) {
		image.getElement().setAttribute("addthis:title", title);
	}

	public void setDescription(String description) {
		image.getElement().setAttribute("addthis:description", description);
	}

	public void setImageAlt(String alt) {
		image.getElement().setAttribute("alt", alt);
	}

	private static final native void addThisOpen(JavaScriptObject e)/*-{ $wnd.addthis_open(e, '' , e['addthis:url'], e['addthis:title']); }-*/;

	private static final native void addThisClose()/*-{ $wnd.addthis_close(); }-*/;

	private static final native void addThisSendTo()/*-{ $wnd.addthis_sendto(); }-*/;
}
