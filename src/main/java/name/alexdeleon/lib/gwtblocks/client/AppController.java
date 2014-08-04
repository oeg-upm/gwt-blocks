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
package name.alexdeleon.lib.gwtblocks.client;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.place.PlaceRequestEvent;
import net.customware.gwt.presenter.client.place.PlaceRequestHandler;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class AppController extends WidgetPresenter<AppController.Display>
		implements PlaceRequestHandler {

	public interface Display extends WidgetDisplay {
		void setContent(Widget widget);
	}

	//private final Set<WidgetPresenter<?>> presenters;
	private Map<String,WidgetPresenter<?>> presenters;
	private Map<String,Place> places;

	public AppController(Display display, EventBus eventBus) {
		super(display, eventBus);
		presenters = new HashMap<String,WidgetPresenter<?>>();
		eventBus.addHandler(PlaceRequestEvent.getType(), this);
	}


	public void addPresenter(WidgetPresenter<?> presenter,Place place) {
		this.presenters.put(place.getName(), presenter);
		places.put(place.getName(), place);
	}

	public void removePresenter(WidgetPresenter<?> presenter) {
		presenters.remove(presenter);
	}
	
	@Override
	public void onPlaceRequest(PlaceRequestEvent event) {
		String place = event.getRequest().getName();
		if (place == null) {
			return;
		}
		for (String namePlace:this.presenters.keySet()) {
			if (place.equals(namePlace)) {
				WidgetPresenter<?> presenter=this.presenters.get(namePlace);
				getDisplay().setContent(presenter.getDisplay().asWidget());
				break;
			}
		}
	}

	/* ----- Presenter API -- */
	public Place getPlace() {
		// This is the default place
		return null;
	}

	@Override
	protected void onBind() {
		// bind children
		for (WidgetPresenter<?> presenter : presenters.values()) {
			presenter.bind();
		}
	}

	protected void onPlaceRequest(PlaceRequest request) {
		// empty
	}

	@Override
	protected void onUnbind() {
		// unbind children
		for (WidgetPresenter<?> presenter : presenters.values()) {
			presenter.unbind();
		}
	}

	public void refreshDisplay() {
		// empty
	}

	public void revealDisplay() {
		// empty
	}

	@Override
	protected void onRevealDisplay() {
		// TODO Auto-generated method stub
		
	}

}
