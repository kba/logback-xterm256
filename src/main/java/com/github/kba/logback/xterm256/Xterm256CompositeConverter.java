package com.github.kba.logback.xterm256;

import static com.github.kba.logback.xterm256.Xterm256Constants.*;
import ch.qos.logback.core.pattern.CompositeConverter;

public abstract class Xterm256CompositeConverter<E> extends CompositeConverter<E>
{
	final private static String SET_DEFAULT_COLOR = ESC_START + "0;" + DEFAULT_FG + ESC_END;

	protected static String parseColorTriple(String opt)
	{
		StringBuilder sb = new StringBuilder();
		String[] fgboldbg = opt.split(";");

		// bg color if set
		if (fgboldbg.length > 2)
		{
			sb.append(START_256_BG);
			sb.append(fgboldbg[2]);
			sb.append(ESC_END);
			sb.append(ESC_START);
		}

		// bold if set
		if (fgboldbg.length > 1 && fgboldbg[1].equals("1"))
		{
			sb.insert(0, BOLD);
		}

		// fg color
		sb.append(START_256_FG);
		sb.append(fgboldbg[0]);

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
		sb.append(SET_DEFAULT_COLOR);
		return sb.toString();
	}

}
