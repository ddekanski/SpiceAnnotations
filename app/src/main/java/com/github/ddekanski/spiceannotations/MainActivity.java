package com.github.ddekanski.spiceannotations;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ddekanski.spiceannotations.controller.FacebookPageRequest;
import com.github.ddekanski.spiceannotations.controller.MySpiceService;
import com.github.ddekanski.spiceannotations.model.FacebookPage;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.springframework.util.StringUtils;

@EActivity(R.layout.activity_main)
@WindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS)
public class MainActivity extends Activity implements RequestListener<FacebookPage> {

    @ViewById
    EditText pageNameInput;

    @ViewById
    TableLayout detailsSection;

    @ViewById
    TextView name;

    @ViewById
    TextView category;

    @ViewById
    TextView about;

    @ViewById
    TextView website;

    @Bean
    FacebookPageRequest facebookPageRequest;

    private SpiceManager spiceManager = new SpiceManager(MySpiceService.class);

    @Override
    public void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    @Click
    void getPageDetails() {
        CharSequence pageName = pageNameInput.getText();
        if (!StringUtils.hasText(pageName)) {
            showMsg("Please specify a Facebook page.");
            return;
        }

        if (isPageInCache(pageName)) {
            showMsg("The page is already cached.");
        }

        detailsSection.setVisibility(View.GONE);
        setProgressBarIndeterminateVisibility(true);
        facebookPageRequest.setPageName(pageName);
        spiceManager.execute(facebookPageRequest, pageName, DurationInMillis.ALWAYS_RETURNED, this);
    }

    private boolean isPageInCache(CharSequence pageName) {
        try {
            return spiceManager.isDataInCache(FacebookPage.class, pageName, DurationInMillis.ALWAYS_RETURNED).get();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onRequestSuccess(FacebookPage facebookPage) {
        setProgressBarIndeterminateVisibility(false);
        detailsSection.setVisibility(View.VISIBLE);
        name.setText(facebookPage.getName());
        category.setText(facebookPage.getCategory());
        about.setText(facebookPage.getAbout());
        website.setText(facebookPage.getWebsite());
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setProgressBarIndeterminateVisibility(false);
        final String msg = spiceException.getCause().getMessage();
        showMsg("Error: " + msg);
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
