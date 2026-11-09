package com.sabbir.islamicai;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout root, messages;
    EditText input;
    ScrollView scroll;

    int green = Color.rgb(22, 132, 91);
    int dark = Color.rgb(35, 45, 40);
    int light = Color.rgb(246, 248, 246);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        );

        buildScreen();
    }

    int dp(float value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density);
    }

    GradientDrawable bg(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);
        return t;
    }

    void buildScreen() {

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.WHITE);

        setContentView(root);

        // Header
        LinearLayout header = new LinearLayout(this);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setPadding(dp(18), dp(12), dp(18), dp(12));

        TextView logo = text("☪", 30, green);
        header.addView(logo);

        LinearLayout titleBox = new LinearLayout(this);
        titleBox.setOrientation(LinearLayout.VERTICAL);
        titleBox.setPadding(dp(10), 0, 0, 0);

        TextView title = text("SABBIR ISLAMIC AI", 17, dark);
        title.setTypeface(null, Typeface.BOLD);

        TextView subtitle = text("Islamic Knowledge Assistant", 11,
                Color.GRAY);

        titleBox.addView(title);
        titleBox.addView(subtitle);

        header.addView(titleBox,
                new LinearLayout.LayoutParams(0, -2, 1));

        TextView menu = text("☰", 25, green);
        header.addView(menu);

        root.addView(header);

        View line = new View(this);
        line.setBackgroundColor(0xFFECEFEC);
        root.addView(line,
                new LinearLayout.LayoutParams(-1, dp(1)));

        // Main chat area
        scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        messages = new LinearLayout(this);
        messages.setOrientation(LinearLayout.VERTICAL);
        messages.setPadding(dp(18), dp(22), dp(18), dp(18));

        scroll.addView(messages);

        root.addView(scroll,
                new LinearLayout.LayoutParams(
                        -1, 0, 1));

        // Welcome screen
        TextView icon = text("☪", 48, green);
        icon.setGravity(Gravity.CENTER);
        messages.addView(icon);

        TextView welcome = text(
                "আসসালামু আলাইকুম!",
                25, dark);

        welcome.setGravity(Gravity.CENTER);
        welcome.setTypeface(null, Typeface.BOLD);

        messages.addView(welcome);

        TextView description = text(
                "ইসলাম সম্পর্কে কী জানতে চান?\n" +
                "আপনার প্রশ্ন লিখুন।",
                15, Color.GRAY);

        description.setGravity(Gravity.CENTER);
        description.setPadding(0, dp(8), 0, dp(25));

        messages.addView(description);

        addFeature("📖  কুরআন ও হাদিস",
                "কুরআন ও হাদিস সম্পর্কে জানতে চান?");

        addFeature("⚖️  ইসলামিক মাসআলা",
                "ইসলামিক মাসআলা সম্পর্কে জানতে চান?");

        addFeature("🕌  ইসলামের ইতিহাস",
                "ইসলামের ইতিহাস সম্পর্কে জানতে চান?");

        addFeature("🎨  ইসলামিক পোস্টার",
                "পোস্টার স্টুডিও চালু করতে চান?");

        // Input area
        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(LinearLayout.VERTICAL);
        bottom.setPadding(dp(12), dp(10), dp(12), dp(8));
        bottom.setBackgroundColor(Color.WHITE);

        LinearLayout inputBox = new LinearLayout(this);
        inputBox.setGravity(Gravity.CENTER_VERTICAL);
        inputBox.setPadding(dp(12), dp(5), dp(7), dp(5));
        inputBox.setBackground(bg(light, 25));

        input = new EditText(this);
        input.setHint("আপনার ইসলামিক প্রশ্ন লিখুন...");
        input.setTextSize(14);
        input.setTextColor(dark);
        input.setHintTextColor(Color.GRAY);
        input.setBackgroundColor(Color.TRANSPARENT);
        input.setSingleLine(false);
        input.setMaxLines(4);
        input.setPadding(dp(5), dp(8), dp(5), dp(8));

        inputBox.addView(input,
                new LinearLayout.LayoutParams(0, -2, 1));

        TextView send = text("➤", 23, Color.WHITE);
        send.setGravity(Gravity.CENTER);
        send.setBackground(bg(green, 30));

        LinearLayout.LayoutParams sendParams =
                new LinearLayout.LayoutParams(dp(45), dp(45));

        inputBox.addView(send, sendParams);

        send.setOnClickListener(v -> sendMessage());

        bottom.addView(inputBox);

        TextView footer = text(
                "SABBIR ISLAMIC AI  •  Developer: Sabbir Ahmed",
                10, Color.GRAY);

        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, dp(9), 0, 0);

        bottom.addView(footer);

        root.addView(bottom);
    }

    void addFeature(String name, String message) {

        TextView item = text(name, 15, dark);
        item.setPadding(dp(15), dp(15), dp(15), dp(15));
        item.setBackground(bg(light, 14));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, -2);

        params.bottomMargin = dp(10);

        messages.addView(item, params);

        item.setOnClickListener(v -> {
            input.setText(message);
            input.setSelection(input.length());
        });
    }

    void sendMessage() {

        String question = input.getText().toString().trim();

        if (question.isEmpty()) {
            Toast.makeText(this,
                    "প্রশ্ন লিখুন",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        TextView userMessage = text(question, 15, Color.WHITE);
        userMessage.setPadding(dp(15), dp(12), dp(15), dp(12));
        userMessage.setBackground(bg(green, 18));

        LinearLayout.LayoutParams userParams =
                new LinearLayout.LayoutParams(
                        -2, -2, 0);

        userParams.gravity = Gravity.RIGHT;
        userParams.bottomMargin = dp(15);

        messages.addView(userMessage, userParams);

        TextView reply = text(
                "আপনার প্রশ্নটি গ্রহণ করা হয়েছে। " +
                "ইসলামিক AI সেবা সংযুক্ত করার পর " +
                "এখানে নির্ভরযোগ্য সূত্রসহ উত্তর দেখানো হবে।",
                14, dark);

        reply.setPadding(dp(15), dp(12), dp(15), dp(12));
        reply.setBackground(bg(light, 16));

        LinearLayout.LayoutParams replyParams =
                new LinearLayout.LayoutParams(
                        -1, -2);

        replyParams.bottomMargin = dp(15);

        messages.addView(reply, replyParams);

        input.setText("");

        ((InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(
                        input.getWindowToken(), 0);

        scroll.post(() ->
                scroll.fullScroll(View.FOCUS_DOWN));
    }
          }
