package com.github.ddekanski.spiceannotations.controller;

import android.app.Application;

import com.github.ddekanski.spiceannotations.model.FacebookPage;
import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson2.Jackson2ObjectPersister;

public class MySpiceService extends SpiceService {

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager manager = new CacheManager();
        Jackson2ObjectPersister<FacebookPage> persister = new Jackson2ObjectPersister<FacebookPage>(getApplication(), FacebookPage.class);
        manager.addPersister(persister);
        return manager;
    }

}
