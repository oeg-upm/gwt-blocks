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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

/**
 * A page presenter is the presenter of complete application page. A page, in
 * contrast with controls, is always associated with a place. Most page are
 * composed of multiple ControlPresenters
 * 
 * @author Alexander De Leon
 */
public abstract class PagePresenter<E extends WidgetDisplay> extends WidgetPresenter<E> {

	private final Set<ControlPresenter<?>> children = new HashSet<ControlPresenter<?>>();

	public PagePresenter(E display, EventBus eventBus) {
		super(display, eventBus);
	}

	public void addControl(ControlPresenter<?> control) {
		children.add(control);
	}

	public void removeControl(ControlPresenter<?> control) {
		children.remove(control);
	}

	public Iterator<ControlPresenter<?>> children() {
		return children.iterator();
	}

	@Override
	public void bind() {
		// bind children first
		for (ControlPresenter<?> control : children) {
			control.bind();
		}
		super.bind();
	}

	@Override
	public void unbind() {
		// unbind children first
		for (ControlPresenter<?> control : children) {
			control.unbind();
		}
		super.unbind();
	}

	public final void refreshDisplay() {
		for (ControlPresenter<?> control : children) {
			control.refreshDisplay();
		}
		onRefreshDisplay();
	}

	public final void revealDisplay() {
		for (ControlPresenter<?> control : children) {
			control.revealDisplay();
		}
		onRevealDisplay();
	}

	protected abstract void onRefreshDisplay();

	protected abstract void onRevealDisplay();

}
