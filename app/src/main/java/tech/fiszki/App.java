package tech.fiszki;

import android.app.Application;

import tech.fiszki.data.DaoMaster;
import tech.fiszki.data.DaoSession;

public class App extends Application {

    private  static DaoMaster daoMaster;

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        //http://greenrobot.org/greendao/documentation//how-to-get-started/
        //*****************************************************************
        daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(this, "tmp51.db").getWritableDatabase());
        mDaoSession = daoMaster.newSession();

//        DataLoader.load2(mDaoSession);


//        WordSelector selector = new MainWordsSelector();
//
//        List<Word> words = null;
//        try {
//            words = selector.nextWordsToReview(2);
//        } catch (InsufficientWordCountException e) {
//            e.printStackTrace();
//        }
//
//        for (Word word : words) {
//            System.out.println(word.getOriginalWord());
//        }


    }

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }
}