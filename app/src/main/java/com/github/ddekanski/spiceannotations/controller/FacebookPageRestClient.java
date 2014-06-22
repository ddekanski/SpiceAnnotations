package com.github.ddekanski.spiceannotations.controller;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.github.ddekanski.spiceannotations.model.FacebookPage;

@Rest(rootUrl = "https://graph.facebook.com", converters = { MappingJackson2HttpMessageConverter.class })
public interface FacebookPageRestClient {

    @Get("/{name}")
    FacebookPage getPage(CharSequence name);

}