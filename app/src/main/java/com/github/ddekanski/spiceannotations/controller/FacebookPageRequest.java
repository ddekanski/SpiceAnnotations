package com.github.ddekanski.spiceannotations.controller;

import com.github.ddekanski.spiceannotations.model.FacebookPage;
import com.octo.android.robospice.request.SpiceRequest;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

@EBean
public class FacebookPageRequest extends SpiceRequest<FacebookPage> {

    @RestService
    FacebookPageRestClient restClient;

    private CharSequence pageName = "";

    public FacebookPageRequest() {
        super(FacebookPage.class);
    }

    public void setPageName(CharSequence pageName) {
        this.pageName = pageName;
    }

    @Override
    public FacebookPage loadDataFromNetwork() throws Exception {
        return restClient.getPage(pageName);
    }

}
