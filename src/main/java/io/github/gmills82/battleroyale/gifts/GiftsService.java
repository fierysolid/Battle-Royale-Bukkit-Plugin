package io.github.gmills82.battleroyale.gifts;

import io.github.gmills82.battleroyale.gifts.impl.Angel;
import io.github.gmills82.battleroyale.gifts.impl.AquaMan;
import io.github.gmills82.battleroyale.gifts.impl.Berserker;
import io.github.gmills82.battleroyale.gifts.impl.BlinkingSpirit;
import io.github.gmills82.battleroyale.gifts.impl.Demolitionist;
import io.github.gmills82.battleroyale.gifts.impl.Devil;
import io.github.gmills82.battleroyale.gifts.impl.Ender;
import io.github.gmills82.battleroyale.gifts.impl.Glutton;
import io.github.gmills82.battleroyale.gifts.impl.MacGyver;
import io.github.gmills82.battleroyale.gifts.impl.Mole;
import io.github.gmills82.battleroyale.gifts.impl.Necromancer;
import io.github.gmills82.battleroyale.gifts.impl.SeaWalker;
import io.github.gmills82.battleroyale.gifts.impl.Stalker;
import io.github.gmills82.battleroyale.gifts.impl.Übermensch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Grant Mills
 * @since 5/23/16
 */
public class GiftsService {
	private static GiftsService instance = null;
	private static final List<PlayerGift> gifts = new ArrayList<PlayerGift>();

	protected GiftsService() {
		//Populate gifts with 1 of each implementation
		gifts.add(new Angel());
		gifts.add(new AquaMan());
		gifts.add(new Berserker());
		gifts.add(new BlinkingSpirit());
		gifts.add(new Demolitionist());
		gifts.add(new Devil());
		gifts.add(new Ender());
		gifts.add(new Glutton());
		gifts.add(new MacGyver());
		gifts.add(new Mole());
		gifts.add(new Necromancer());
		gifts.add(new SeaWalker());
		gifts.add(new Stalker());
		gifts.add(new Übermensch());
	}

	public static GiftsService getInstance() {
		if(null == instance) {
			instance = new GiftsService();
		}
		return instance;
	}

	public PlayerGift getRandomPlayerGift() {
		return gifts.get(ThreadLocalRandom.current().nextInt(gifts.size()));
	}
}
