package ng.codehaven.demola.sphinx.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ng.codehaven.demola.sphinx.R;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_HIGHLIGHT = 1;
    public static final int TYPE_TERMS = 2;
    public static final int TYPE_DETAILS = 3;
    public static final int TYPE_MAP = 4;


    private static final int MIN_ITEMS_COUNT = 5;

    private JSONObject jo;
    private Context mContext;

    public DetailAdapter(Context context, JSONObject jo) {
        this.jo = jo;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1) {
            return TYPE_HIGHLIGHT;
        } else if (position == 2) {
            return TYPE_TERMS;
        } else if (position == 3) {
            return TYPE_DETAILS;
        } else {
            return TYPE_MAP;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (TYPE_HEADER == viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item_header_layout, viewGroup, false);

            return new ViewHolder(view);
        } else if (TYPE_HIGHLIGHT == viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item_highlight_layout, viewGroup, false);
            return new HighlightViewHolder(view);
        } else if (TYPE_TERMS == viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item_terms_layout, viewGroup, false);
            return new TermsViewHolder(view);
        } else if (TYPE_DETAILS == viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item_details_layout, viewGroup, false);
            return new DetailsViewHolder(view);
        } else if (TYPE_MAP == viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_map_layout, viewGroup, false);
            return new MapViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder itemViewHolder, int position) {

        //Here you can fill your row view
    }

    @Override
    public int getItemCount() {
        return MIN_ITEMS_COUNT;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }

    protected class HighlightViewHolder extends ViewHolder {
        public HighlightViewHolder(View view) {
            super(view);
        }
    }

    protected class TermsViewHolder extends ViewHolder {
        public TermsViewHolder(View view) {
            super(view);
        }
    }

    protected class DetailsViewHolder extends ViewHolder {
        public DetailsViewHolder(View view) {
            super(view);
        }
    }

    protected class MapViewHolder extends ViewHolder implements OnMapReadyCallback {
        GoogleMap gMap;
        @InjectView(R.id.mapImageView)
        MapView map;

        public MapViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);

            if (map != null)
            {
                map.onCreate(null);
                map.onResume();
                map.getMapAsync(this);
            }

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            //initialize the Google Maps Android API if features need to be used before obtaining a map
            MapsInitializer.initialize(mContext);
            gMap = googleMap;

            //you can move map here to item specific 'location'
            int pos = getAdapterPosition();

            LatLng latLng = new LatLng(6.42788, 3.4274513);
            LatLng NIGERIA = new LatLng(9.0611017, 4.1763482);

            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            gMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .draggable(false)
                    .flat(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }
}
