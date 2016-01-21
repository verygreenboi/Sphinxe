package ng.codehaven.demola.sphinx.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import net.glassstones.library.utils.NetworkHelper;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ng.codehaven.demola.sphinx.R;
import ng.codehaven.demola.sphinx.models.LocalData;
import ng.codehaven.demola.sphinx.ui.adapters.DetailAdapter;

public class DetailActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.header)
    ImageView mHeader;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsing;
    int mutedColor = R.attr.colorPrimary;
    @InjectView(R.id.scrollableview)
    RecyclerView mRecyclerView;
    @InjectView(R.id.fab_buy)
    FloatingActionButton mFab;
    private DetailAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mHeader.setImageBitmap(bitmap);
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {
                    mutedColor = palette.getMutedColor(R.color.color_primary_blue);
                    mCollapsing.setContentScrimColor(mutedColor);
                    mCollapsing.setStatusBarScrimColor(R.color.black_trans80);
                }
            });
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        initializeToolbar();

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LocalData getData = new LocalData(getIntent()).invoke();
        String url = getData.getUrl();
        String title = getData.getTitle();
        JSONObject jo = getData.getJo();

        if (url == null || title == null || jo == null) {
            finish();
        }

        if (NetworkHelper.isOnline(this))
            Picasso.with(this).load(url).into(target);

        mCollapsing.setTitle(title);

        mRecyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        mAdapter = new DetailAdapter(this, jo);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        mFab.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null && item.getItemId() == R.id.action_buy) {
            doBuy();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == mFab.getId()) {
            doBuy();
        }
    }

    @Override
    public int getToolBarId() {
        return R.id.anim_toolbar;
    }

    @Override
    public boolean hasMenu() {
        return true;
    }

    @Override
    public int getMenuId() {
        return R.menu.menu_details;
    }

    private void doBuy() {

    }
}
