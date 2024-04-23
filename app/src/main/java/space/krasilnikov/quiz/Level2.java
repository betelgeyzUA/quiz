package space.krasilnikov.quiz;

import static space.krasilnikov.quiz.R.*;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.Random;

public class Level2 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.universal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level_1);

        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent); finish();
                } catch (Exception e) {

                }
            }
        });

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        // установить картинку в диалоговое окно
        ImageView previewdialog = (ImageView) dialog.findViewById(R.id.preview_img);
        previewdialog.setImageResource(R.drawable.preview_win_two);
//        // установить текст задания в диалоговое окно
        TextView textdescription = (TextView) dialog.findViewById(R.id.text_description);
        textdescription.setText(R.string.level_two);

        TextView btn_close = (TextView)dialog.findViewById(R.id.btn_close);

        // Диалоговое окно закрить
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    dialog.cancel();
                } catch (Exception e) {

                }

                dialog.dismiss();
            }
        });

        // Диалоговое окно продолжить
        Button btn_continue = (Button)dialog.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {

                }

            }
        });

        dialog.show();

        //______________________________

        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        Objects.requireNonNull(dialogEnd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);

        TextView btn_close2 = (TextView)dialogEnd.findViewById(R.id.btn_close);

        // Диалоговое окно закрить
        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    dialogEnd.cancel();
                } catch (Exception e) {

                }

                dialogEnd.dismiss();
            }
        });

        // Диалоговое окно продолжить
        Button btn_continue2 = (Button)dialogEnd.findViewById(R.id.btn_continue);
        btn_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level2.this, Level3.class);
                    startActivity(intent);
                    finish();
                    dialogEnd.dismiss();
                } catch (Exception e) {

                }

            }
        });

        TextView text_description_end = dialogEnd.findViewById(id.text_description_end);
        text_description_end.setText(R.string.level_two_end);

        //______________________________

        // Прогресс игри

        final int[] progress = {
            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
            R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
            R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
            R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };

        // Анимация
        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);

        // Генерируєм левую сторону
        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images2[numLeft]);
        text_left.setText(array.texts2[numLeft]);

        // Генерируєм правую сторону
        numRight = random.nextInt(10);

        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images2[numRight]);
        text_right.setText(array.texts2[numRight]);

        // Нажатиє на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Касание картинки
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false); // Блокируем правую картинку
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.image_true);
                    } else {
                        img_left.setImageResource(R.drawable.image_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 20) {
                            count++;
                        }

                        // Красим серим
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pointer);
                        }

                        // Красим зеленим количество верных ответов
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pointer_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }

                            // Красим серим
                            for (int i = 0; i < 19; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_pointer);
                            }

                            // Красим зеленим количество верных ответов
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_pointer_green);
                            }
                        }
                    }

                    if (count == 20) {
                        dialogEnd.show();
                    } else {
                        // Генерируєм левую сторону
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts2[numLeft]);

                        // Генерируєм правую сторону
                        numRight = random.nextInt(10);

                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images2[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);

                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });

        // Нажатиє на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Касание картинки
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false); // Блокируем левую картинку
                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.image_true);
                    } else {
                        img_right.setImageResource(R.drawable.image_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft < numRight) {
                        if (count < 20) {
                            count++;
                        }

                        // Красим серим
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pointer);
                        }

                        // Красим зеленим количество верных ответов
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pointer_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }

                            // Красим серим
                            for (int i = 0; i < 19; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_pointer);
                            }

                            // Красим зеленим количество верных ответов
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_pointer_green);
                            }
                        }
                    }

                    if (count == 20) {
                        dialogEnd.show();
                    } else {
                        // Генерируєм левую сторону
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts2[numLeft]);

                        // Генерируєм правую сторону
                        numRight = random.nextInt(10);

                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images2[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        try {
            Intent intent = new Intent(Level2.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
}