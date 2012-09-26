package ori.ogapi.maths;

public class Math {

	public static int abs(int a) {
		return (a < 0) ? 0 - a : a;
	}

	public static long abs(long a) {
		return (a < 0) ? 0 - a : a;
	}

	public static int pgcd(int a, int b) {
		int pgcd = 1;
		int p = abs(a);
		int q = abs(b);
		while (p*q != 0) {
			if (p > q)
				p -= q;
			else
				q -= p;
		}
		return (p == 0) ? q : p;
	}

	public static double sqrt(int n) {
		return java.lang.Math.sqrt(n);
	}

	public static int round(double n) {
		return (int)java.lang.Math.round(n);
	}
	
	public static Frac fracSqrt(Frac frac) {
/*		int p,q;
		p = 1;
		while (frac.compareTo(p*p) < 0)
			p++;
		if (frac.compareTo(p*p))
			return new Frac(p,1);
	 	p--;
*/
	// TODO
		return new Frac(Math.round(Math.sqrt(frac.p())),Math.round(Math.sqrt(frac.q())));
	}

	public static Frac[] computeRoots(Frac alpha, Frac beta, Frac gamma) {
		// TODO
		Frac tmp;
		Frac delta = beta.clone();
		delta.times(beta);
		tmp = new Frac(4,1);
		tmp.times(alpha);
		tmp.times(gamma);
		delta.minus(tmp);

		if (delta.compareTo(0) < 0)
			return null;

		Frac res[];
		Frac deltasqrt = fracSqrt(delta);

		if (delta.equals(0)) {
			res = new Frac[1];
			res[0] = beta.clone();
			tmp = alpha.clone();
			tmp.times(2);
			tmp.inverse();
			res[0].times(tmp);
		}

		else {
			res = new Frac[2];
			res[0] = beta.clone();
			res[1] = beta.clone();
			res[0].plus(deltasqrt);
			res[1].minus(deltasqrt);
			tmp = alpha.clone();
			tmp.times(2);
			tmp.inverse();
			res[0].times(tmp);
			res[1].times(tmp);
		}

		return res;
	}

	private Math() { }

};

