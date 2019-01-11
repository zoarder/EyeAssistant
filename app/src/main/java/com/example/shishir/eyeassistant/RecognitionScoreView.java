package com.example.shishir.eyeassistant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.List;
import java.util.Locale;

//import org.tensorflow.demo.Classifier.Recognition;

public class RecognitionScoreView extends View implements ResultsView {
    private static final float TEXT_SIZE_DIP = 24;
    private List<Classifier.Recognition> results;
    private final float textSizePx;
    private final Paint fgPaint;
    private final Paint bgPaint;
    TextToSpeech t1;

    public RecognitionScoreView(Context context, AttributeSet set) {
        super(context, set);

        textSizePx =
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
        fgPaint = new Paint();
        fgPaint.setTextSize(textSizePx);

        bgPaint = new Paint();
        bgPaint.setColor(0xcc4285f4);

        t1 = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });
    }

    @Override
    public void setResults(final List<Classifier.Recognition> results) {
        this.results = results;
        postInvalidate();
    }

    @Override
    public void onDraw(final Canvas canvas) {
        final int x = 10;
        int y = (int) (fgPaint.getTextSize() * 1.5f);

        canvas.drawPaint(bgPaint);

        if (results != null) {
            Classifier.Recognition recog = results.get(0);
//            for (final Classifier.Recognition recog : results) {
                canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);
//                y += fgPaint.getTextSize() * 1.5f;
                t1.speak(recog.getTitle(), TextToSpeech.QUEUE_FLUSH, null);
//            }
        }
    }
}
