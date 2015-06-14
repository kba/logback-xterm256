package com.github.kba.logback.xterm256;

import static com.github.kba.logback.xterm256.Xterm256Constants.*;
import ch.qos.logback.core.pattern.CompositeConverter;

public abstract class Xterm256CompositeConverter<E> extends CompositeConverter<E>
{
	final private static String RESET_COLOR = ESC_START + "0;" + DEFAULT_FG + DEFAULT_BG + ESC_END ;

	protected static String parseColorTriple(String opt)
	{
		StringBuilder sb = new StringBuilder();
		String[] fgSgrBg = opt.split("-");

		// SGR if set
		if (fgSgrBg.length > 1)
		{
			sb.append(fgSgrBg[1]);
			sb.append(";");
		}

		// fg color
		sb.append(START_256_FG);
		sb.append(fgSgrBg[0]);

		// bg color if set
		if (fgSgrBg.length > 2)
		{
			sb.append(";");
			sb.append(START_256_BG);
			sb.append(fgSgrBg[2]);
		}

		return sb.toString();
	}

	abstract String getColorTriple(E event);

	@Override
	protected String transform(E event, String in)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(ESC_START);
		sb.append(parseColorTriple(getColorTriple(event)));
		sb.append(ESC_END);
		sb.append(in);
		sb.append(RESET_COLOR);
		return sb.toString();
	}

}
