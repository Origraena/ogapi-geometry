package ori.ogapi.geometry;

import ori.ogapi.util.Iterator;
import ori.ogapi.util.AdaptedIterator;

import java.util.LinkedList;
import java.util.List;

public class LinkedListSurface<S extends Shape> extends AbstractSurface<S> {

	public LinkedListSurface() {
		super();
		_list = new LinkedList<S>();
	}

	@Override 
	public boolean add(S s) {
		if (_list.contains(s))
			return false;
		_list.add(s);
		return true;
	}

	@Override 
	public boolean remove(S s) {
		return _list.remove(s);
	}

	@Override 
	public Iterator<S> iterator() {
		return new AdaptedIterator<S>(_list.iterator());
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(super.toString());
		s.append('\n');
		for (S shape : this) {
			s.append('\t');
			s.append(shape);
			s.append('\n');
		}
		return s.toString();
	}

	private List<S> _list;

};

