package com.github.kba.logback.xterm256;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class Highlighting256CompositeConverter extends Xterm256CompositeConverter<ILoggingEvent> {

	public static final String HIGHLIGHT_TRIPLE = "15;1;19";

	public final static List<String> DEFAULT_256_COLORS = Arrays.asList(
			parseColorTriple("196;1"), // ERROR
			parseColorTriple("3"), // WARN
			parseColorTriple("2"), // INFO
			parseColorTriple("15"), // DEBU
			parseColorTriple("254") // TRACE
			);
	private final static Map<Integer, Integer> levelIntOrder = new HashMap<>();
	static
	{
		levelIntOrder.put(Level.ERROR_INT, 0);
		levelIntOrder.put(Level.WARN_INT, 1);
		levelIntOrder.put(Level.INFO_INT, 2);
		levelIntOrder.put(Level.DEBUG_INT, 3);
		levelIntOrder.put(Level.TRACE_INT, 4);
	}

	@Override
	String getColorTriple(ILoggingEvent event)
	{
		Marker marker = event.getMarker();
		if (marker != null && marker.equals(Xterm256Constants.HIGHLIGHT))
		{
			return HIGHLIGHT_TRIPLE;
		}
		Level level = event.getLevel();
		List<String> colors = parseOptions();
		return colors.get(levelIntOrder.get(level.toInt()));
	}

	private List<String> parseOptions()
	{
		List<String> ret = new ArrayList<>(DEFAULT_256_COLORS);
		List<String> optionList2 = getOptionList();
		for (int i = 0; i < optionList2.size(); i++)
		{
			ret.set(i, optionList2.get(i));
		}
		return ret;
	}

}