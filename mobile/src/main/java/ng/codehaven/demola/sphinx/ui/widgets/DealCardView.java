package ng.codehaven.demola.sphinx.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ng.codehaven.demola.sphinx.R;

public class DealCardView extends CardView implements OnClickListener {

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mSubtitle;
    private Button mMoreButton;
    private String mTag;
    private View mRoot;
    private OnDealCardButtonClicked mListener = null;

    public interface OnDealCardButtonClicked {
        void onDealCardButtonClicked(String tag);
    }

    public void setListener(OnDealCardButtonClicked listener) {
        mListener = listener;
    }

    public DealCardView(Context context) {
        super(context, null, 0);
        initialize(context, null, 0);
    }

    public DealCardView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initialize(context, attrs, 0);
    }

    public DealCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs, defStyle);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyle){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mRoot = inflater.inflate(R.layout.deal_card, this, true);

        mTitle = (TextView) mRoot.findViewById(R.id.tv_title);
        mSubtitle = (TextView) mRoot.findViewById(R.id.tv_subtitle);
        mImageView = (ImageView) mRoot.findViewById(R.id.iv_promo_img);
        mMoreButton = (Button) mRoot.findViewById(R.id.btn_action);

        mMoreButton.setOnClickListener(this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DealCard, defStyle, 0);
        String title = a.getString(R.styleable.DealCard_dealTitle);
        setTitle(title);
        String subtitle = a.getString(R.styleable.DealCard_dealSubtitle);
        setSubtitle(subtitle);

        String buttonTag = a.getString(R.styleable.DealCard_buttonTag);
        String buttonText = a.getString(R.styleable.DealCard_buttonText);

        setButton(buttonTag, buttonText);

        boolean hasImage = a.getBoolean(R.styleable.DealCard_hasImage, false);

        if (hasImage){
            int imageRes = a.getInt(R.styleable.DealCard_dealImage, R.drawable.ic_placeholder);
            setImage(imageRes);
        }

        setRadius(getResources().getDimensionPixelSize(R.dimen.card_corner_radius));
        setCardElevation(getResources().getDimensionPixelSize(R.dimen.card_elevation));
        setPreventCornerOverlap(false);

        a.recycle();

    }

    public void setButton(String buttonTag, String buttonText) {
        mMoreButton.setText(buttonText);
        mTag = buttonTag;
    }

    public void setImage(int imageRes) {
        mImageView.setImageDrawable(getResources().getDrawable(imageRes));
    }

    public ImageView getmImageView(){
        return mImageView;
    }

    public void setSubtitle(String subtitle) {
        if (!TextUtils.isEmpty(subtitle)) {
            mSubtitle.setText(subtitle);
        } else {
            mSubtitle.setVisibility(GONE);
        }
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)){
            mTitle.setVisibility(GONE);
        } else {
            mTitle.setVisibility(VISIBLE);
            mTitle.setText(title);
        }
    }

    @Override
    public void onClick(View v) {
        if (mListener == null){
            return;
        }

        mListener.onDealCardButtonClicked(mTag);
    }
}
