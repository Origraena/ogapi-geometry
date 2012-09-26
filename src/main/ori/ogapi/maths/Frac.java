package ori.ogapi.maths;

public class Frac implements Comparable<Frac> {

	public Frac() {
		_p = 0;
		_q = 1;
	}

	public Frac(int p) {
		_p = p;
		_q = 1;
	}

	public Frac(int p, int q) {
		set(p,q);
	}

	@Override
	public Frac clone() {
		return new Frac(_p,_q);
	}

	public void set(int p, int q) {
		if (q == 0)
			q = 1;
		_p = p;
		_q = q;
		simplify();
	}

	public void simplify() {
		if (_q < 0) {
			_p = 0 - _p;
			_q = 0 - _q;
		}
		// TODO
		int pgcd = Math.pgcd(_p,_q);
		_p /= pgcd;
		_q /= pgcd;
	}

	public int p() {
		return _p;
	}

	public int q() {
		return _q;
	}

	public Frac opposite() {
		return new Frac(0-_p,_q);
	}

	public Frac inverse() {
		if (_p == 0)
			return null;
		return new Frac(_q,_p);
	}

	public void plus(Frac f) {
		int p,q;
		p = _p;
		q = _q;
		p *= f.q();
		q *= f.q();
		p += f.p() * _q;
		this.set(p,q);
	}

	public void minus(Frac f) {
		this.plus(f.opposite());
	}

	public void plus(int x) {
		this.set(_p+(x*_q),_q);
	}

	public void minus(int x) {
		this.plus(0-x);
	}

	public void times(Frac f) {
		this.set(_p*f.p(),_q*f.q());
	}

	public void times(int x) {
		this.set(_p*x,_q);
	}

	@Override
	public int compareTo(Frac f) {
		double f1,f2;
		f1 = ((double)_p)/((double)_q);
		f2 = ((double)f.p())/((double)f.q());
		return (int)(f1 - f2);
	}

	public int compareTo(int x) {
		return this.compareTo(new Frac(x,1));
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof Frac) {
			Frac f = (Frac)o;
			return ((f.p() == this.p()) && (f.q() == this.q()));
		}
		if (o instanceof Integer)
			return this.equals(new Frac(((Integer)o).intValue(),1));
		return false;
	}

	public boolean equals(int x) {
		return this.equals(new Frac(x,1));
	}

	public String toString() {
		return ""+_p+"/"+_q;
	}

	private int _p, _q;

};

