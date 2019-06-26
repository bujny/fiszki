package tech.fiszki.logic;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TextToSpeechMock implements TTF {

    private TextToSpeech ttobj;

    @Override
    public void onInit(Context mContext) {
        ttobj = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    ttobj.setLanguage(Locale.UK);
                }
            }
        });
    }

    @Override
    public void read(String text){
        ttobj.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
