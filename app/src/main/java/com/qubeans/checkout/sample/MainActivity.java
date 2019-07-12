package com.qubeans.checkout.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qubeans.checkout.models.Soofa;

public class MainActivity extends AppCompatActivity {
    private TextView tvAmount, tvReference;
    private Button bSubmit;
    private static final String TAG = "Sooofa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAmount = findViewById(R.id.tv_amount);
        tvReference = findViewById(R.id.tv_reference);
        bSubmit = findViewById(R.id.bSubmit);
        bSubmit.setOnClickListener(v -> {
            String amount = tvAmount.getText().toString().trim();

            String reference = tvReference.getText().toString().trim();

            new Soofa(this,
                    5002,
                    Double.parseDouble(amount),
                    reference
            ).create();

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Soofa.TRANSACTION_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("message");

            switch (resultCode){
                case Soofa.TRANSACTION_FAILED:
                    /**
                     *
                     * The transaction did not go through.
                     * Network issues, bad data et al.
                     *
                    *  In case the transaction failed, the extra data is  {"message"}
                     *
                     *  Use your logic here
                    * */

                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    break;

               case Soofa.TRANSACTION_CANCELLED:

                   /**
                    *The user has cancelled the transaction, onBackPressed or the transaction timed out
                    *
                    * Same response as  {@link  Soofa.TRANSACTION_FAILED}
                    *
                    *
                    * input your logic here.
                    *
                    *
                    * */
                   Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                   Log.e(TAG, message );
                   break;

                case Soofa.TRANSACTION_SUCCESSFUL:
                    /**
                     *The transaction is successful.
                     *
                     * Response includes the transaction id    {"tid"}
                     * and the reference {"reference"}
                     *
                     * input your logic here
                     * **/
                    String tId = data.getStringExtra("tid");
                    String reference = data.getStringExtra("reference");
                    Log.e(TAG, "onActivityResult: " + tId + " " + reference );
                    break;
            }
        }

    }
}
