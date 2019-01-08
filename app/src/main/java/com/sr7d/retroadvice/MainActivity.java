package com.sr7d.retroadvice;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sr7d.retroadvice.models.AdviceSlipResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private AdviceApiService adviceApiService;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        createRetrofitClient();
        fetchAdvice();
    }

    private void bindViews() {
        textView = findViewById(R.id.text_view_advice);
    }

    private void fetchAdvice() {
        progressBarSetup();
        adviceApiService.getAdvice().enqueue(new Callback<AdviceSlipResponse>() {
            @Override
            public void onResponse(Call<AdviceSlipResponse> call, Response<AdviceSlipResponse> response) {
                AdviceSlipResponse adviceSlipResponse = response.body();
                String advice = adviceSlipResponse.getAdviceSlip().getAdvice();
                textView.setText(advice);
                progressBar.dismiss();
            }

            @Override
            public void onFailure(Call<AdviceSlipResponse> call, Throwable t) {

            }
        });
    }

    private void progressBarSetup() {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Fetching Advice...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
    }

    private void createRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.adviceslip.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        adviceApiService = retrofit.create(AdviceApiService.class);
    }

    public interface AdviceApiService {
        @GET("advice")
        Call<AdviceSlipResponse> getAdvice();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_refresh:
                fetchAdvice();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
