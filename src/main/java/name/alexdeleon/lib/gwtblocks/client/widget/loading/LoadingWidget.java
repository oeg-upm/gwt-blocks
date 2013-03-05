/**
 * Copyright (c) 2011 Ontology Engineering Group, 
 * Departamento de Inteligencia Artificial,
 * Facultad de Informática, Universidad 
 * Politécnica de Madrid, Spain
 * 
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
package name.alexdeleon.lib.gwtblocks.client.widget.loading;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Alexander De Leon
 */
@Singleton
public class LoadingWidget extends PopupPanel {

	public interface Stylesheet {
		String loadingWidgetStyle();

		String loadingWidgetLabelStyle();

		String loadingWidgetIconStyle();
	}

	private final String message;
	private final ImageResource loadingIcon;
	private final Stylesheet style;
	private final Set<Object> waitingQueue;

	@Inject
	public LoadingWidget(ImageResource loadingIcon, String message, Stylesheet style) {
		this.loadingIcon = loadingIcon;
		this.message = message;
		this.style = style;
		waitingQueue = new HashSet<Object>();
		setWidget(createUi());
		addStyleName(style.loadingWidgetStyle());
	}

	public void waitOn(Object worker) {
		waitingQueue.add(worker);
		center();
	}

	public void release(Object worker) {
		waitingQueue.remove(worker);
		if (waitingQueue.isEmpty()) {
			hide();
		}
	}

	private Widget createUi() {
		Label label = new Label(message);
		label.setStyleName(style.loadingWidgetLabelStyle());
		FlowPanel panel = new FlowPanel();
		Image icon = new Image(loadingIcon);
		icon.setStyleName(style.loadingWidgetIconStyle());
		DOM.setStyleAttribute(label.getElement(), "float", "right");
		panel.add(icon);
		panel.add(label);

		return panel;
	}

}
