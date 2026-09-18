package com.rachana.textcode;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText messageInput;
    private EditText keyInput;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput = findViewById(R.id.messageInput);
        keyInput = findViewById(R.id.keyInput);
        outputText = findViewById(R.id.outputText);

        Button encryptButton = findViewById(R.id.encryptButton);
        Button decryptButton = findViewById(R.id.decryptButton);

        encryptButton.setOnClickListener(v -> handleEncrypt());
        decryptButton.setOnClickListener(v -> handleDecrypt());
    }

    private void handleEncrypt() {
        String message = messageInput.getText().toString();
        String key = keyInput.getText().toString();

        if (message.isEmpty() || key.isEmpty()) {
            Toast.makeText(this, "Enter both a message and a secret key", Toast.LENGTH_SHORT).show();
            return;
        }

        String encoded = TextCodeCipher.encrypt(message, key);
        outputText.setText(encoded);
    }

    private void handleDecrypt() {
        String message = messageInput.getText().toString();
        String key = keyInput.getText().toString();

        if (message.isEmpty() || key.isEmpty()) {
            Toast.makeText(this, "Enter both a message and a secret key", Toast.LENGTH_SHORT).show();
            return;
        }

        String decoded = TextCodeCipher.decrypt(message, key);
        if (decoded == null) {
            outputText.setText(R.string.decrypt_error);
        } else {
            outputText.setText(decoded);
        }
    }
}
