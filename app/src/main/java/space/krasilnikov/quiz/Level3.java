package space.krasilnikov.quiz;

import static space.krasilnikov.quiz.R.anim;
import static space.krasilnikov.quiz.R.drawable;
import static space.krasilnikov.quiz.R.id;
import static space.krasilnikov.quiz.R.layout;
import static space.krasilnikov.quiz.R.string;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.Random;

public class Level3 extends AppCompatActivity {

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
        setContentView(layout.universal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView text_levels = findViewById(id.text_levels);
        text_levels.setText(string.level_1);

        Button button_back = (Button)findViewById(id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent); finish();
                } catch (Exception e) {

                }
            }
        });

        final ImageView img_left = (ImageView)findViewById(id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(id.text_left);
        final TextView text_right = findViewById(id.text_right);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout.previewdialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        // установить картинку в диалоговое окно
        ImageView previewdialog = (ImageView) dialog.findViewById(id.preview_img);
        previewdialog.setImageResource(drawable.preview_win_three);

        // установить фон в диалоговое окно
        LinearLayout dialog_fon = dialog.findViewById(id.dialogfon);
        dialog_fon.setBackgroundResource(drawable.preview_background_three);

        // установить текст задания в диалоговое окно
        TextView textdescription = (TextView) dialog.findViewById(id.text_description);
        textdescription.setText(string.level_three);

        TextView btn_close = (TextView)dialog.findViewById(id.btn_close);

        // Диалоговое окно закрить
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    dialog.cancel();
                } catch (Exception e) {

                }

                dialog.dismiss();
            }
        });

        // Диалоговое окно продолжить
        Button btn_continue = (Button)dialog.findViewById(id.btn_continue);
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
        dialogEnd.setContentView(layout.dialogend);
        Objects.requireNonNull(dialogEnd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);

        TextView btn_close2 = (TextView)dialogEnd.findViewById(id.btn_close);

        // Диалоговое окно закрить
        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    dialogEnd.cancel();
                } catch (Exception e) {

                }

                dialogEnd.dismiss();
            }
        });

        // Диалоговое окно продолжить
        Button btn_continue2 = (Button)dialogEnd.findViewById(id.btn_continue);
        btn_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, Level3.class);
                    startActivity(intent);
                    finish();
                    dialogEnd.dismiss();
                } catch (Exception e) {

                }

            }
        });

        TextView text_description_end = dialogEnd.findViewById(id.text_description_end);
        text_description_end.setText(string.level_two_end);

        //______________________________

        // Прогресс игри

        final int[] progress = {
            id.point1, id.point2, id.point3, id.point4, id.point5,
            id.point6, id.point7, id.point8, id.point9, id.point10,
            id.point11, id.point12, id.point13, id.point14, id.point15,
            id.point16, id.point17, id.point18, id.point19, id.point20
        };

        // Анимация
        final Animation a = AnimationUtils.loadAnimation(Level3.this, anim.alpha);

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
                        img_left.setImageResource(drawable.image_true);
                    } else {
                        img_left.setImageResource(drawable.image_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 20) {
                            count++;
                        }

                        // Красим серим
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(drawable.style_pointer);
                        }

                        // Красим зеленим количество верных ответов
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(drawable.style_pointer_green);
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
                                tv.setBackgroundResource(drawable.style_pointer);
                            }

                            // Красим зеленим количество верных ответов
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(drawable.style_pointer_green);
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
                        img_right.setImageResource(drawable.image_true);
                    } else {
                        img_right.setImageResource(drawable.image_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft < numRight) {
                        if (count < 20) {
                            count++;
                        }

                        // Красим серим
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(drawable.style_pointer);
                        }

                        // Красим зеленим количество верных ответов
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(drawable.style_pointer_green);
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
                                tv.setBackgroundResource(drawable.style_pointer);
                            }

                            // Красим зеленим количество верных ответов
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(drawable.style_pointer_green);
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
            Intent intent = new Intent(Level3.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
}