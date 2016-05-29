package io.github.gmills82.battleroyale.catastrophy;

import io.github.gmills82.battleroyale.catastrophy.impl.BossSummon;
import io.github.gmills82.battleroyale.catastrophy.impl.Flood;
import io.github.gmills82.battleroyale.catastrophy.impl.LandDestruction;
import io.github.gmills82.battleroyale.catastrophy.impl.LightningStorm;
import io.github.gmills82.battleroyale.catastrophy.impl.NetherInvasion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Grant Mills
 * @since 5/29/16
 */
public class CatastrophyService {
	private static CatastrophyService instance = null;
	private static final List<Catastrophy> catastrophies = new ArrayList<Catastrophy>();

	protected CatastrophyService() {
		//Populate gifts with 1 of each implementation
		this.catastrophies.add(new BossSummon());
		this.catastrophies.add(new Flood());
		this.catastrophies.add(new LandDestruction());
		this.catastrophies.add(new LightningStorm());
		this.catastrophies.add(new NetherInvasion());
	}

	//Singleton
	public static CatastrophyService getInstance() {
		if(null == instance) {
			instance = new CatastrophyService();
		}
		return instance;
	}

	public Catastrophy getRandomCatastophy() {
		return catastrophies.get(ThreadLocalRandom.current().nextInt(catastrophies.size()));
	}
}
