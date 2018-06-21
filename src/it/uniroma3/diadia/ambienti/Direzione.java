package it.uniroma3.diadia.ambienti;

public enum Direzione {
	NORD {
		@Override
		Direzione opposta() {
			return SUD;
		}
	}, SUD {
		@Override
		Direzione opposta() {
			return SUD;
		}
	}, EST {
		@Override
		Direzione opposta() {
			return OVEST;
		}
	}, OVEST {
		@Override
		Direzione opposta() {
			return EST;
		}
	};
	abstract Direzione opposta();
	
	public static Direzione convertString(String dir) {
		String dirUpperCase = dir.toUpperCase();
		switch(dirUpperCase) {
			case "NORD": return Direzione.NORD;
			case "SUD": return Direzione.SUD;
			case "OVEST": return Direzione.OVEST;
			case "EST": return Direzione.EST;
		};
		return null;
	}
}
