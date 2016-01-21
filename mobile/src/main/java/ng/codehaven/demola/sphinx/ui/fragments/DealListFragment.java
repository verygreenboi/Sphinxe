package ng.codehaven.demola.sphinx.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ng.codehaven.demola.sphinx.Common;
import ng.codehaven.demola.sphinx.R;
import ng.codehaven.demola.sphinx.interfaces.MoreClickInterface;
import ng.codehaven.demola.sphinx.ui.DetailActivity;
import ng.codehaven.demola.sphinx.ui.adapters.DealAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DealListFragment extends Fragment implements MoreClickInterface {

    public final static String ITEMS_COUNT_KEY = "AllFragment$ItemsCount";
    public final static String ITEMS_CATEGORY_KEY = "AllFragment$ItemsCategory";

    List<JSONObject> deals;

    String cat;

    public DealListFragment() {
        // Required empty public constructor
    }

    public static DealListFragment createInstance(int itemsCount, String category) {
        DealListFragment partThreeFragment = new DealListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ITEMS_COUNT_KEY, itemsCount);
        bundle.putString(ITEMS_CATEGORY_KEY, category);
        partThreeFragment.setArguments(bundle);
        return partThreeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_all, container, false);
        if (getArguments() != null) {
            cat = getArguments().getString(ITEMS_CATEGORY_KEY);
        }
        try {
            setupRecyclerView(recyclerView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) throws JSONException {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DealAdapter recyclerAdapter = new DealAdapter(getActivity(), createItemList());
        recyclerAdapter.setMoreCickInterface(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<JSONObject> createItemList() throws JSONException {
        deals = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            String[] imgs = new String[0];
            switch (cat) {
                case "home":
                    imgs = Common.DEALS_HOME_URL;
                    break;
                case "fashion":
                    imgs = Common.DEALS_FASHION_URL;
                    break;
                case "technology":
                    imgs = Common.DEALS_GADGETS_URL;
                    break;
                case "ho":
                    imgs = Common.DEALS_HO_URL;
                    break;
            }

            loopCat(cat, imgs);

        }
        return deals;
    }

    private void loopAll() throws JSONException {
        List<String> url = new ArrayList<>();
        // Loop home
        Collections.addAll(url, Common.DEALS_HOME_URL);
        Collections.addAll(url, Common.DEALS_FASHION_URL);
        Collections.addAll(url, Common.DEALS_GADGETS_URL);
        Collections.addAll(url, Common.DEALS_HO_URL);

        for (int i = 0; i < url.size(); i++) {
            JSONObject jo = new JSONObject();

            jo.put(Common.DEAL_TITLE, "Title " + i);
            jo.put(Common.DEAL_SUBTITLE, "Subtitle");
            jo.put(Common.DEAL_IMG_URL, url.get(i));

            deals.add(jo);
        }
    }

    private void loopCat(String cat, String[] imgs) throws JSONException {
        if (!TextUtils.isEmpty(cat)) {
            for (int i = 0; i < imgs.length; i++) {
                JSONObject jo = new JSONObject();
                jo.put(Common.DEAL_TITLE, "Title " + i);
                jo.put(Common.DEAL_SUBTITLE, "Subtitle");
                jo.put(Common.DEAL_IMG_URL, imgs[i]);
                deals.add(jo);
            }
        } else {
            loopAll();
        }
    }


    @Override
    public void onMoreClicked(int position) {
        String deal = deals.get(position).toString();
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra(Common.DEAL_TO_STRING, deal);
        getActivity().startActivity(i);
    }
}
