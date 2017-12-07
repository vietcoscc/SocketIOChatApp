package com.example.ominext.socketiochatapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.contentRecyclerView)
    RecyclerView contentRecyclerView;
    @BindView(R.id.edtMessage)
    EditText edtMessage;
    @BindView(R.id.btnSend)
    Button btnSend;
    private String name;
    private Socket socket = SocketIO.getInstance().getSocket();

    public MainActivity() throws MalformedURLException, URISyntaxException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showNameDialog();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtMessage.getText().toString().isEmpty()) {
                    ChatMessage chatMessage = new ChatMessage(name, "To name", "client ID", edtMessage.getText().toString());
                    String json = (new Gson()).toJson(chatMessage);
                    try {
                        socket.emit("sendTo", new JSONObject(json));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "message empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        socket.on("Emit", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, args[0].toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void showNameDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Ok", null);
        final EditText editText = new EditText(this);
        builder.setView(editText);
        builder.setMessage("Enter name");
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(editText.getText())) {
                            name = editText.getText().toString();
                            getSupportActionBar().setTitle(name);
                            dialog.dismiss();
                            socket.connect();
                        } else {
                            Toast.makeText(MainActivity.this, "Your name must not empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        dialog.show();
    }
}
