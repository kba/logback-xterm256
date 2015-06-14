package com.github.kba.logback.xterm256;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class Xterm256HighlightingCompositeConverter extends Xterm256CompositeConverter<ILoggingEvent> {

	public static final String HIGHLIGHT_TRIPLE = "15-1-19";

	public final static List<String> LEVEL_TRIPLES = Arrays.asList(
			"15-1-124", // ERROR
			"11",       // WARN
			"10",       // INFO
			"-0-",         // DEBUG
			"248-3"     // TRACE
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
	private List<String> colorTriples;

	private List<String> parseOptions()
	{
		List<String> merged = new ArrayList<>(LEVEL_TRIPLES);
		if (null != getOptionList())
		{
			for (int i = 0; i < getOptionList().size(); i++)
			{
				merged.set(i, getOptionList().get(i));
			}
		}
		return merged;
	}

	@Override
	String getColorTriple(ILoggingEvent event)
	{
		if (event.getMarker() != null && event.getMarker().equals(Xterm256Constants.HIGHLIGHT))
		{
			return HIGHLIGHT_TRIPLE;
		}
		Level level = event.getLevel();
		return colorTriples.get(levelIntOrder.get(level.toInt()));
	}

	@Override
	public void start()
	{
		colorTriples = parseOptions();
	}

}