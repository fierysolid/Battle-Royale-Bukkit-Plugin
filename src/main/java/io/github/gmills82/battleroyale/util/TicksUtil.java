package io.github.gmills82.battleroyale.util;

/**
 * @author Grant Mills
 * @since 5/21/16
 */
public class TicksUtil {
	public static Integer convertMinsToTicks(Integer mins) {
		//20 ticks per second
		return mins * 60 * 20;
	}

	public static Integer convertMinsToTicks(double mins) {
		//20 ticks per second
		Double ticks = mins * 60 * 20;
		return ticks.intValue();
	}
}
