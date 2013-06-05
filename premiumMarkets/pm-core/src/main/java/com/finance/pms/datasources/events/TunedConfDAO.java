package com.finance.pms.datasources.events;

import java.util.List;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfId;

public interface TunedConfDAO {

	public abstract void resetTunedConfsFor(final Stock stock);

	public abstract void resetTunedConfs();

	public abstract List<TunedConf> loadAllTunedConfs();

	public abstract void saveOrUpdateTunedConfs(TunedConf tunedConf);

	public abstract TunedConf loadTunedConf(TunedConfId tunedConfId);

}
