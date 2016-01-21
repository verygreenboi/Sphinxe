package ng.codehaven.demola.sphinx.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ng.codehaven.demola.sphinx.R;
import ng.codehaven.demola.sphinx.utils.LogHelper;


public abstract class ActionBarActivity extends AppCompatActivity {

    private static final String TAG = LogHelper.makeLogTag(ActionBarActivity.class);
    private Toolbar mToolbar;
    private boolean mToolbarInitialized;

    public abstract int getToolBarId();

    public abstract boolean hasMenu();

    public abstract int getMenuId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.d(TAG, "Activity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mToolbarInitialized) {
            throw new IllegalStateException("You must run super.initializeToolbar at " +
                    "the end of your onCreate method");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mToolbar.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        mToolbar.setTitle(titleId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(getMenuId(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // If not handled by drawerToggle, home needs to be handled by returning to previous
        if (item != null && item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item != null && item.getItemId() == R.id.action_settings){
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initializeToolbar() {
        mToolbar = (Toolbar) findViewById(getToolBarId());
        if (mToolbar == null) {
            throw new IllegalStateException("Layout is required to include a Toolbar with id " +
                    "'toolbar'");
        }

        if (hasMenu())
            mToolbar.inflateMenu(getMenuId());

        setSupportActionBar(mToolbar);
        mToolbarInitialized = true;
    }
}
