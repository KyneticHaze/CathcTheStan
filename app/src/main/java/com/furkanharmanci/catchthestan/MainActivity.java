package com.furkanharmanci.catchthestan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView time;
    TextView score;
    int number;
    ImageView stan1;
    ImageView stan2;
    ImageView stan3;
    ImageView stan4;
    ImageView stan5;
    ImageView stan6;
    ImageView stan7;
    ImageView stan8;
    ImageView stan9;
    ImageView stan10;
    ImageView stan11;
    ImageView stan12;
    ImageView[] imageArr;

    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
        score = findViewById(R.id.score);

        stan1 = findViewById(R.id.stan1);
        stan2 = findViewById(R.id.stan2);
        stan3 = findViewById(R.id.stan3);
        stan4 = findViewById(R.id.stan4);
        stan5 = findViewById(R.id.stan5);
        stan6 = findViewById(R.id.stan6);
        stan7 = findViewById(R.id.stan7);
        stan8 = findViewById(R.id.stan8);
        stan9 = findViewById(R.id.stan9);
        stan10 = findViewById(R.id.stan10);
        stan11 = findViewById(R.id.stan11);
        stan12 = findViewById(R.id.stan12);

        number = 0;


        imageArr = new ImageView[]{stan1, stan2, stan3, stan4, stan5, stan6, stan7 ,stan8, stan9, stan10, stan11, stan12};


        hideImages();
        // resimleri gizleme methodu

        new CountDownTimer(25000, 1000) {
            // Geriye sayım methodu
            // 25 saniye, 1'er saniye şeklinde geriye sayacak
            @Override
            public void onTick(long ms) {
                time.setText("Time: " + ms / 1000);
            }

            @Override
            public void onFinish() {
                // Sayaç sonlandığında aktifleşecek method

                time.setText("Time Over!");

                handler.removeCallbacks(runnable);
                // runnable geriye çağırma veyahut sonlandırma

                for (ImageView inVisibleImage : imageArr) {
                    inVisibleImage.setVisibility(View.INVISIBLE);
                    // Sayaç sonlandığında tüm resimler görünmez kılınacak
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                // Sayaç sonlandığında bir alert dönecek

                alert.setTitle("Restart Game");
                alert.setMessage("Dou you want to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // alert evetlendiğinde
                        Intent intent = getIntent();
                        finish(); // sayfa sonlanacak
                        startActivity(intent); // sayfa başlayacak
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // alert negatif halde
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                        // Ufak mesaj yayınlanacak
                    }
                });

                alert.show();
                // alert'in görünmesi için bu kod gerekli
            }
        }.start();
    }

    public void changeStan(View view) {
        // her bir stan resmine tıklamak için kullanılan method
        number++;
        score.setText("Score: " + number);
        // Her tıklanıldığında özel tanımlanan number değişkeni birer arttırılacak ve score değişkeninin görünümüne yazılacak.
    }

    public void hideImages() {
        // onCreate() methodu içinde yazılan resim gizleme methodunun kod bloğu

        handler = new Handler(Looper.getMainLooper());
        // bir tutucu tanımlandı

        runnable = new Runnable() {
            // bir çalıştırıcı tanımlandı
            @Override
            public void run() {

                for (ImageView image : imageArr) {
                    image.setVisibility(View.INVISIBLE);
                }
                // çalıştırıcı çalışma esnasında iken resimler tamamen görünmez halde.


                Random random = new Random();
                // rastgele sayı belirleyen sınıftan bir değişken tanımlandı.

                int i = random.nextInt(12);
                // index değeri tutacak ve 12'ye kadar rastgele gelecek sayıları bir değişkene atadık.

                imageArr[i].setVisibility(View.VISIBLE);
                // rastgele gelen i değişkeni, resim listesinin index değerini tutarak rasgele şekilde gelecek resimleri görünür kıldık.
                handler.postDelayed(this, 700);
                // çalıştırıcı 700ms'de gecikmeli çalışacak.
            }
        };

        handler.post(runnable);
        // çalıştırıcının aktifleşmesi için elzem kod
    }
}