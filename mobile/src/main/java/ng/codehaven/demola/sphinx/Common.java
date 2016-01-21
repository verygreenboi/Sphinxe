package ng.codehaven.demola.sphinx;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

import cat.ppicas.customtypeface.CustomTypeface;

/**
 * Created by Thompson on 11/11/2015.
 */
public class Common extends Application {
    public static final String SHARED_PREF = "my_prefs";
    public static final String FIRST_RUN_KEY = "first_key";
    public static final String CURRENT_FRAGMENT = "current_fragment";
    public static final String DEAL_TITLE = "deal_title";
    public static final String DEAL_SUBTITLE = "deal_subtitle";
    public static final String DEAL_IMG_URL = "deal_img_url";
    public static final String DEAL_TO_STRING = "deal";
    public static final String[] DEALS_HOME_URL = {
            "https://farm8.staticflickr.com/7048/6833773916_035ea3dbe4_o_d.jpg",
            "https://farm4.staticflickr.com/3132/3107626997_102250b81b_b_d.jpg",
            "https://farm6.staticflickr.com/5054/5532408016_49dab99785_o_d.jpg",
            "https://farm8.staticflickr.com/7279/7447223380_2f34a99e51_k_d.jpg",
            "https://farm7.staticflickr.com/6094/6254798710_7eb4fc9b9e_o_d.jpg",
            "https://farm3.staticflickr.com/2322/2483158372_d80f90137b_o_d.jpg",
            "https://farm4.staticflickr.com/3377/4613198694_7b05668a72_b_d.jpg",
            "https://farm3.staticflickr.com/2729/4425945810_1a369d1d7b_o_d.jpg"
    };
    public static final String[] DEALS_FASHION_URL = {
            "https://farm4.staticflickr.com/3946/15427793078_f9d3852274_k_d.jpg",
            "https://farm6.staticflickr.com/5626/22516022049_fd72daa042_k_d.jpg",
            "https://farm6.staticflickr.com/5643/22528209707_43e28dab98_k_d.jpg",
            "https://farm1.staticflickr.com/695/22916850741_9aaecd988e_k_d.jpg",
            "https://farm1.staticflickr.com/669/22484485341_ec39d27b83_k_d.jpg"
    };
    public static final String[] DEALS_GADGETS_URL = {
            "https://farm5.staticflickr.com/4002/4471944167_f012d09e66_b_d.jpg",
            "https://farm6.staticflickr.com/5068/5630662365_af13015a20_b_d.jpg",
            "https://farm9.staticflickr.com/8384/8641243736_766de2109d_k_d.jpg",
            "https://farm9.staticflickr.com/8607/16722924922_8c150e7e41_k_d.jpg",
            "https://farm4.staticflickr.com/3828/9524355523_cc1a969c10_h_d.jpg",
            "https://farm3.staticflickr.com/2492/5812924771_1ec43e7a40_b_d.jpg",
            "https://farm5.staticflickr.com/4047/5141617803_51c63836c2_b_d.jpg"
    };

    public static final String[] DEALS_HO_URL = {
            "https://farm3.staticflickr.com/2490/4099800502_f2d8d3c56a_b_d.jpg",
            "https://farm1.staticflickr.com/771/21717803999_0babec9da1_o_d.jpg",
            "https://farm2.staticflickr.com/1063/1414665399_a34d74382e_o_d.jpg",
            "https://farm3.staticflickr.com/2384/2117364772_6534d1c653_o_d.jpg",
            "https://farm5.staticflickr.com/4082/4820751991_04c4d2fa16_o_d.jpg",
            "https://farm9.staticflickr.com/8251/8652607225_9b0b54f293_h_d.jpg",
            "https://farm8.staticflickr.com/7440/11075738604_5c84eb2e7e_h_d.jpg"
    };

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "2qU0xzy2IylWbnMz8p9zAFSKZaeZFepQJ8J6SND9", "BD4XBe533g3PBZcEBgU0kgvgGZ7DctxqcMvaKO4H");

        CustomTypeface.getInstance().registerTypeface("batik_gangster", getAssets(), "fonts/batik_gangster.otf");

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
