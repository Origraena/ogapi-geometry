package ori.ogapi.maths;

public class Fracs {

	public static Frac plus(Frac f, Frac f2) {
		Frac res = f.clone();
		res.plus(f2);
		return res;
	}

	public static Frac plus(Frac f, int x) {
		Frac res = f.clone();
		res.plus(x);
		return res;
	}

	public static Frac minus(Frac f, Frac f2) {
		Frac res = f.clone();
		res.minus(f2);
		return res;
	}

	public static Frac minus(Frac f, int x) {
		Frac res = f.clone();
		res.minus(x);
		return res;
	}

	public static Frac minus(int x, Frac f) {
		Frac res = new Frac(x);
		res.minus(f);
		return res;
	}

	public static Frac times(Frac f, Frac f2) {
		Frac res = f.clone();
		res.times(f2);
		return res;
	}

	public static Frac times(Frac f, int x) {
		Frac res = f.clone();
		res.times(x);
		return res;
	}

	public static Frac times(int x, Frac f) {
		Frac res = new Frac(x);
		res.times(f);
		return res;
	}


	private Fracs() { }

};

