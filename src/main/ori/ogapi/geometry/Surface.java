package ori.ogapi.geometry;

import ori.ogapi.util.Iterator;

public interface Surface<S extends Shape> extends Iterable<S> {

	public boolean add(S s);
	public boolean remove(S s);
	public boolean contains(S s);

	public Surface<S> getIn(Shape s);
	public Surface<S> getPartlyIn(Shape s);
	public Surface<S> getAt(Point p);

	@Override public Iterator<S> iterator();

};

