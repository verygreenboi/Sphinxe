package ng.codehaven.demola.sphinx.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import net.glassstones.library.utils.NetworkHelper;
import net.glassstones.library.utils.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ng.codehaven.demola.sphinx.Common;
import ng.codehaven.demola.sphinx.R;
import ng.codehaven.demola.sphinx.interfaces.MoreClickInterface;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class DealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private final int rowSize;
    private List<JSONObject> mDeals;
    private Context mContext;
    private MoreClickInterface mListener;

    public DealAdapter(Context context, List<JSONObject> deals) {
        mContext = context;
        mDeals = deals;
        this.rowSize = UIUtils.getScreenHeight(mContext) / 3;
    }

    public void setMoreCickInterface(MoreClickInterface moreCickInterface) {
        this.mListener = moreCickInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.new_card_layout, viewGroup, false);

//        View view = LayoutInflater.from(mContext).inflate(R.layout.deal_card_layout, viewGroup, false);

        DealViewHolder vh = new DealViewHolder(view);
        vh.getmRoot().setOnClickListener(this);
        vh.getmPromoImg().setOnClickListener(this);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        bindHolder((DealViewHolder) vh, position);
    }

    private void bindHolder(DealViewHolder vh, int position) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = rowSize;

        vh.itemView.setLayoutParams(params);

        vh.getmRoot().setPreventCornerOverlap(false);
        vh.mPromoImg.setCornerRadiiDP(4f, 4f, 4f, 4f);

        //Here you can fill your row view
        String url = null;
        String title = null;
        try {
            url = mDeals.get(position).getString(Common.DEAL_IMG_URL);
            title = mDeals.get(position).getString(Common.DEAL_TITLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (NetworkHelper.isOnline(mContext)) {
            Picasso.with(mContext).load(url).placeholder(R.drawable.ic_placeholder).into(vh.getmPromoImg());
        }
        vh.getmTitle().setText(title);
        vh.getmPromoImg().setTag(position);
        vh.getmRoot().setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDeals.size();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        int position = (int) v.getTag();
        switch (id) {
            case R.id.iv_promo_img:
                // Do fancy animation before passing position to fragment
                revealAnim(position);
                mListener.onMoreClicked(position);
                break;
            case R.id.btn_action:
                btnRevealAnim(position);
                mListener.onMoreClicked(position);
        }
    }

    private void btnRevealAnim(int position) {

    }

    private void revealAnim(int position) {

    }

    static class DealViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_promo_img)
        SelectableRoundedImageView mPromoImg;
        @InjectView(R.id.tv_title)
        TextView mTitle;
        @InjectView(R.id.tv_subtitle)
        TextView mSubtitle;
        @InjectView(R.id.root)
        CardView mRoot;

        public DealViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public ImageView getmPromoImg() {
            return mPromoImg;
        }

        public TextView getmTitle() {
            return mTitle;
        }

        public TextView getmSubtitle() {
            return mSubtitle;
        }

        public CardView getmRoot() {
            return mRoot;
        }
    }
}
