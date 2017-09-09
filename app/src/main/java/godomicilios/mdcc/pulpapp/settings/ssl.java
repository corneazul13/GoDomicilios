package godomicilios.mdcc.pulpapp.settings;

/**
 * Created by PROGRAMACION5 on 18/05/2017.
 */
import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;

/**
 * An {@link com.android.volley.toolbox.HttpStack HttpStack} implementation which
 * uses OkHttp as its transport.
 */
public class ssl extends HurlStack {
    private final OkHttpClient client;

    public ssl() {
        this(new OkHttpClient());
    }

    public ssl(OkHttpClient client) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.client = client;
    }

}
