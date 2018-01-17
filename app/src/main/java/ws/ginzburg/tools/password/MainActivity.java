package ws.ginzburg.tools.password;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        serviceNameEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence c, int i, int i1, int i2) {}

            @Override public void onTextChanged(CharSequence c, int i, int i1, int i2) {}

            @Override public void afterTextChanged(Editable e) {
                simplifiedEditText.setText(simplifyServiceName(e.toString()));
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                passwordEditText.setText(generatePassword(
                        prefixEditText.getText().toString(),
                        secretEditText.getText().toString(),
                        simplifyServiceName(serviceNameEditText.getText().toString()),
                        Integer.valueOf(suffixLengthEditText.getText().toString())
                ));
            }
        });
    }

    protected String generatePassword(String prefix, String secret, String serviceName, int suffixLength) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            // this shouldn't happen anywhere
            Log.wtf(TAG, "SHA-256 algorithm not found");
            return null;
        }

        String suffixSource = (prefix + secret + serviceName);
        String digest = byteArrayToHex(md.digest(suffixSource.getBytes()));
        return prefix + digest.substring(0, suffixLength);
    }

    protected String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    protected String simplifyServiceName(String serviceName) {
        return serviceName
                .replaceFirst(".*://+", "")
                .toLowerCase()
                .replaceAll("[^a-z0-9]", "");
    }

    private EditText prefixEditText;
    private EditText secretEditText;
    private EditText suffixLengthEditText;
    private EditText serviceNameEditText;
    private EditText simplifiedEditText;
    private Button generateButton;
    private EditText passwordEditText;

    private void findViews() {
        prefixEditText = (EditText)findViewById(R.id.prefixEditText);
        secretEditText = (EditText)findViewById(R.id.secretEditText);
        suffixLengthEditText = (EditText)findViewById(R.id.suffixLengthEditText);
        serviceNameEditText = (EditText)findViewById(R.id.serviceNameEditText);
        simplifiedEditText = (EditText)findViewById(R.id.simplifiedEditText);
        generateButton = (Button)findViewById(R.id.generateButton);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
    }
}
